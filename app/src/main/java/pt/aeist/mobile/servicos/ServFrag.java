package pt.aeist.mobile.servicos;

import java.util.ArrayList;
import java.util.List;

import pt.aeist.mobile.ActInicio;
import pt.aeist.mobile.R;
import pt.aeist.mobile.eventos.EventosFrag;
import pt.aeist.mobile.res.NextFragmentListener;
import android.content.Context;
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

public class ServFrag extends Fragment{
	protected static final String TAG = "Servicos";
	private List<Servico> servicoList = new ArrayList<Servico>();
	private ListView listView;
	private ServListAdapter adapter;
    static NextFragmentListener firstPageListener;
    private Context _cont;
    public static ServFrag newInstance(NextFragmentListener listener){
        firstPageListener = listener;
        return new ServFrag();
    }
	
	public ServFrag() {
    }


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		_cont = getActivity().getApplicationContext();
		View rootView = inflater.inflate(R.layout.serv_frag, container, false);
		listView = (ListView) rootView.findViewById(R.id.list);
		/*
		 * ViewGroup header = (ViewGroup)
		 * inflater.inflate(R.layout.header_eventos, listView, false);
		 * listView.addHeaderView(header, null, false);
		 */

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int pos, long mylng) {
				switch (pos) {
				case 0:
					firstPageListener.onSwitchToNextFragment("rec_frag");
					break;
				case 1:
					firstPageListener.onSwitchToNextFragment("sf_frag");
					break;
				case 2:
					firstPageListener.onSwitchToNextFragment("desp_frag");
					break;
				}
			}

		});
		adapter = new ServListAdapter(getActivity(), servicoList);
		listView.setAdapter(adapter);
		Servico serv = new Servico();
		serv.setTitle("Recreativa");
		serv.setThumbnailUrl("http://mobile.aeist.pt/assets/images/recreativa.png");
		serv.setDesc(_cont.getString(R.string.recreativa));
		servicoList.add(serv);

		Servico serv1 = new Servico();
		serv1.setTitle("Secção de Folhas");
		serv1.setThumbnailUrl("http://mobile.aeist.pt/assets/images/sf.jpg");
		serv1.setDesc(_cont.getString(R.string.sf));
		servicoList.add(serv1);

		Servico serv2 = new Servico();
		serv2.setTitle("Desportiva");
		serv2.setThumbnailUrl("http://mobile.aeist.pt/assets/images/desp.jpg");
		serv2.setDesc(_cont.getString(R.string.desportiva));
		servicoList.add(serv2);
		
		
		Servico serv3 = new Servico();
		serv3.setTitle("GEFE");
		serv3.setThumbnailUrl("http://mobile.aeist.pt/assets/images/gefe.jpg");
		serv3.setDesc(_cont.getString(R.string.gefe));
		servicoList.add(serv3);

		adapter.notifyDataSetChanged();

		return rootView;
	}

}
