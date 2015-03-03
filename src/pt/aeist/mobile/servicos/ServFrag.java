package pt.aeist.mobile.servicos;

import pt.aeist.mobile.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ServFrag extends Fragment {
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.serv_frag, container, false);
	        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
	        
	        // Instance of ImageAdapter Class
	        gridView.setAdapter(new ImageAdapter(getActivity()));
	        return rootView;
	    }
	}
