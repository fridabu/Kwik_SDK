package me.kwik.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.widget.EditText;

import me.kwik.bl.KwikMe;
import me.kwik.bl.KwikServerError;
import me.kwik.listeners.CreateUserListener;
import me.kwik.listeners.InitialHandShakeListener;
import me.kwik.listeners.SendValidationCodeListener;
import me.kwik.rest.responses.CreateUserResponse;
import me.kwik.rest.responses.InitialHandShakeResponse;
import me.kwik.utils.Logger;
import me.kwk.utils.Utils;


public class SignupActivity extends BaseActivity {

    private EditText mNameEditText;
    private EditText mPhoneNumberEditText;
    private String s;
    boolean cancelWaitForSMSTimeout = false;
    private IntentFilter mIntentFilter = new IntentFilter();
    private BroadcastReceiver mSmsBroadcastReceiver;
    private String TAG = SignupActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mNameEditText = (EditText)findViewById(R.id.sign_up_activity_name_edit_text);
        mPhoneNumberEditText = (EditText)findViewById(R.id.sign_up_activity_phone_edit_text);
        mActionBarTitle.setText(getResources().getString(R.string.sign_up_activity_title));
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        Utils.setOnTouchAndClickListener(mPhoneNumberEditText, this);

        if(getIntent().getStringExtra("sender") == LoginActivity.class.getSimpleName()){
            if(getIntent().getStringExtra("phoneNumber") != null) {
                mPhoneNumberEditText.setText(getIntent().getStringExtra("phoneNumber"));
            }
        }


    }

    @Override
    protected void clickNext() {
        super.clickNext();

        if(isEditTextsEmpty(new EditText[]{mNameEditText, mPhoneNumberEditText},new String[]{"name is empty","phone is empty"})){
            return;
        }

        super.showProgressBar();

        final String name = mNameEditText.getText().toString();
        final String phoneNumber = mPhoneNumberEditText.getText().toString();
        KwikMe k = new KwikMe(this);

        KwikMe.initialHandShake("a", "a", null, "nn", new InitialHandShakeListener() {
            @Override
            public void initialHandShakeDone(InitialHandShakeResponse.Message message) {

                if (message != null) {
                    showOneButtonErrorDialog(message.getTitle(), message.getMessage());
                } else {
                    sendPhoneNumber(phoneNumber.replace(" ", ""), false, name);
                }
            }

            @Override
            public void initialHandShakeError(KwikServerError kwikServerError) {
                hideProgressBar();
                if (kwikServerError != null) {
                    showOneButtonErrorDialog("Error", kwikServerError.getMessage());
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mSmsBroadcastReceiver != null ){
            try {
                unregisterReceiver(mSmsBroadcastReceiver);
            }catch (Exception e){
                //Do nothing
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //If this called after click change phone number at VerifyPhoneNumber screen
        if(getIntent().getStringExtra("sender")!= null && getIntent().getStringExtra("sender").equals(VerifyPhoneActivity.class.getSimpleName())){
            if(getIntent().getStringExtra("phoneNumber") != null) {
                mPhoneNumberEditText.setText(getIntent().getStringExtra("phoneNumber"));
            }

            if(getIntent().getStringExtra("name") != null){
                mNameEditText.setText(getIntent().getStringExtra("name"));
            }
        }

        cancelWaitForSMSTimeout = false;
        mSmsBroadcastReceiver = new BroadcastReceiver() {
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
                            cancelWaitForSMSTimeout = true;
                            createUser(s);
                        }
                    });


                }
            }
        };
        registerReceiver(mSmsBroadcastReceiver, mIntentFilter);
    }

    private void createUser(String s) {
        String validationCode =s.substring(0,4);
        String phoneNumber = mPhoneNumberEditText.getText().toString();
        KwikMe.createUser(phoneNumber, validationCode, new CreateUserListener() {
            @Override
            public void createUserDone(CreateUserResponse res) {
                hideProgressBar();
                Intent i = new Intent(SignupActivity.this, ChooseNetworkActivity.class);
                startActivity(i);
                SignupActivity.this.finish();
            }

            @Override
            public void createUserError(KwikServerError error) {
                hideProgressBar();
                showOneButtonErrorDialog("error", error.getMessage());
            }
        });
    }

    private void sendPhoneNumber(final String phoneNumber, final boolean isUserExist,final String name) {

            KwikMe.sendValidationCode(phoneNumber, isUserExist, new SendValidationCodeListener() {
            @Override
            public void sendValidationCodeDone() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!cancelWaitForSMSTimeout) {
                            hideProgressBar();
                            Intent i = new Intent(SignupActivity.this, VerifyPhoneActivity.class);
                            if (isUserExist) {
                                i.putExtra("sender", LoginActivity.class.getSimpleName());
                            } else {
                                i.putExtra("sender", SignupActivity.class.getSimpleName());
                            }
                            i.putExtra("phoneNumber", phoneNumber);
                            i.putExtra("name", name);
                            startActivity(i);
                            SignupActivity.this.finish();
                        }
                    }
                }, 8000);

            }

            @Override
            public void sendValidationCodeError(KwikServerError kwikServerError) {
                hideProgressBar();
                if(kwikServerError.getValue() == 409){
                    showTwoButtonErrorDialog("Error",kwikServerError.getMessage(),
                            "Sign up with another user",
                            "log in with this number",null,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    showProgressBar();
                                    sendPhoneNumber(phoneNumber.replace(" ",""),true, name);

                                }
                            });

                }else {
                    showOneButtonErrorDialog("error", kwikServerError.getMessage());
                }
            }
        });
    }
}
