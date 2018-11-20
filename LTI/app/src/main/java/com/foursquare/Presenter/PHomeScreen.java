package com.foursquare.Presenter;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.foursquare.Core.Constants;
import com.foursquare.Core.Utility;
import com.foursquare.Model.Search.VenueDTO;
import com.foursquare.Model.Search.VenueListDTO;
import com.foursquare.Model.Search.VenueResponseDTO;
import com.foursquare.Service.GPSManager;
import com.foursquare.Service.MSearchAsynchTask;

import java.util.ArrayList;
import java.util.List;

import static com.foursquare.Core.Constants.DEFAULT_LATTITUDE;
import static com.foursquare.Core.Constants.DEFAULT_LONGITUDE;
import static com.foursquare.Core.Constants.ERROR_FAILED_TO_GET_COORDINATES;
import static com.foursquare.Core.Constants.ERROR_GPS_IS_OFF;
import static com.foursquare.Core.Constants.ERROR_INCORRECT_REQUEST_ID;
import static com.foursquare.Core.Constants.ERROR_NO_DATA_FROM_SERVER;
import static com.foursquare.Core.Constants.ERROR_NO_RESPONSE_FROM_SERVER;
import static com.foursquare.Core.Constants.ERROR_NO_VENUES;
import static com.foursquare.Core.Constants.SUCCESS;

public class PHomeScreen
{
    private Context mContext;
    private Handler mHandler ;
    private MSearchAsynchTask mSearchAsynchTask;
    private Bundle mBundle = new Bundle();
    private ArrayList<VenueListDTO> mArrayListVenues = new ArrayList<>();
    private GPSManager mGPSManager;
    private int mResponseCode = ERROR_NO_RESPONSE_FROM_SERVER;

    private double mLatitude = DEFAULT_LATTITUDE;
    private double mLongitude = DEFAULT_LONGITUDE;

    private static String metres=" Meters";
    private static String ll="lat_long";

    public PHomeScreen(Context _mContext, Handler _mHandler, GPSManager _mGPSManager) {
        mContext = _mContext;
        mHandler = _mHandler;
        mSearchAsynchTask = new MSearchAsynchTask(mContext, this);
        mGPSManager = _mGPSManager;
    }


    public void pConductSearch(String mText) {
        if(mSearchAsynchTask != null) {
            mSearchAsynchTask.cancel(true);
        }
        mSearchAsynchTask = null;

        if(Utility.validateString(mText)) {
            mBundle.clear();
            mBundle.putString("query", mText);

            // Get the co-ordinates
            mLatitude = DEFAULT_LATTITUDE;
            mLongitude = DEFAULT_LONGITUDE;

            if ( mGPSManager.isGPSOnn() ) {
                mLatitude = mGPSManager.getLatitude();
                mLongitude = mGPSManager.getLongitude();

                if(mLatitude != DEFAULT_LATTITUDE && mLongitude != DEFAULT_LONGITUDE) {
                    // add the lat long
                    mBundle.putString(ll, ""+mLatitude + "," + mLongitude);
                    mSearchAsynchTask = new MSearchAsynchTask(mContext, this);
                    mSearchAsynchTask.execute(mBundle);
                }
                else
                {
                    mResponseCode = ERROR_FAILED_TO_GET_COORDINATES;
                    PUpdateView();
                    mGPSManager.getLocation();
                }
            }
            else {
                mResponseCode = ERROR_GPS_IS_OFF;
                PUpdateView();
            }
        }
        else {
            PUpdateView();
        }
    }

    private void PUpdateView() {
        mArrayListVenues.clear();

        Message mMessage = mHandler.obtainMessage();
        Bundle mBundle = new Bundle();
        mBundle.putInt(Constants.responseCode, mResponseCode);
        mBundle.putSerializable(Constants.venues, mArrayListVenues);
        mMessage.setData(mBundle);
        mHandler.sendMessage(mMessage);
    }

    public void PUpdateView(VenueResponseDTO mVenueResponse) {

        mResponseCode = ERROR_NO_RESPONSE_FROM_SERVER;
        mArrayListVenues.clear();
            if(mVenueResponse.getMeta() != null) {
                if(Utility.validateString(mVenueResponse.getMeta().getRequestId())) {
                    if(mVenueResponse.getResponse() != null) {
                        List<VenueDTO> mListVenues = mVenueResponse.getResponse().getVenues();
                        if(mListVenues != null && mListVenues.size() > 0) {
                            for (VenueDTO mVenue: mListVenues) {
                                VenueListDTO mVenueListDTO = new VenueListDTO();
                                mVenueListDTO.setName(mVenue.getName());
                                mVenueListDTO.setDistance(mVenue.getLocation().getDistance() +metres );
                                mVenueListDTO.setAddress(mVenue.getLocation().getAddress());
                                mArrayListVenues.add(mVenueListDTO);
                            }
                            mResponseCode = SUCCESS;
                        }
                        else {
                            mResponseCode = ERROR_NO_VENUES;
                        }
                    }
                    else {
                        mResponseCode = ERROR_NO_DATA_FROM_SERVER;
                    }
                }
                else {
                    mResponseCode = ERROR_INCORRECT_REQUEST_ID;
                }
            }
            else {
                mResponseCode = ERROR_NO_RESPONSE_FROM_SERVER;
            }

        Message mMessage = mHandler.obtainMessage();
        Bundle mBundle = new Bundle();
        mBundle.putInt(Constants.responseCode, mResponseCode);
        mBundle.putSerializable(Constants.venues, mArrayListVenues);
        mMessage.setData(mBundle);
        mHandler.sendMessage(mMessage);
    }

}
