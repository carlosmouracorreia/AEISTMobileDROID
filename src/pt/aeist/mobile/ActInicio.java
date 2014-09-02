package pt.aeist.mobile;



import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ViewPager;


public class ActInicio extends ActionBarActivity implements
		ActionBar.TabListener {
	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	
	private String[] tabs = { "Eventos", "Ementas", "�til" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_inicio);
		
		//inicializacao
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getSupportActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
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
	}
		 
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
      						ActInicio.this.finish();
      					}
      				  })
      				.setNegativeButton("Defini��es",new DialogInterface.OnClickListener() {
      					public void onClick(DialogInterface dialog,int id) {
      						Intent intent = new Intent(Settings.ACTION_SETTINGS);
      			            intent.addCategory(Intent.CATEGORY_LAUNCHER);           
      			            startActivity(intent);
      					}
      				});
       
      				AlertDialog alertDialog = alertDialogBuilder.create();
       
      				alertDialog.show();
      			}
	    
	
	
	 
	    @Override
	    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	    }
	 
	    @Override
	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	        // on tab selected
	        // show respected fragment view
	        viewPager.setCurrentItem(tab.getPosition());
	    }
	 
	    @Override
	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	    }

	    protected void onResume()
	    {
	       super.onResume();
	       ConnectivityManager cm =
	                 (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	             NetworkInfo netInfo = cm.getActiveNetworkInfo();
	       if (netInfo == null || !netInfo.isConnectedOrConnecting()) {
	    	  //openDialog();
           }
	       
	    }

}
