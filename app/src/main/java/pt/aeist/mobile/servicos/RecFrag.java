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

public class RecFrag extends Fragment {
	protected static final String TAG = "Servicos";
    static NextFragmentListener firstPageListener;
    private RecAdapter _recAdapter;
    private static RecFrag thisFrag;
    private ExpandableListView listaRec;

    public static RecFrag newInstance(NextFragmentListener listener){
        firstPageListener = listener;
        thisFrag = new RecFrag();
        return thisFrag;
    }
    
    public static RecFrag getInstance() {
    	return thisFrag;
    }
    
	public RecFrag() {
    }



    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.sf_frag, container, false);
		 listaRec = (ExpandableListView)rootView.findViewById(R.id.listView2);
       ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_rec_frag, listaRec,
              false);
       _recAdapter = new RecAdapter(getActivity(),listaRec);
        _recAdapter.fetchData();
  	   listaRec.addHeaderView(header, null, false);
       listaRec.setAdapter(_recAdapter);
       return rootView;
	}


   @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
       if(!ActInicio.getInstance().toBbq())
         ActInicio.getInstance().getMenu().getItem(0).setVisible(menuVisible);
    }


	  public void backPressed() {
	        firstPageListener.onSwitchToNextFragment("root");
	    }
}
