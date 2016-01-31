package me.kwik.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.kwik.bl.KwikMe;
import me.kwik.bl.KwikServerError;
import me.kwik.listeners.CreateUserListener;
import me.kwik.listeners.LoginByValidationCodeListener;
import me.kwik.listeners.SendValidationCodeListener;
import me.kwik.rest.responses.CreateUserResponse;
import me.kwik.rest.responses.LoginByValidationCodeResponse;
import me.kwik.utils.Logger;


public class VerifyPhoneActivity extends BaseActivity {

    private EditText mValidationCodeEditText;
    private String name;
    private String phoneNumber;
    private String sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        TextView sentMessage = (TextView)findViewById(R.id.verify_phone_activity_message_sent_text_view);
        mValidationCodeEditText = (EditText)findViewById(R.id.verify_phone_activity_code_edit_text);

        name = getIntent().getStringExtra("name");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        sender = getIntent().getStringExtra("sender");

        mActionBarTitle.setText("SMS verify");

        sentMessage.setText("Enter the code we sent to " + phoneNumber);


    }

    // Click resend sms link
    public void resendSms(View resendTextView){
        if(phoneNumber != null ) {
            if(sender.equals(SignupActivity.class.getSimpleName())) {

                sendPhoneNumber(phoneNumber, false , null);
            }else if(sender.equals(LoginActivity.class.getSimpleName())){
                sendPhoneNumber(phoneNumber, true, null);
            }
        }
    }

    // Click change phone number link
    public void changePhoneNumber(View changePhoneNumberTextView){
        Class a;

        if(name.isEmpty()){
            a = LoginActivity.class;
        }else{
            a = SignupActivity.class;
        }

        Intent i = new Intent(VerifyPhoneActivity.this,a);
        i.putExtra("sender",VerifyPhoneActivity.class.getSimpleName());
        i.putExtra("name",name);
        i.putExtra("phoneNumber",phoneNumber);
        startActivity(i);
        this.finish();

    }

    @Override
    protected void clickNext() {
        super.clickNext();

        if(isEditTextsEmpty(new EditText[]{mValidationCodeEditText}, new String[]{"Please enter the code"})){
            return;
        }
        String validationCode = mValidationCodeEditText.getText().toString();

        if(sender.equals(SignupActivity.class.getSimpleName())) {
            KwikMe.createUser(phoneNumber, validationCode, new CreateUserListener() {
                @Override
                public void createUserDone(CreateUserResponse res) {
                    Intent i = new Intent(VerifyPhoneActivity.this, ChooseNetworkActivity.class);
                    i.putExtra("name", name);
                    startActivity(i);
                    VerifyPhoneActivity.this.finish();
                }

                @Override
                public void createUserError(KwikServerError error) {
                    showOneButtonErrorDialog("error", error.getMessage());
                }
            });

        }else if(sender.equals(LoginActivity.class.getSimpleName())){
            KwikMe.loginByValidationCode(validationCode,phoneNumber, new LoginByValidationCodeListener() {
                @Override
                public void loginByValidationCodeDone(LoginByValidationCodeResponse res) {
                    Intent i = new Intent(VerifyPhoneActivity.this, ChooseNetworkActivity.class);
                    i.putExtra("name", name);
                    startActivity(i);
                    VerifyPhoneActivity.this.finish();
                }

                @Override
                public void loginByValidationCodeError(KwikServerError error) {
                    showOneButtonErrorDialog("error", error.getMessage());
                }
            });

        }

    }

    private void sendPhoneNumber(final String phoneNumber, final boolean isUserExist,final String name) {

        showProgressBar();
        KwikMe.sendValidationCode(phoneNumber, isUserExist, new SendValidationCodeListener() {
            @Override
            public void sendValidationCodeDone() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressBar();
                    }
                }, 8000);

            }

            @Override
            public void sendValidationCodeError(KwikServerError kwikServerError) {
                hideProgressBar();
                showOneButtonErrorDialog("error", kwikServerError.getMessage());

            }
        });
    }
}
