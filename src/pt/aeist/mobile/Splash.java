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
	private boolean _networkStatusOn;
	private boolean _connectivityChecked;
	private Activity Sp = this;
	 Handler mHandler = new Handler()
	 {
	     public void handleMessage(Message msg)
	     {
	    	 AppController.getInstance().openDialog(Splash.this);
	     }
	 };
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     
        setContentView(R.layout.splash);
        

        Thread logoTimer = new Thread() {
            public void run(){
                try{
                	 sleep(1000);
                	/* AppController.getInstance().networkStatus(getBaseContext(),Sp);
                	  while(!is_connectivityChecked()) {}
           	    	   if(!getNetworkStatus()) {

                         mHandler.sendEmptyMessage(0);
           	    	   }
                     else 
                    { */
                    startActivity(new Intent("pt.aeist.mobile.START"));
                    finish();
                    
                   // }
                } 
                 
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
         
        logoTimer.start();
}
	
	public void setNetworkStatus(boolean networkStatusOn) {
		_networkStatusOn = networkStatusOn;
	}
	
	public boolean getNetworkStatus() {
		return _networkStatusOn;
	}
	
	public boolean is_connectivityChecked() {
		return _connectivityChecked;
	}

	public void set_connectivityChecked(boolean _connectivityChecked) {
		this._connectivityChecked = _connectivityChecked;
	}
	
}
