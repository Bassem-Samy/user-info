package com.concept.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.concept.user.info.UserInfoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFragment();
    }

    private void initializeFragment() {
        if (getSupportFragmentManager().findFragmentByTag(UserInfoFragment.TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frm_container, UserInfoFragment.newInstance(), UserInfoFragment.TAG)
                    .commit();
        }
    }

}
