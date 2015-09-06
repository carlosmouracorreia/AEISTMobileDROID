package pt.aeist.mobile.servicos;

import java.util.ArrayList;
import java.util.List;

import pt.aeist.mobile.ActInicio;
import pt.aeist.mobile.R;
import pt.aeist.mobile.eventos.EventosFrag;
import pt.aeist.mobile.res.NextFragmentListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DespFrag extends Fragment {
	protected static final String TAG = "Servicos";
    static NextFragmentListener firstPageListener;
    private DespAdapter serviceAdapter;


	public static DespFrag newInstance(NextFragmentListener listener){
        firstPageListener = listener;
        return new DespFrag();

    }

    
	public DespFrag() {
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.sf_frag, container, false);
		ExpandableListView listaServicos = (ExpandableListView)rootView.findViewById(R.id.listView2);
	    ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_desp_frag, listaServicos,
              false);
	    serviceAdapter = new DespAdapter(getActivity());
  	    listaServicos.addHeaderView(header, null, false);
	       listaServicos.setAdapter(serviceAdapter);
	       listaServicos.expandGroup(4);
		return rootView;
	}


	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);
		ActInicio.getInstance().getMenu().getItem(0).setVisible(menuVisible);
	}
	  public void backPressed() {
	        firstPageListener.onSwitchToNextFragment("root");
	    }
}
