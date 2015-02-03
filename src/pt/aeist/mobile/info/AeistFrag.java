package pt.aeist.mobile.info;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import pt.aeist.mobile.R;
import pt.aeist.mobile.eventos.EventoContainer;
import pt.aeist.mobile.res.AppController;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AeistFrag extends Fragment {
	private PelAdapter pelListAdapter;
	private List<PelouroContainer> pelouroList;
	private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	
	public class PelAdapter extends BaseExpandableListAdapter {

		@Override
		public Pessoa getChild(int groupPosition, int childPosition) {
			return pelouroList.get(groupPosition).getPessoa(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		
		@Override
	    public View getChildView(int groupPosition, final int childPosition,
	            boolean isLastChild, View convertView, ViewGroup parent) {
	 
	        final String nome = getChild(groupPosition, childPosition).nome;
	        final String desc = getChild(groupPosition, childPosition).desc;
	        final String imageLink = getChild(groupPosition, childPosition).imageLink;
	        
	        if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) getActivity()
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.listitem, null);
	        }
	 
	        if (imageLoader == null)
				imageLoader = AppController.getInstance().getImageLoader();
			
			NetworkImageView thumbNail = (NetworkImageView) convertView
					.findViewById(R.id.thumbnail);
			TextView eventoNome = (TextView)convertView.findViewById(R.id.textView1);
			TextView eventoDesc = (TextView)convertView.findViewById(R.id.textView2);
			
			eventoNome.setText(nome);
			eventoDesc.setText(desc);
			thumbNail.setImageUrl(imageLink, imageLoader);
	        return convertView;
	    }

		@Override
		public int getChildrenCount(int groupPosition) {
			return pelouroList.get(groupPosition).getSize();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return pelouroList.get(groupPosition).pelouroNome;
		}

		@Override
		public int getGroupCount() {
			return pelouroList.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
	    public View getGroupView(int groupPosition, boolean isExpanded,
	            View convertView, ViewGroup parent) {
	        String headerTitle = (String) getGroup(groupPosition);
	        if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) getActivity()
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.list_group, null);
	        }
	 
	        TextView lblListHeader = (TextView) convertView
	                .findViewById(R.id.lblListHeader);
	        lblListHeader.setTypeface(null, Typeface.BOLD);
	        lblListHeader.setText(headerTitle);
	 
	        return convertView;
	    }
	 

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			
			return true;
		}
		
	}
	
	public class Pessoa {
		String nome;
		String desc;
		String imageLink;
		
		public Pessoa(String _nome,String _desc, String _imageLink) {
			nome = _nome;
			desc = _desc;
			imageLink = _imageLink;
		}
	}

	public class PelouroContainer {
		private String pelouroNome;
		private List<Pessoa> pessoa;
		public PelouroContainer(String pelName) {
			pelouroNome = pelName;
			pessoa = new ArrayList<Pessoa>();
		}
		public Pessoa getPessoa(int arg0) {
			return pessoa.get(arg0);
		}
		
		public void addPessoa(Pessoa p1) {
			pessoa.add(p1);
		}
		public int getSize() {
			return pessoa.size();
		}
	}
	
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.aeist_frag, container, false);
	        
	        pelouroList = new ArrayList<PelouroContainer>();
	        
	        PelouroContainer p1 = new PelouroContainer("Presidência");
	        p1.addPessoa(new Pessoa("Pedro Sereno","O Actual Persidente da Associacao de es"
	        		+ "tudantes do IST. blablbablabla",""
	        				+ "http://mobile.aeist.pt/pics/arraial.png"));
	        p1.addPessoa(new Pessoa("Marco Gomes","O Actual Persidente da Associacao de es"
	        		+ "tudantes do IST. blablbablabla",""
	        				+ "http://mobile.aeist.pt/pics/jobshop.png"));
	        pelouroList.add(p1);
	        pelouroList.add(new PelouroContainer("Tesouraria"));
	        pelouroList.add(new PelouroContainer("Gestão e Serviços"));
	        pelouroList.add(new PelouroContainer("Desporto"));
	        pelouroList.add(new PelouroContainer("Recreativa"));
	        pelouroList.add(new PelouroContainer("Cultural"));
	        pelouroList.add(new PelouroContainer("GEFE"));
	        pelouroList.add(new PelouroContainer("Relações Internas e Associados"));
	        pelouroList.add(new PelouroContainer("Relações Externas"));
	        pelouroList.add(new PelouroContainer("Comunicação"));
	        
	        
	        //pelouroListAdapter = new PelouroAdapter();
          	pelListAdapter = new PelAdapter();
	        
	       ExpandableListView listaPelouros = (ExpandableListView)rootView.findViewById(R.id.listView2);
	       ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_aeist, listaPelouros,
                   false);
       	 listaPelouros.addHeaderView(header, null, false);
	       listaPelouros.setAdapter(pelListAdapter);
         	
         	 
	        return rootView;
	    }    	
	    
	    
	}
