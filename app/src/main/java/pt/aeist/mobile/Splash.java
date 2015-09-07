package pt.aeist.mobile;

import pt.aeist.mobile.res.AppController;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


@SuppressLint("HandlerLeak")
public class Splash extends Activity {
	private boolean firstRun = true;
	private static Splash mInstance;

	public static synchronized Splash getInstance() {
		return mInstance;
	}
	
	public Handler getHandler() {
		return mHandler;
	}
	
   public void startApp() {
	 startActivity(new Intent("pt.aeist.mobile.START").putExtra("bbq",false));
     finish();
   }

	  Handler mHandler = new Handler()
		 {
		     public void handleMessage(Message msg)
		     {
		    		    switch (msg.what) {
		    		        case 1:
		    		        	AppController.getInstance().openDialog(Splash.this,1);   		        	
		    		            break;
		    		        case 0:
		    		        	AppController.getInstance().openDialog(Splash.this,0);
		    		            break;
		    		    }
		    	 
		     }
		     
		 };
		 Thread logoTimer = new Thread() {
	            public void run(){
	                try{
	                	 sleep(1000);
	 	            	AppController.getInstance().new CheckConnectivity().execute(getBaseContext());


	                    }
	                catch (InterruptedException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        };
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
		AppController.getInstance().setAppStarted(false);
        mInstance = this;
        setContentView(R.layout.splash);
		 logoTimer.start();
        }
	 
	protected void onResume()
	    {
		 super.onResume();
		 if(firstRun) {
			 firstRun = false;
		 } else {
			 logoTimer.run();
		 }
	   }
	
}
