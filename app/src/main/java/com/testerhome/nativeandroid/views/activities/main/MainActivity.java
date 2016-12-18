package com.testerhome.nativeandroid.views.activities.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.testerhome.nativeandroid.R;
import com.testerhome.nativeandroid.fragments.TopicListFragment;
import com.testerhome.nativeandroid.fragments.WebViewFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private Fragment topicFragment;
    private Fragment wikiFragment;
    private Fragment favoriteFragment;
    private Fragment notificationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fragment_container

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setDefaultFragment();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);

        switch (item.getItemId()) {
            case R.id.navigation_topic:
                if (topicFragment == null) {
                    topicFragment = new TopicListFragment();
                    fragmentTransaction.add(R.id.fragment_container, topicFragment);
                }
                fragmentTransaction.show(topicFragment);
                break;
            case R.id.navigation_wiki:
                if (wikiFragment == null) {
                    wikiFragment = new WebViewFragment();
                    fragmentTransaction.add(R.id.fragment_container, wikiFragment);
                }
                fragmentTransaction.show(wikiFragment);
                break;
            case R.id.navigation_favorite:
                break;
            case R.id.navigation_notification:
                break;
            default:
                break;
        }

        fragmentTransaction.commitNowAllowingStateLoss();
        return true;
    }

    private void setDefaultFragment() {
        topicFragment = new TopicListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, topicFragment)
                .commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (topicFragment != null) {
            fragmentTransaction.hide(topicFragment);
        }
        if (wikiFragment != null) {
            fragmentTransaction.hide(wikiFragment);
        }
        if (favoriteFragment != null) {
            fragmentTransaction.hide(favoriteFragment);
        }
        if (notificationFragment != null) {
            fragmentTransaction.hide(notificationFragment);
        }
    }
}
