package me.kwik.app;

import android.os.Bundle;
import android.widget.ImageView;

public class GoodJobActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_job);
        getSupportActionBar().hide();

        ImageView imageView = (ImageView) findViewById(R.id.good_job_animation_image_view);
        AnimationsContainer.FramesSequenceAnimation anim = AnimationsContainer.getInstance().createGoodJobAnim(imageView);
        anim.start();
    }
}
