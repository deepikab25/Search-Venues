package com.foursquare.Core;

import android.util.Log;

import com.foursquare.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;
import static com.foursquare.Core.BaseApplication.getBaseApplicationContext;

/**
 * Contains all the frequently used call across the app
 */

public class Utility {

    /*
        Description
            Logs the messages. Can be viewed in Logcat.
        Parameters
            mMessage to be logged
        Return
            Nothing
     */
    public static void log(String mMessage)
    {
        Log.d(TAG, mMessage);
    }

    /*
        Description
            Fetches the Client Id from the strings.xml
        Parameters
            None
        Return
            Client Id
     */
    public static String getClientId() {
        return getBaseApplicationContext().getResources().getString(R.string.client_id);
    }

    /*
        Description
            Fetches the Client Secret from the strings.xml
        Parameters
            None
        Return
            Client Secret
     */
    public static String getClientSecret() {
        return getBaseApplicationContext().getResources().getString(R.string.client_secret);
    }

    /*
        Description
            Fetches the tag name from the strings.xml
        Parameters
            None
        Return
            Tag name
     */
    public static String getTagName() {
        return getBaseApplicationContext().getClass().getName();
    }

    /*
        Description
            Fetches the tag name from the strings.xml
        Parameters
            None
        Return
            Tag name
     */
    public static String getPathEndpointSearch() {
        return getBaseApplicationContext().getResources().getString(R.string.endpoint_search_path);
    }

    public static String getDisplayText(int id) {
        return getBaseApplicationContext().getResources().getString(id);
    }

    public static String getVersion() {
        Date mDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String mVersionDate = formatter.format(mDate);
        Utility.log(" Version logged " + mVersionDate);
        return mVersionDate;
    }

    /*
	 * returns true, if the validation passes
	*/
    public static boolean validateString(String mString) {
        boolean mResultFlag = true;

        try {
            mString = mString.trim();
            if(mString == null || mString.equals(null) || mString.equals("") || mString == "") {
                mResultFlag = false;
            }
        }
        catch (Exception e) {
            Utility.log(e.getLocalizedMessage());
            mResultFlag = false;
        }

        return mResultFlag;
    }
}
