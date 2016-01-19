package me.kwik.app;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.TextView;


public class BaseActivity extends AppCompatActivity{

    protected TextView mActionBarTitle;
    protected TextView mPrevTextView;
    protected TextView mNextTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar);

        setContentView(R.layout.activity_base);
        mActionBarTitle = (TextView)findViewById(R.id.base_activity_action_bar_title);

        //Change the action bar text font
        SpannableString s = new SpannableString(mActionBarTitle.getText().toString());
        s.setSpan(new TypefaceSpan("OpenSans.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        actionBar.setTitle(s);

        //Action bar - click back
        mPrevTextView = (TextView)findViewById(R.id.base_activity_action_bar_prev_text_view);
        mPrevTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.finish();
            }
        });
    }

}
