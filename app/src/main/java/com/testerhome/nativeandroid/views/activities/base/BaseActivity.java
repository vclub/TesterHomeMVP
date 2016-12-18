package com.testerhome.nativeandroid.views.activities.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Bin Li on 2016/12/9.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);


    }
}
