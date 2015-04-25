package pt.aeist.mobile.servicos;

import java.util.ArrayList;
import java.util.List;

import pt.aeist.mobile.R;
import pt.aeist.mobile.eventos.EventosFrag;
import pt.aeist.mobile.res.NextFragmentListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DespFrag extends Fragment {
	protected static final String TAG = "Servicos";
    static NextFragmentListener firstPageListener;

	
    public static DespFrag newInstance(NextFragmentListener listener){
        firstPageListener = listener;
        return new DespFrag();

    }
    
    
	public DespFrag() {
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.header_desp_frag, container, false);
		return rootView;
	}

	  public void backPressed() {
	        firstPageListener.onSwitchToNextFragment("root");
	    }
}
