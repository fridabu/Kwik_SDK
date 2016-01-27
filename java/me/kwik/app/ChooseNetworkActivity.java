package me.kwik.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.kwik.utils.Logger;

public class ChooseNetworkActivity extends BaseActivity {

    private TextView mTitle;
    private ListView mNetworksListView;
    private static long lastSelectedNetworkId = -1;
    private WifiManager mainWifi;
    private NetWorksWifiReceiver receiverWifi;
    private List<ScanResult> wifiList;
    private EditText mEnterManualyNetworkEditText;
    private ArrayAdapter<TextView> mWifiListAdapter;
    private String selectedNetworkName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_network);

        mEnterManualyNetworkEditText = (EditText)findViewById(R.id.choose_network_enter_manually_network_edit_text);
        mNetworksListView = (ListView)findViewById(R.id.choose_network_networks_list_view);
        mPrevTextView.setVisibility(View.INVISIBLE);
        mActionBarTitle.setText("1 of 3");

        mTitle = (TextView)findViewById(R.id.choose_network_title_text_view);
        mTitle.setText("Let's Setup Your Button " + getIntent().getStringExtra("name"));



        mNetworksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                unCheckAllWifiItems();
                checkWifiItem((TextView) mNetworksListView.getChildAt(position));
                lastSelectedNetworkId = position;

            }
        });


        mEnterManualyNetworkEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unCheckAllWifiItems();
            }
        });

        TextView scanAgain = (TextView)findViewById(R.id.choose_network_scan_again_text_view);
        scanAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanWifi();
            }
        });
    }



    private void checkWifiItem(TextView t){
        if(t!= null) {
            t.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.choose_network__checkmark, 0);
            t.setCompoundDrawablePadding(500);
        }
    }

    private void unCheckWifiItem(TextView t){
        if (t != null) {
            t.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.blank, 0);
        }
    }

    private void unCheckAllWifiItems(){
        TextView item;
        for (int position=0; position<mNetworksListView.getCount(); position++){
            item = (TextView)mNetworksListView.getChildAt(position);
            unCheckWifiItem(item);
        }
        lastSelectedNetworkId = -1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        startScanWifi();

    }

    private void startScanWifi() {
        showProgressBar();

        mNetworksListView.setAdapter(new ArrayAdapter<String>(ChooseNetworkActivity.this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>()));
        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (mainWifi.isWifiEnabled() == false){
            // If wifi disabled then enable it
            mainWifi.setWifiEnabled(true);
        }
        receiverWifi = new NetWorksWifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();
    }

    protected void onPause() {
        try {
            unregisterReceiver(receiverWifi);
        }catch(Exception e){
            //Do nothing
        }
        super.onPause();
    }

    class NetWorksWifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {
            ArrayList<String> listItems=new ArrayList<String>();

            mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

            //Comparator to sort wifi list
            Comparator<ScanResult> comparator = new Comparator<ScanResult>() {
                @Override
                public int compare(ScanResult lhs, ScanResult rhs) {
                    return (lhs.level >= rhs.level ? -1 : (lhs.level==rhs.level ? 0 : 1));
                }
            };

            wifiList = mainWifi.getScanResults();
            Collections.sort(wifiList, comparator);

            for(ScanResult s:wifiList){
                listItems.add(s.SSID);
            }


            mWifiListAdapter =new ArrayAdapter(ChooseNetworkActivity.this,
                    android.R.layout.simple_list_item_1,
                    listItems);

            mNetworksListView.setAdapter(mWifiListAdapter);
            hideProgressBar();
            mWifiListAdapter.notifyDataSetChanged();
            c.unregisterReceiver(this);
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void clickNext() {
        super.clickNext();
        Intent i = new Intent(ChooseNetworkActivity.this,NetworkPasswordActivity.class);
        i.putExtra("selectedWiFiSsid",selectedNetworkName);
        startActivity(i);
    }
}
