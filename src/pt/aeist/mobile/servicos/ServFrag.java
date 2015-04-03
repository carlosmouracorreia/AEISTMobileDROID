package pt.aeist.mobile.servicos;

import java.util.ArrayList;
import java.util.List;

import pt.aeist.mobile.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ServFrag extends Fragment {
	    private List<Servico> servicoList = new ArrayList<Servico>();
	    private ListView listView;
	    private ServListAdapter adapter;	
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.serv_frag, container, false);
	        listView = (ListView) rootView.findViewById(R.id.list);
	     /*  ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_eventos, listView,
                    false);
        	listView.addHeaderView(header, null, false); */
	        adapter = new ServListAdapter(getActivity(), servicoList);
	        listView.setAdapter(adapter);
	        
	        
	        Servico serv = new Servico();
            serv.setTitle("Secção de Folhas");
            serv.setThumbnailUrl("http://mobile.aeist.pt/assets/images/sf.jpg");
            serv.setDesc("Consulta cenas e bue cenas, cenas e bue cenas, cenas e bue cenas, cenas e bue cenas");
            servicoList.add(serv);
            
            Servico serv2 = new Servico();
            serv2.setTitle("Desportiva");
            serv2.setThumbnailUrl("http://mobile.aeist.pt/assets/images/desp.jpg");
            serv2.setDesc("Consulta cenas e bue cenas, cenas e bue cenas, cenas e bue cenas, cenas e bue cenas, Consulta cenas e bue cenas, cenas e bue cenas, cenas e bue cenas, cenas e bue cenas");
            servicoList.add(serv2);
            adapter.notifyDataSetChanged();
	        return rootView;
	    }
	}
