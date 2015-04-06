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
	                	 
	                	 int msg = AppController.getInstance().networkStatus(getBaseContext());
				    	   switch(msg) {
				    	   case 0:
				    		   mHandler.sendEmptyMessage(0);
				    		   this.interrupt();
				    		   break;
				    	   case 1:
				    		   mHandler.sendEmptyMessage(1);
				    		   this.interrupt();
				    		   break;
				    	   case 2:
				    		   startActivity(new Intent("pt.aeist.mobile.START"));
			                   finish();
				    		  
				    	   }

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
        setContentView(R.layout.splash);
		 logoTimer.start();
        }
	 
	protected void onResume()
	    {
		 super.onResume();
		 if(firstRun) {
			 firstRun = false;
		 } else {
			 logoTimer.start();
		 }
	   }
	
}
