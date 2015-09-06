package pt.aeist.mobile.info;

import pt.aeist.mobile.ActInicio;
import pt.aeist.mobile.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AEFrag extends Fragment {
	 private FragmentTabHost mTabHost;


	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);
		if(menuVisible)
			ActInicio.getInstance().getMenu().getItem(0).setVisible(false);
	}

	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
		 View rootView = inflater.inflate(R.layout.fragment_tabs,container, false);


	        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
	        
	        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

	        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Direcção"),
	                AeistFrag.class, null);
	        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("C.Fiscal"),
	                FiscalFrag.class, null);
	        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("MAGA"),
	                MagaFrag.class, null);


	        return rootView;
	        
	 }

}
