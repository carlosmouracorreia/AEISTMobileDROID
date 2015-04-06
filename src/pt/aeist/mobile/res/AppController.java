package pt.aeist.mobile.res;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

import pt.aeist.mobile.ActInicio;
import pt.aeist.mobile.R;
import pt.aeist.mobile.Splash;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

	public static final String TAG = AppController.class.getSimpleName();

	private boolean appStarted;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private ProgressDialog pDialog;
	private static final String url = "http://mobile.aeist.pt/index.html";

	private static AppController mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue,
					new LruBitmapCache());
		}
		return this.mImageLoader;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
	
	public void initpDialog(Activity a) {
		pDialog = new ProgressDialog(a);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
	}
	public void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    public void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    /**
     * 
     * @param cont
     * @return 2 means ok, 1 means host, 0 means 3g or wifi
     * 
     */    
     public class CheckConnectivity extends AsyncTask<Context, Void, Integer> {
        protected Integer doInBackground(Context... cont) {
        	ConnectivityManager cm = (ConnectivityManager) cont[0].getSystemService(Context.CONNECTIVITY_SERVICE);
        	NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        	//final ActInicio yolo = (ActInicio) a ;
        	boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        	//work on this thread
        	if(isConnected) {
        		
        		//Check if can connect to AEISTMobile Server. 3 seconds treshold
        		try {
        	            URL url2 = new URL(url);
        	            final HttpURLConnection urlc = (HttpURLConnection) url2.openConnection();
        	            urlc.setRequestProperty("User-Agent", "Android Application");
        	            urlc.setRequestProperty("Connection", "close");
        	            urlc.setConnectTimeout(3 * 1000);
        	            urlc.connect();
        	            if (urlc.getResponseCode() == 200) {
        	                return 2;
        	            }
        	        }
        		 catch (java.net.ConnectException e) { return 1; } //host too
        		 catch (Throwable e) {
        	            return 1; // other exceptions
        	        }
        	        return 1;  //error resolving host
        	}
        	else {
        	return 0; //no wifi or 3g
        	}
        }
        protected void onPostExecute(Integer result) {
        	if(isAppStarted()) {
        		switch(result) {
		    	   case 0:
		    		   ActInicio.getInstance().getHandler().sendEmptyMessage(0);
		    		   break;
		    	   case 1:
		    		   ActInicio.getInstance().getHandler().sendEmptyMessage(1);
		    		   break;
		    	   case 2:
		    		   break;
        		}
        	} else {
	        	switch(result) {
		    	   case 0:
		    		   Splash.getInstance().getHandler().sendEmptyMessage(0);
		    		   break;
		    	   case 1:
		    		   Splash.getInstance().getHandler().sendEmptyMessage(1);
		    		   break;
		    	   case 2:
		    		   Splash.getInstance().startApp();
	        	}
        	}
    }
 }
    public void openDialog(Context a,int type) {
     	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(a);
     	 		final Activity _a = (Activity) a;
   			// set title
   			alertDialogBuilder.setTitle(R.string.app_name);
    
   			// set dialog message
   			
   				
   				if(type==0) { alertDialogBuilder.setMessage(R.string.dialogo_net);}
   			    if(type==1) {alertDialogBuilder.setMessage(R.string.dialogo_net_serv); }
   			 alertDialogBuilder.setCancelable(false)
   				.setPositiveButton("Sair",new DialogInterface.OnClickListener() {
   					public void onClick(DialogInterface dialog,int id) {
   						_a.finish();
   					}
   				  })
   				.setNegativeButton("Definições",new DialogInterface.OnClickListener() {
   		            public void onClick(DialogInterface dialog,int id) {
   		                Intent intent = new Intent(Settings.ACTION_SETTINGS);
   		                intent.addCategory(Intent.CATEGORY_LAUNCHER);           
   		                _a.startActivity(intent);
   		             
   		            }
   		        });
   				
    
   				AlertDialog alertDialog = alertDialogBuilder.create();
   				alertDialog.show();
   			}

	public boolean isAppStarted() {
		return appStarted;
	}

	public void setAppStarted(boolean appStarted) {
		this.appStarted = appStarted;
	}
}