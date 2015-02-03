package pt.aeist.mobile.res;


//import pt.aeist.mobile.EmentasFrag;

import pt.aeist.mobile.info.AeistFrag;
import pt.aeist.mobile.ServFrag;
import pt.aeist.mobile.eventos.EventosFrag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
public class TabsPagerAdapter extends FragmentPagerAdapter {
	public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
	@Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
        	return new EventosFrag();
        case 1:
            // Games fragment activity
            return new ServFrag();
        case 2:
            // Movies fragment activity
            return new AeistFrag();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}
