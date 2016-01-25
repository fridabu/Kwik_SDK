package me.kwik.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class BaseActivity extends AppCompatActivity{

    protected TextView mActionBarTitle;
    protected TextView mPrevTextView;
    protected TextView mNextTextView;
    protected ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        actionBar.setCustomView(R.layout.custom_action_bar);



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

        //Action bar - click next
        mNextTextView = (TextView)findViewById(R.id.base_activity_action_bar_next_text_view);
        mNextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNext();
            }
        });
    }

    protected void clickNext() {
    }

    /**
     * Show progress bar
     */
    protected void showProgressBar(){
        mProgressBar = (ProgressBar)findViewById(R.id.base_activity_progressBar);
        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Hide progress bar
     */
    protected void hideProgressBar() {
        mProgressBar = (ProgressBar) findViewById(R.id.base_activity_progressBar);
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * validate if all input edit texts if any is empty
     * @param fields : edit texts array
     * @param errors : errors array for each edit text
     * @return true if any of the edit texts is empty
     */
    protected boolean isEditTextsEmpty(EditText[] fields, String[] errors){
        boolean empty = false;
        for(int i=0; i<fields.length;i++){
            EditText currentField = fields[i];
            if(currentField.getText() == null || currentField.getText().toString().trim().length() <=0){
                currentField.setError(errors[i]);
                empty = true;
            }
        }

        return empty;
    }

    /**
     * Show error dialog with one button
     * @param header error header
     * @param message error message
     */
    protected void showOneButtonErrorDialog(String header,String message){
        AlertDialog alertDialog = new AlertDialog.Builder(BaseActivity.this).create();
        alertDialog.setTitle(header);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /**
     * Show error dialog with two buttons
     * @param header
     * @param message
     * @param okClicked
     * @param cancelClicked
     */
    protected void showTwoButtonErrorDialog(String header,String message,
                                            String okString,
                                            String cancelString,
                                            DialogInterface.OnClickListener okClicked,
                                            DialogInterface.OnClickListener cancelClicked){
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);

        if (header != null) builder.setTitle(header);

        builder.setMessage(message);
        builder.setPositiveButton(okString, okClicked);
        builder.setNegativeButton(cancelString, cancelClicked);
        builder.show();
    }
}
