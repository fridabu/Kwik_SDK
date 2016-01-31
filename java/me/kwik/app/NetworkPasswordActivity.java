package me.kwik.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NetworkPasswordActivity extends BaseActivity {

    private TextView hideShowTextView;
    private EditText mWifiPasswordEditText;
    private String mWifiNetworkSsid;
    private TextView mInstructionsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_password);
        mInstructionsTextView = (TextView)findViewById(R.id.network_password_activity_title_text_view);
        mWifiNetworkSsid = getIntent().getStringExtra("selectedWiFiSsid");
        mWifiPasswordEditText = (EditText)findViewById(R.id.network_password_activity_password_edit_text);
        mInstructionsTextView.setText("Enter the password for the\n" + mWifiNetworkSsid + " network");
        hideShowTextView = (TextView)findViewById(R.id.choose_network_hide_show_text_view);
        hideShowTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = hideShowTextView.getText().toString();
                if(status.equals("Hide")){
                    hideShowTextView.setText("Show");
                    mWifiPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    hideShowTextView.setText("Hide");
                    mWifiPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
        mActionBarTitle.setText("2 of 3");
    }

    @Override
    protected void clickNext() {
        if(isEditTextsEmpty(new EditText[]{mWifiPasswordEditText},new String[]{"please enter password"})){
            return;
        }
        super.clickNext();
        Intent i = new Intent(NetworkPasswordActivity.this,ButtonToApActivity.class);
        i.putExtra("selectedWiFiSsid",mWifiNetworkSsid);
        i.putExtra("password",mWifiPasswordEditText.getText().toString());
        startActivity(i);
    }
}
