package net.yams.mlbquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView image = (ImageView) findViewById(R.id.splash_image);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash);

        anim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation anim) {}
            @Override public void onAnimationRepeat(Animation anim) {}

            @Override public void onAnimationEnd(Animation anim)
            {
                image.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(SplashActivity.this, ChooseQuizActivity.class);
                startActivity(intent);

                finish();
            }
        });

        image.startAnimation(anim);
    }
}