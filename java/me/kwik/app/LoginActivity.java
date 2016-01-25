package me.kwik.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.EditText;

import me.kwik.bl.KwikMe;
import me.kwik.bl.KwikServerError;
import me.kwik.listeners.InitialHandShakeListener;
import me.kwik.listeners.LoginByValidationCodeListener;
import me.kwik.listeners.SendValidationCodeListener;
import me.kwik.rest.responses.InitialHandShakeResponse;
import me.kwik.rest.responses.LoginByValidationCodeResponse;
import me.kwik.utils.Logger;
import me.kwk.utils.Utils;

public class LoginActivity extends BaseActivity {

    EditText mPhoneNumberEditText;
    String s;
    IntentFilter mIntentFilter = new IntentFilter();
    boolean cancelWaitForSMSTimeout = false;
    private BroadcastReceiver mSmsBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPhoneNumberEditText = (EditText)findViewById(R.id.login_activity_phone_edit_text);
        Utils.setOnTouchAndClickListener(mPhoneNumberEditText, this);
        mActionBarTitle.setText("LOG IN");
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSmsBroadcastReceiver= new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent intent) {
                Bundle bundle = intent.getExtras();

                if (bundle != null) {

                    Object[] pdus = (Object[]) intent.getExtras().get("pdus");
                    SmsMessage shortMessage;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        shortMessage = SmsMessage.createFromPdu((byte[]) pdus[0], format);
                    } else {
                        shortMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
                    }

                    s = shortMessage.getDisplayMessageBody();    // has the actual message
                    Logger.d("TAG", "Sms : %s", s);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            login(s);
                        }
                    });

                }
            }
        };
        registerReceiver(mSmsBroadcastReceiver, mIntentFilter);
    }



    @Override
    protected void onPause() {
        super.onPause();
        if(mSmsBroadcastReceiver != null){
            try{
                unregisterReceiver(mSmsBroadcastReceiver);
            }catch (Exception e){
                //Do nothing
            }
        }
    }

    private void login(String s) {

        String validationCode = s.substring(0,4);
        KwikMe.loginByValidationCode(validationCode, mPhoneNumberEditText.getText().toString(), new LoginByValidationCodeListener() {
            @Override
            public void loginByValidationCodeDone(LoginByValidationCodeResponse res) {
                cancelWaitForSMSTimeout = true;
                hideProgressBar();
                Intent i = new Intent(LoginActivity.this, ChooseNetworkActivity.class);
                i.putExtra("sender", LoginActivity.class.getSimpleName());
                startActivity(i);
                LoginActivity.this.finish();
            }

            @Override
            public void loginByValidationCodeError(KwikServerError error) {
                hideProgressBar();
                showOneButtonErrorDialog("error", error.getMessage());
            }
        });
    }

    @Override
    protected void clickNext() {
        super.clickNext();

        if(isEditTextsEmpty(new EditText[]{mPhoneNumberEditText},new String[]{"phone is empty"})){
            return;
        }

        super.showProgressBar();


        final String phoneNumber = mPhoneNumberEditText.getText().toString();
        KwikMe k = new KwikMe(this);

        KwikMe.initialHandShake("a", "a", null, "nn", new InitialHandShakeListener() {
            @Override
            public void initialHandShakeDone(InitialHandShakeResponse.Message message) {

                if(message != null){
                    showOneButtonErrorDialog(message.getTitle(),message.getMessage());
                }else{
                    sendPhoneNumber(phoneNumber,"null");
                }
            }

            @Override
            public void initialHandShakeError(KwikServerError kwikServerError) {
                hideProgressBar();
                if(kwikServerError != null){
                    showOneButtonErrorDialog("Error",kwikServerError.getMessage());
                }

            }
        });
    }

    private void sendPhoneNumber(final String phoneNumber,final String name) {

        KwikMe.sendValidationCode(phoneNumber,true, new SendValidationCodeListener() {
            @Override
            public void sendValidationCodeDone() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!cancelWaitForSMSTimeout) {
                            hideProgressBar();
                            Intent i = new Intent(LoginActivity.this, VerifyPhoneActivity.class);
                            i.putExtra("sender", LoginActivity.class.getSimpleName());
                            i.putExtra("phoneNumber", phoneNumber);
                            i.putExtra("name", name);
                            startActivity(i);
                            LoginActivity.this.finish();
                        }
                    }
                }, 8000);
            }

            @Override
            public void sendValidationCodeError(KwikServerError kwikServerError) {
                hideProgressBar();
                if(kwikServerError.getValue() == 404) {
                    showTwoButtonErrorDialog("Error", kwikServerError.getMessage(),
                            "Cancel",
                            "Sign up", null,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                                    i.putExtra("sender", LoginActivity.class.getSimpleName());
                                    i.putExtra("phoneNumber", phoneNumber);
                                    startActivity(i);
                                    LoginActivity.this.finish();

                                }
                            });
                }else {
                    showOneButtonErrorDialog("error", kwikServerError.getMessage());
                }
            }
        });
    }
}
