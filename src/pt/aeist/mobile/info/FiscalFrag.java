package pt.aeist.mobile.info;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import pt.aeist.mobile.R;
import pt.aeist.mobile.res.AppController;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;

public class FiscalFrag extends Fragment {
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
	            convertView = infalInflater.inflate(R.layout.list_pessoa, null);
	        }
	 
	        if (imageLoader == null)
				imageLoader = AppController.getInstance().getImageLoader();
			
			NetworkImageView thumbNail = (NetworkImageView) convertView
					.findViewById(R.id.thumbnail);
			thumbNail.setDefaultImageResId(R.drawable.loader);
			thumbNail.setErrorImageResId(R.drawable.loader);
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
		String email;
		public Pessoa(String _nome,String _desc, String _imageLink,String _email) {
			nome = _nome;
			desc = _desc;
			imageLink = _imageLink;
			email = _email;
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
	        
	        PelouroContainer p1 = new PelouroContainer("Conselho Fiscal");
	        p1.addPessoa(new Pessoa("Pedro Sereno","Presidente","http://mobile.aeist.pt/dAEISTpics/pres/sereno.jpg","pedro.sereno@aeist.pt"));
	        p1.addPessoa(new Pessoa("Marco Gomes","Vice-Presidente da AdministraÃ§Ã£o Financeira","http://mobile.aeist.pt/dAEISTpics/pres/marco.jpg","marco.gomes@aeist.pt"));
	        p1.addPessoa(new Pessoa("Maria de Almeida","Vice-Presidente da GestÃ£o","http://mobile.aeist.pt/dAEISTpics/pres/maria.jpg","maria.almeida@aeist.pt"));
	        pelouroList.add(p1);
	        
	        
	        
	        //pelouroListAdapter = new PelouroAdapter();
          	pelListAdapter = new PelAdapter();
	        
	       ExpandableListView listaPelouros = (ExpandableListView)rootView.findViewById(R.id.listView2);
	       
	       ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_aeist, listaPelouros,
                   false);
       	  // listaPelouros.addHeaderView(header, null, false);
	       listaPelouros.setAdapter(pelListAdapter);
	       listaPelouros.setOnChildClickListener(new OnChildClickListener() {
        		 
               @Override
               public boolean onChildClick(ExpandableListView parent, View v,
                       int groupPosition, int childPosition, long id) {
           		String email = pelouroList.get(groupPosition).pessoa.get(childPosition).email;
           		Intent intent = new Intent(Intent.ACTION_SEND);
           		intent.setType("message/rfc822");
           		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
           		Intent mailer = Intent.createChooser(intent, null);
           		startActivity(mailer);
           	
               return false;
               }
           });
	       listaPelouros.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
	    	   @Override
	    	   public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
	    	       // Doing nothing
	    	     return true;
	    	   }
	    	 });
	       listaPelouros.expandGroup(0);
	        return rootView;
	    }    	
	    
	    
	}