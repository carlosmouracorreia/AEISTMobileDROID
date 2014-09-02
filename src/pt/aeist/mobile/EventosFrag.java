package pt.aeist.mobile;


import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class EventosFrag extends Fragment {
	
	public class eventoContainer {
		String eventoNome;
		String eventoDesc;
		String eventoLink;
		String imageLink;
	}
	NewsAdapter eventosListAdapter;
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.eventos_frag, container, false);
	        
	        eventosListAdapter = new NewsAdapter();
	        
	        ListView listaEventos = (ListView)rootView.findViewById(R.id.listView1);
	        listaEventos.setAdapter(eventosListAdapter);
	        
	        listaEventos.setOnItemClickListener(new OnItemClickListener() {
	        	//Ao ser clicado  um evento na lista
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					
					eventoContainer chapter = eventosListAdapter.getEventoNr(arg2);
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chapter.eventoLink));
					startActivity(browserIntent);
					
				}
			});
	        
	        return rootView;
	    }
	    
	    public class NewsAdapter extends BaseAdapter {

	    	List<eventoContainer> eventosList = getDataForListView();
			@Override
			public int getCount() {
				return eventosList.size();
			}

			@Override
			public eventoContainer getItem(int arg0) {
				return eventosList.get(arg0);
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				
				if(arg1==null)
				{
					LayoutInflater inflater = (LayoutInflater) EventosFrag.this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					arg1 = inflater.inflate(R.layout.listitem, arg2,false);
				}
				
				TextView eventoNome = (TextView)arg1.findViewById(R.id.textView1);
				TextView eventoDesc = (TextView)arg1.findViewById(R.id.textView2);
				ImageView eventoFtg = (ImageView)arg1.findViewById(R.id.imageView1);
				eventoContainer chapter = eventosList.get(arg0);
				
				eventoNome.setText(chapter.eventoNome);
				eventoDesc.setText(chapter.eventoDesc);
				eventoFtg.setImageResource(getResources().getIdentifier(chapter.imageLink, "drawable", EventosFrag.this.getActivity().getPackageName()));
				return arg1;
			}
			
			public eventoContainer getEventoNr(int position)
			{
				return eventosList.get(position);
			}

	    }
	    
	    
	    public List<eventoContainer> getDataForListView()
	    {
	    	List<eventoContainer> eventosList = new ArrayList<eventoContainer>();
	    	
	    	for(int i=0;i<3;i++)
	    	{
	    		
	    		eventoContainer chapter = new eventoContainer();
	    		chapter.eventoNome = "Evento "+i;
	    		chapter.eventoDesc = "Descrição para o evento (bd here) "+i;
	    		chapter.eventoLink = "http://www.aeist.pt/evento_" + i;
	    		chapter.imageLink = "arraial" + i;
	    		eventosList.add(chapter);
	    	}
	    	
	    	return eventosList;
	    	
	    }

}
