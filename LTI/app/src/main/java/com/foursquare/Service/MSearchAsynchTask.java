package com.foursquare.Service;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.foursquare.Core.NetworkManager;
import com.foursquare.Core.Utility;
import com.foursquare.Model.Search.VenueResponseDTO;
import com.foursquare.Presenter.PHomeScreen;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;


public class MSearchAsynchTask extends AsyncTask<Bundle, Integer, Boolean> {

    private Context mContext;
	String mServerPath = Utility.getPathEndpointSearch();
    private PHomeScreen mPHomeScreen;


	public MSearchAsynchTask(Context _mContext, PHomeScreen _mPHomeScreen)
	{
	    mContext = _mContext;
        mPHomeScreen = _mPHomeScreen;
        mServerPath += "?v=" + Utility.getVersion() + "&client_id=" + Utility.getClientId()
                + "&client_secret=" + Utility.getClientSecret();

	}
	
	// actual network process takes place
	protected Boolean doInBackground(Bundle... arg0) 
	{
        VenueResponseDTO mVenueResponseDTO = null;
		try
        {
            if(NetworkManager.checkInternet(mContext))
            {
                 if(arg0 != null && arg0.length>0)
                {
                    Bundle mBundle = arg0[0];
                    if((mBundle != null) && (mBundle.size() > 0))
                    {
                        mServerPath += "&ll=" + mBundle.getString("lat_long");
                        mServerPath += "&query=" + URLEncoder.encode(mBundle.getString("query"),"UTF-8");
                        Log.d("lat_long_path", mServerPath);
                    }
                }

                InputStream mInputStream = NetworkManager.inputStreamPostData(mServerPath);

                if(mInputStream != null)
                {
                    Gson gson = new Gson();
                    Reader reader = new InputStreamReader(mInputStream);
                    mVenueResponseDTO = gson.fromJson(reader, VenueResponseDTO.class);
                }
            }
        }
        catch (Exception e)
        {
            Utility.log(e.getLocalizedMessage());
            mVenueResponseDTO = null;
        }
        if(mPHomeScreen != null)
        {
            mPHomeScreen.PUpdateView(mVenueResponseDTO);
        }
        return true;
	}

}
