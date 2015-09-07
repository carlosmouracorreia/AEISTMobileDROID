package pt.aeist.mobile;



import pt.aeist.mobile.res.AppController;
import pt.aeist.mobile.res.TabsPagerAdapter;
import pt.aeist.mobile.res.AppController.CheckConnectivity;
import pt.aeist.mobile.services.QuickstartPreferences;
import pt.aeist.mobile.services.RegistrationIntentService;
import pt.aeist.mobile.servicos.DespFrag;
import pt.aeist.mobile.servicos.RecFrag;
import pt.aeist.mobile.servicos.SFFrag;
import pt.aeist.mobile.servicos.ServFrag;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


public class ActInicio extends ActionBarActivity implements
		ActionBar.TabListener {
	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private static ActInicio mInstance;
	private String[] tabs = { "Eventos", "Serviços", "A AEIST" };
	private boolean firstRun = true;
	private Menu menu;
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	public static final String TAG = "AEISTMobile";
	private boolean toBbq = false;
	private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProgressDialog pDialog;


	public static synchronized ActInicio getInstance() {
		return mInstance;
	}


	public boolean toBbq() {
		return toBbq;
	}

	public void setBbq(boolean val) {
		toBbq = val;
	}


	public Menu getMenu() {
		return menu;
	}
	
    Handler mHandler = new Handler()
		 {
	    public void handleMessage(Message msg)
	     {
		    switch (msg.what) {
		        case 1:
		        	AppController.getInstance().openDialog(ActInicio.this,1);
		            break;
		        case 0:
		        	AppController.getInstance().openDialog(ActInicio.this,0);
		            break;
		    }
	     }
		     
		 }; 
		 
		 public Handler getHandler() {
				return mHandler;
			}
		 
		 Thread logoTimer = new Thread() {
             public void run(){
          	  AppController.getInstance().new CheckConnectivity().execute(getBaseContext());
             }
         };
	
	@Override
	public void onBackPressed() {
        if(viewPager.getCurrentItem() == 1) {
            if (mAdapter.getItem(1) instanceof SFFrag) {
                ((SFFrag) mAdapter.getItem(1)).backPressed();
            }
            else if (mAdapter.getItem(1) instanceof DespFrag) {
                ((DespFrag) mAdapter.getItem(1)).backPressed();
            }
            else if (mAdapter.getItem(1) instanceof RecFrag) {
                ((RecFrag) mAdapter.getItem(1)).backPressed();
            }
            else {
                finish();
            }
        }
        else {
            finish();
        }
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mInstance = this;
		AppController.getInstance().setAppStarted(true);
		setContentView(R.layout.activity_act_inicio);

		mRegistrationBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				SharedPreferences sharedPreferences =
						PreferenceManager.getDefaultSharedPreferences(context);
				boolean sentToken = sharedPreferences
						.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
				if (sentToken) {
					Log.d(TAG,"TOKEN SENT");
				} else {
					Log.d(TAG, "TOKEN NOT SENT");
				}
			}
		};

		if (checkPlayServices()) {
			// Start IntentService to register this application with GCM.
			Intent intent = new Intent(this, RegistrationIntentService.class);
			startService(intent);
		}


		//inicializacao
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getSupportActionBar();

		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setLogo(R.drawable.ic_launcher);
		getSupportActionBar().setDisplayUseLogoEnabled(true);

		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


		
		//Adicionar Tabs
		for(String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}


		 viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			 
	            @Override
	            public void onPageSelected(int position) {
	                // on changing the page
	                // make respected tab selected
	                actionBar.setSelectedNavigationItem(position);
	            }
	 
	            @Override
	            public void onPageScrolled(int arg0, float arg1, int arg2) {
	            }
	 
	            @Override
	            public void onPageScrollStateChanged(int arg0) {
	            }
	        });
		if(savedInstanceState==null) {
			Bundle extras = getIntent().getExtras();
			if(extras != null) {
				boolean bbq = extras.getBoolean("bbq");
				if(bbq) {
						toBbq = true;
						viewPager.setCurrentItem(1);
				}
			}
		}
	}
	    @Override
	    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	    }
	 
	    @Override
	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	        // on tab selected
	        // show respected fragment view
	           logoTimer.run();
	        viewPager.setCurrentItem(tab.getPosition());
	    }
	 
	    @Override
	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	    }

	    protected void onResume()
	    {
	       super.onResume();
	       if(firstRun==true) {
	    	   firstRun = false;
	       }
	       else {
	           logoTimer.run(); 
	       }
			LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
					new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));

	       
	    }
	       
	    
	    public ViewPager getPager() {
	    	return viewPager;
	    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.act_inicio, menu);
		this.menu = menu;

		menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				ActInicio.getInstance().onBackPressed();
				return true;
			}
		});
		if(toBbq) {
			menu.getItem(0).setVisible(true);
			toBbq=false;
		}
		return super.onCreateOptionsMenu(menu);
	}



	@Override
	protected void onPause() {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
		super.onPause();
	}


	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.i(TAG, "This device is not supported.");
				finish();
			}
			return false;
		}
		return true;
	}

	public void showpDialog() {
		pDialog = new ProgressDialog(this);
        pDialog.setMessage("A descarregar conteudos...");
        pDialog.setCancelable(false);
         if (!pDialog.isShowing())
            pDialog.show();
	}


	public void hidepDialog() {
        if(pDialog.isShowing())
            pDialog.dismiss();
	}
}
