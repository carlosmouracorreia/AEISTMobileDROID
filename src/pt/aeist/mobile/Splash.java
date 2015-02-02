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
                	 sleep(2000);
                     if (!AppController.getInstance().networkStatus(getBaseContext())) 
                    {               
                         mHandler.sendEmptyMessage(0);
                    } else 
                    { 
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
         
        logoTimer.start();
}
	
}
