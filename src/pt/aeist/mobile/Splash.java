package pt.aeist.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;


public class Splash extends Activity {
	
	 public void openDialog() {
    	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
  				this);
   
  			// set title
  			alertDialogBuilder.setTitle(R.string.app_name);
   
  			// set dialog message
  			alertDialogBuilder
  				.setMessage(R.string.dialogo_net)
  				.setCancelable(false)
  				.setPositiveButton("Sair",new DialogInterface.OnClickListener() {
  					public void onClick(DialogInterface dialog,int id) {
  						Splash.this.finish();
  					}
  				  })
  				.setNegativeButton("Definições",new DialogInterface.OnClickListener() {
  		            public void onClick(DialogInterface dialog,int id) {
  		                Intent intent = new Intent(Settings.ACTION_SETTINGS);
  		                intent.addCategory(Intent.CATEGORY_LAUNCHER);           
  		                startActivity(intent);
  		                finish();
  		            }
  		        });
  				
  				AlertDialog alertDialog = alertDialogBuilder.create();
   
  				alertDialog.show();
  			}
	
	 Handler mHandler = new Handler()
	 {
	     public void handleMessage(Message msg)
	     {
	        openDialog();//Display Alert
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
                     ConnectivityManager cm =
                         (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                     NetworkInfo netInfo = cm.getActiveNetworkInfo();

                     if (netInfo == null || !netInfo.isConnectedOrConnecting()) 
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
