package me.kwik.app;

import android.os.Bundle;

import me.kwik.bl.KwikMe;


public class SignupActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mActionBarTitle.setText(getResources().getString(R.string.sign_up_activity_title));


    }
}
