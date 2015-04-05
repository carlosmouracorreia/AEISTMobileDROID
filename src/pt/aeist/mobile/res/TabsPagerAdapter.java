package pt.aeist.mobile.res;


//import pt.aeist.mobile.EmentasFrag;

import pt.aeist.mobile.R;
import pt.aeist.mobile.info.AEFrag;
import pt.aeist.mobile.servicos.SFFrag;
import pt.aeist.mobile.servicos.ServFrag;
import pt.aeist.mobile.eventos.EventosFrag;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
public class TabsPagerAdapter extends FragmentPagerAdapter {
	private final FragmentManager mFragmentManager;
	private Fragment mFragmentAtPos0;
    private Context context;
	public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }
	@Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
        	return new EventosFrag();
        case 1:
        	 if (mFragmentAtPos0 == null)
             {
                 mFragmentAtPos0 = ServFrag.newInstance(listener);
             }
             return mFragmentAtPos0;
        case 2:
            // Movies fragment activity
            return new AEFrag();
        }
 
        return null;
    }
	
	 private final class FirstPageListener implements
     FirstPageFragmentListener {
         public void onSwitchToNextFragment() {
           mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();
             if (mFragmentAtPos0 instanceof ServFrag){
            	 System.err.println("entra!");

            	 mFragmentAtPos0 = SFFrag.newInstance(listener);
               
             }else{ // Instance of NextFragment
                 mFragmentAtPos0 = ServFrag.newInstance(listener);
             }
             notifyDataSetChanged();
         }
     }
     FirstPageListener listener = new FirstPageListener();

 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
    
    @Override
    public int getItemPosition(Object object)
    {
        //object is the current fragment displayed at position 0.  
        if(object instanceof ServFrag && mFragmentAtPos0 instanceof SFFrag){
            return POSITION_NONE;
        //this condition is for when you press back
         }else if(object instanceof SFFrag && mFragmentAtPos0 instanceof ServFrag){
              return POSITION_NONE;
         }
            return POSITION_UNCHANGED;

    }

}
