package com.testerhome.nativeandroid.views.activities.base;

import android.view.MenuItem;

import com.testerhome.nativeandroid.R;

/**
 * Created by Bin Li on 2016/12/18.
 */

public abstract class BackBaseActivity extends BaseActivity {

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        if (toolbar != null && getSupportActionBar() != null) {
            toolbar.setNavigationIcon(R.drawable.icon_back);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
