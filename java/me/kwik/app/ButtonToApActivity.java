package me.kwik.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.kwik.bl.KwikMe;
import me.kwik.utils.Logger;
import me.kwik.wifi.TeachWifiCredentials;

public class ButtonToApActivity extends BaseActivity {

    TextView mSearchButtonTextView;
    private String TAG = ButtonToApActivity.class.getSimpleName();
    private TeachButtonWifiReceiver mTeachButtonWifiReceiver;
    private AnimationsContainer.FramesSequenceAnimation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_to_ap);

        mSearchButtonTextView = (TextView)findViewById(R.id.button_to_ap_activity_looking_for_button_text_view);
        mActionBarTitle.setText("3 of 3");
        mNextTextView.setVisibility(View.INVISIBLE);

        mTeachButtonWifiReceiver = new TeachButtonWifiReceiver();
        ImageView imageView = (ImageView) findViewById(R.id.button_to_ap_activity_image_view);
        mAnimation = AnimationsContainer.getInstance().createButtonToApAnim(imageView);

        showProgressBar();

        String ssid = getIntent().getStringExtra("selectedWiFiSsid");
        String password = getIntent().getStringExtra("password");

        TeachWifiCredentials t = new TeachWifiCredentials(this, ssid , password);
        t.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAnimation.start();
        registerReceiver(mTeachButtonWifiReceiver, new IntentFilter(KwikMe.WIFI_TEACH_COMPLETED_INTENT));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAnimation.stop();
        try{
            unregisterReceiver(mTeachButtonWifiReceiver);
        }catch(Exception e){
            //Do nothing
        }
    }

    private void showWaitingMessage(boolean show){
        if(show){
            mSearchButtonTextView.setVisibility(View.VISIBLE);
        }else{
            mSearchButtonTextView.setVisibility(View.INVISIBLE);
        }
    }


    class TeachButtonWifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {
            Logger.d(TAG,"Teach wifi response = %s",intent.getBooleanExtra(KwikMe.WIFI_TEACH_COMPLETED_INTENT_SUCCESS_EXTRA,false));
        }
    }
}
