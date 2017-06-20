package com.a11group.microfinanceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.a11group.microfinanceapp.Activities.LoginActivity;
import com.a11group.microfinanceapp.R;

public class SplashActivity extends AppCompatActivity {

    private static int TEMPO_SPLASH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
                finish();
            }
        }, TEMPO_SPLASH);

    }
}
