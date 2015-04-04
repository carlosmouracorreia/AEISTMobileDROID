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
                 mFragmentAtPos0 = new ServFrag(listener);
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
             mFragmentManager.beginTransaction().remove(mFragmentAtPos0)
             .commit();
             if (mFragmentAtPos0 instanceof ServFrag){
            	 System.err.println("entra!");

            	 SFFrag fragment = new SFFrag(listener);
                 FragmentTransaction transaction = null;
                 transaction = mFragmentAtPos0.getFragmentManager().beginTransaction();
                 
                 transaction.replace(R.id.pager, fragment); //id of ViewPager
                 transaction.addToBackStack(null);
                 transaction.commit();
             }else{ // Instance of NextFragment
                 mFragmentAtPos0 = new ServFrag(listener);
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

}
