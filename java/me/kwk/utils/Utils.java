package me.kwk.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

import me.kwik.app.BaseActivity;
import me.kwik.app.LoginActivity;
import me.kwik.app.R;
import me.kwik.app.VerifyPhoneActivity;
import me.kwik.bl.KwikMe;
import me.kwik.bl.KwikServerError;
import me.kwik.listeners.SendValidationCodeListener;

/**
 * Created by root on 25/01/16.
 */
public class Utils {


    private static PopupWindow popupWindow;
    private static String mPhonePrefix = "+972";


    public static EditText setOnTouchAndClickListener(EditText phoneNumberEditText, final Context context){
        final EditText t = phoneNumberEditText;
        t.setText(mPhonePrefix);
        Selection.setSelection(t.getText(), t.getText().length());
        t.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dismissPopup();
                    int i = t.getSelectionStart();
                    if (i <= 3 ) {
                        PopupWindow popUp = popupWindowsort(t, context);
                        popUp.showAsDropDown(v, 0, 0); // show popup like dropdown list

                    }
                }

            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopup();
                int i = t.getSelectionStart();
                if (i <= 3) {
                    PopupWindow popUp = popupWindowsort(t, context);
                    popUp.showAsDropDown(v, 0, 0); // show popup like dropdown list

                }
            }
        });

        t.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().contains(mPhonePrefix)) {
                    t.setText(mPhonePrefix);
                    Selection.setSelection(t.getText(), t.getText().length());

                }

            }
        });

        return t;
    }

    /**
     * show popup window method reuturn PopupWindow
     */
    private static PopupWindow popupWindowsort(final EditText t,Context context) {

        // initialize a pop up window type
        popupWindow = new PopupWindow(context);

        ArrayList<String> sortList = new ArrayList<String>();
        sortList.add("+972 ");
        sortList.add("+1   ");
        sortList.add("+2   ");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,
                sortList);
        // the drop down list is a list view
        ListView listViewSort = new ListView(context);

        // set our adapter and pass our pop up window contents
        listViewSort.setAdapter(adapter);

        // set on item selected
        listViewSort.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView parent, View view, int position, long id) {
                        if (position == 0) {
                            mPhonePrefix = "+972 ";
                        } else if (position == 1) {
                            //  reverseByName(products);
                            mPhonePrefix = "+1   ";
                        } else {
                            //   sortByPrice(products);
                            mPhonePrefix = "+2   ";

                        }
                        t.setText(mPhonePrefix);
                        Selection.setSelection(t.getText(), t.getText().length());
                        dismissPopup();
                    }
                }
        );

        // some other visual settings for popup window
        popupWindow.setFocusable(true);
        popupWindow.setWidth(250);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the listview as popup content
        popupWindow.setContentView(listViewSort);

        return popupWindow;
    }

    protected static void dismissPopup() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }



}
