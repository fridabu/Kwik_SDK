package me.kwik.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ButtonToApActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_to_ap);
        mActionBarTitle.setText("3 of 3");

        ImageView imageView = (ImageView) findViewById(R.id.button_to_ap_activity_image_view);
        AnimationsContainer.FramesSequenceAnimation anim = AnimationsContainer.getInstance().createButtonToApAnim(imageView);
        anim.start();

        showProgressBar();
    }
}
