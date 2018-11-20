package com.foursquare.View.Screens;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;

import com.foursquare.Core.BaseApplication;
import com.foursquare.Core.Utility;
import com.foursquare.R;
import com.foursquare.Service.GPSManager;

public class SplashScreenActivity extends AppCompatActivity {
    // defines the splash period
    final int PERIOD = 3000;

    private static final int REQUEST_CODE_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }

    @Override
    protected void onResume() {
        super.onResume();

        String[] mPermission = {
                Manifest.permission.ACCESS_FINE_LOCATION};
        try {
            int verCode = android.os.Build.VERSION.SDK_INT;
            if(verCode <= 22) {
                // init app
                Stall();
            }
            else {
                // show request
                if (ActivityCompat.checkSelfPermission(this, mPermission[0])
                        != MockPackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, mPermission, REQUEST_CODE_PERMISSION);
                    // If any permission above not allowed by user, this condition will execute every time, else your else part will work
                }
                else {
                    Stall();
                }
            }

        }
        catch (Exception e) {
            Utility.log(e.getLocalizedMessage());
        }
    }

    void Stall() {
        GPSManager mGPManager = BaseApplication.getGPSManager();
        if(mGPManager.isGPSOnn()) {
            mGPManager.getLocation();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchHomeScreen();
            }
        }, PERIOD);
    }

    void launchHomeScreen() {
        Intent mIntent = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mIntent);
        finish();
    }

}
