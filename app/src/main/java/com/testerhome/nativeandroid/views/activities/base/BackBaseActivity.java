package com.testerhome.nativeandroid.views.activities.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.testerhome.nativeandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by Bin Li on 2016/12/18.
 */

public abstract class BackBaseActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        setupToolbar();
    }

    protected void setupToolbar() {
        checkNotNull(mToolbar);
        setSupportActionBar(mToolbar);
    }
}
