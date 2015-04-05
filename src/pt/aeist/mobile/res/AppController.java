package pt.aeist.mobile.res;

import org.json.JSONArray;

import pt.aeist.mobile.ActInicio;
import pt.aeist.mobile.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private ProgressDialog pDialog;
	private boolean efConnected;
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
    
    public void networkStatus(Context cont,Activity a) {
    	ConnectivityManager cm = (ConnectivityManager) cont.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    	final ActInicio yolo = (ActInicio) a ;
    	boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    	//work on this thread
    	
    	if(isConnected) {
    		AppController.getInstance().showpDialog();
    		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
    	            new Response.Listener<String>() {
  	                    @Override
  	                    public void onResponse(String response) {
  	                    	AppController.getInstance().hidepDialog();
  	                    	yolo.setNetworkStatus(true);
  	                    	yolo.set_connectivityChecked(true);
  	                    }
    	}
    		  , new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                      AppController.getInstance().hidepDialog();
                      yolo.setNetworkStatus(false);
                      yolo.set_connectivityChecked(true);
                  }
              });
      AppController.getInstance().addToRequestQueue(stringRequest);
    	 }
    	else {
    	
    	yolo.setNetworkStatus(false);
    	yolo.set_connectivityChecked(true);
    	}
}
    
    public void openDialog(Context a) {
     	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(a);
     	 		final Activity _a = (Activity) a;
   			// set title
   			alertDialogBuilder.setTitle(R.string.app_name);
    
   			// set dialog message
   			alertDialogBuilder
   				.setMessage(R.string.dialogo_net)
   				.setCancelable(false)
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
   		              _a.finish();
   		            }
   		        });
   				
    
   				AlertDialog alertDialog = alertDialogBuilder.create();
    
   				alertDialog.show();
   			}
}