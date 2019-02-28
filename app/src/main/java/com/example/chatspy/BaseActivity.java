package com.example.chatspy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
  /*      Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
        int i=0;
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                Log.d("here",cursor.getColumnName(2)+" "+cursor.getString(2)+" "+2);
                Log.d("here",cursor.getColumnName(12)+" "+cursor.getString(12)+" "+12);
                Log.d("here",cursor.getColumnName(9)+" "+cursor.getString(9)+" "+9);
                i++;
                // use msgData
            } while (cursor.moveToNext() && i<20);
        } else {
            // empty box, no SMS
        }*/
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
