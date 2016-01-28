package me.kwik.app;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NetworkPasswordActivity extends BaseActivity {

    TextView hideShowTextView;
    EditText mWifiPasswordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_password);
        mWifiPasswordEditText = (EditText)findViewById(R.id.network_password_activity_password_edit_text);
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


}
