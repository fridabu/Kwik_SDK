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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.kwik.utils.Logger;
import me.kwik.wifi.WifiReceiver;

public class ChooseNetworkActivity extends BaseActivity {

    TextView title;
    ListView mNetworksListView;
    static int lastSelectedNetwork;
    WifiManager mainWifi;
    NetWorksWifiReceiver receiverWifi;
    List<ScanResult> wifiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_network);

        mNetworksListView = (ListView)findViewById(R.id.choose_network_networks_list_view);


        mPrevTextView.setVisibility(View.INVISIBLE);
        mActionBarTitle.setText("1 of 3");

        title = (TextView)findViewById(R.id.choose_network_title_text_view);
        title.setText("Let's Setup Your Button " + getIntent().getStringExtra("name"));


        mNetworksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView lastSelectedNework = (TextView) parent.getChildAt(lastSelectedNetwork);
                lastSelectedNework.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.blank, 0);
                TextView t = (TextView) view;
                t.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.choose_network__checkmark, 0);
                lastSelectedNetwork = position;
                t.setCompoundDrawablePadding(500);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (mainWifi.isWifiEnabled() == false)
        {
            // If wifi disabled then enable it
            mainWifi.setWifiEnabled(true);
        }
        receiverWifi = new NetWorksWifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();
       // mainText.setText("Starting Scan...");

    }
    protected void onPause() {
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    class NetWorksWifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {
            ArrayList<String> listItems=new ArrayList<String>();

            mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            wifiList = mainWifi.getScanResults();
            for(ScanResult s:wifiList){
                listItems.add(s.SSID);
            }

            ArrayAdapter<String> adapter;
            adapter=new ArrayAdapter<String>(ChooseNetworkActivity.this,
                    android.R.layout.simple_list_item_1,
                    listItems);
            mNetworksListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

    }
}
