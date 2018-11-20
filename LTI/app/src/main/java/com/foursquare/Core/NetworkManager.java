package com.foursquare.Core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NetworkManager {
	/*
	 * Checks whether Internet is present or not
	*/
	
	public static boolean checkInternet(Context mContext) {
		ConnectivityManager connectivity = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
            NetworkInfo[] info = getNetworkInfo(connectivity);
            if (info != null) {
                for (NetworkInfo mNetworkInfo:info)  {
                    if (mNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
	}

    /*
     * Returns the network info for all the active networks
     */

	private static NetworkInfo[] getNetworkInfo(ConnectivityManager connectivity){
	    Network[] networks = connectivity.getAllNetworks();
        NetworkInfo[] networkInfos=null;
	     if(networks!=null) {
             networkInfos= new NetworkInfo[networks.length];
          int count=0;
          for (Network mNetwork : networks) {
              networkInfos[count]=connectivity.getNetworkInfo(mNetwork);
              count++;
          }
      }
      return networkInfos;
    }

   /*
     * Returns the response in form of gson
     */
    public static InputStream inputStreamPostData(String url) {
        if(checkInternet(BaseApplication.getBaseApplicationContext())) {
            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
            url = urlBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().byteStream();
            }
            catch (IOException e) {
                Utility.log(e.getLocalizedMessage());
                return null;

            }finally{

            }
        }
        else {
            return null;
        }
    }
}
