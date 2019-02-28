package com.example.chatspy.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.chatspy.Adapters.PagerAdapter;
import com.example.chatspy.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
                    PagerAdapter myPagerAdapter = new PagerAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(myPagerAdapter);
                    TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    Toast.makeText(BaseActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
