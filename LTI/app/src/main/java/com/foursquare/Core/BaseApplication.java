package com.foursquare.Core;

import android.app.Application;
import android.content.Context;

import com.foursquare.Service.GPSManager;

/**
 * Base application
 */

public class BaseApplication extends Application {

    // Tag name used for logging
    public static String TAG = null;

    // Application context
    static Context mContext = null;


    static GPSManager mGPSManager = null;


    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();

        if(mContext == null) {
            mContext = BaseApplication.this;
        }

        TAG = Utility.getTagName();

        mGPSManager = new GPSManager(mContext);

        getDatabaseManager().buildSnapshot();

        Utility.log("Application created");

    }


    public static DatabaseManager getDatabaseManager() {
        return DatabaseManager.getInstance(mContext);
    }


    public static GPSManager getGPSManager() {
        if(mGPSManager == null) {
            mGPSManager = new GPSManager(mContext);
        }
        return mGPSManager;
    }

    /*
	 * Returns the base application context
	 */
    public static Context getBaseApplicationContext() {
        return mContext;
    }

}
