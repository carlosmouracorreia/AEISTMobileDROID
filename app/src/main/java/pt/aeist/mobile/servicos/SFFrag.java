package pt.aeist.mobile.servicos;

import java.util.ArrayList;
import java.util.List;

import pt.aeist.mobile.ActInicio;
import pt.aeist.mobile.R;
import pt.aeist.mobile.eventos.EventosFrag;
import pt.aeist.mobile.info.AeistFrag.PelAdapter;
import pt.aeist.mobile.res.NextFragmentListener;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;

public class SFFrag extends Fragment {
	protected static final String TAG = "Servicos";
    static NextFragmentListener firstPageListener;
    private SFAdapter serviceAdapter;


	public static SFFrag newInstance(NextFragmentListener listener){
        firstPageListener = listener;
        return new SFFrag();

    }
    
    
	public SFFrag() {
    }


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.sf_frag, container, false);
		 ExpandableListView listaServicos = (ExpandableListView)rootView.findViewById(R.id.listView2);
	       ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_sf_frag, listaServicos,
                 false);
	       serviceAdapter = new SFAdapter(getActivity());
     	   listaServicos.addHeaderView(header, null, false);
	       listaServicos.setAdapter(serviceAdapter);
	       listaServicos.setOnChildClickListener(new OnChildClickListener() {

			   @Override
			   public boolean onChildClick(ExpandableListView parent, View v,
										   int groupPosition, int childPosition, long id) {
				   if (groupPosition == 0 && childPosition == 1) {
					   final String googleDocsUrl = "http://docs.google.com/viewer?url=";
					   final String url = "http://aeist.pt/mediaRep/editors/AEIST/Documentos/PrecarioSF1.pdf";
					   Intent intent = new Intent(Intent.ACTION_VIEW);
					   intent.setDataAndType(Uri.parse(googleDocsUrl + url), "text/html");
					   startActivity(intent);

				   }
				   if (groupPosition == 0 && childPosition == 3) {
					   final String googleDocsUrl = "http://docs.google.com/viewer?url=";
					   final String url = "http://aeist.pt/mediaRep/editors/AEIST/Documentos/PrecarioSF2.pdf";
					   Intent intent = new Intent(Intent.ACTION_VIEW);
					   intent.setDataAndType(Uri.parse(googleDocsUrl + url), "text/html");
					   startActivity(intent);

				   }
				   return false;
			   }
		   });
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
