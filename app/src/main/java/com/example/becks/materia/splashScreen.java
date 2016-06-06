package com.example.becks.materia;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by becks on 5/13/16.
 */
public class splashScreen extends AppCompatActivity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.TRANSPARENT);
    }
    /** Called when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(splashScreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    splashScreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    splashScreen.this.finish();
                }

            }
        };
        splashTread.start();

    }

}
