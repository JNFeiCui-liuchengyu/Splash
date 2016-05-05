package edu.feicui.com.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class logoActivity extends AppCompatActivity  {

    private ImageView img_logo;
    private Animation manimation;


    private Animation.AnimationListener animationListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent=new Intent(logoActivity.this,
                    TelmsgActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        img_logo = (ImageView) findViewById(R.id.iv_logo);
        manimation = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
        manimation.setAnimationListener(animationListener);
        img_logo.startAnimation(manimation);
    }
}
