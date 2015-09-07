package pt.aeist.mobile.res;


//import pt.aeist.mobile.EmentasFrag;

import pt.aeist.mobile.ActInicio;
import pt.aeist.mobile.R;
import pt.aeist.mobile.info.AEFrag;
import pt.aeist.mobile.info.AeistFrag;
import pt.aeist.mobile.servicos.DespFrag;
import pt.aeist.mobile.servicos.RecFrag;
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
        	return new EventosFrag();
        case 1:
        	 if (mFragmentAtPos0 == null)
             {
                if(ActInicio.getInstance().toBbq()) {
                    mFragmentAtPos0 = RecFrag.newInstance(listener);
                }
                 else
                    mFragmentAtPos0 = ServFrag.newInstance(listener);
             }
             return mFragmentAtPos0;
        case 2:
            return new AeistFrag();
        }
 
        return null;
    }
	
	 private final class FragmentChangeListener implements
     NextFragmentListener {
         public void onSwitchToNextFragment(String fragment) {
           mFragmentManager.beginTransaction().remove(mFragmentAtPos0).commit();
             
             switch (fragment){
             case "root":
            	 mFragmentAtPos0 = ServFrag.newInstance(listener);
                 break;
             case "sf_frag":
            	 mFragmentAtPos0 = SFFrag.newInstance(listener);
                 break;
             case "desp_frag":
            	 mFragmentAtPos0 = DespFrag.newInstance(listener);
                 break;
             case "rec_frag":
            	 mFragmentAtPos0 = RecFrag.newInstance(listener);
                 break;
             }            
             
             notifyDataSetChanged();
         }
     }
     FragmentChangeListener listener = new FragmentChangeListener();

 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
    /**
     * THIS IS SOME REALLY IMPORTANT BUSINESS :)
     */
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
        //DESP
	    else if(object instanceof ServFrag && mFragmentAtPos0 instanceof DespFrag){
	        return POSITION_NONE;
	   }
		else if(object instanceof DespFrag && mFragmentAtPos0 instanceof ServFrag){
		    return POSITION_NONE;
		}
        //REC
		 else if(object instanceof ServFrag && mFragmentAtPos0 instanceof RecFrag){
		        return POSITION_NONE;
		   }
			else if(object instanceof RecFrag && mFragmentAtPos0 instanceof ServFrag){
			    return POSITION_NONE;
			}
            return POSITION_UNCHANGED;

    }

}
