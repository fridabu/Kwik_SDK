package me.kwik.app;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends BaseActivity {

    private TextView mTermsAndConditionsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();

        mTermsAndConditionsTextView = (TextView)findViewById(R.id.start_activity_terms_and_conditions_text_view);
        mTermsAndConditionsTextView.setText(Html.fromHtml(getString(R.string.start_activity_terms_and_conditions)));

        ImageView imageView = (ImageView) findViewById(R.id.start_activity_animation_image_view);
        AnimationsContainer.FramesSequenceAnimation anim = AnimationsContainer.getInstance().createSplashAnim(imageView);
        anim.start();

    }

    public void signupClicked(View sigupButton){
        Intent i = new Intent(StartActivity.this,SignupActivity.class);
        startActivity(i);

    }


    public void loginClicked(View loginButton){
        Intent i = new Intent(StartActivity.this,LoginActivity.class);
        startActivity(i);
    }



}
