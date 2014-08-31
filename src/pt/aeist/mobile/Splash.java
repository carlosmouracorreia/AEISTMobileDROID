package pt.aeist.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Splash extends Activity {
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     
        setContentView(R.layout.splash);
        

        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 2000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                   startActivity(new Intent("pt.aeist.mobile.START"));
                } 
                 
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 
                finally{
                    finish();
                }
            }
        };
         
        logoTimer.start();
}
	
	
}
