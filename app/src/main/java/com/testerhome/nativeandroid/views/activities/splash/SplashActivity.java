package com.testerhome.nativeandroid.views.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.testerhome.nativeandroid.R;
import com.testerhome.nativeandroid.views.activities.base.BaseActivity;
import com.testerhome.nativeandroid.views.activities.main.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        findViewById(R.id.btn_dark).setOnClickListener(view -> {

            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            recreate();
        });

        findViewById(R.id.btn_light).setOnClickListener(view -> {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            recreate();
        });

        findViewById(R.id.btn_next).setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        });
    }
}
