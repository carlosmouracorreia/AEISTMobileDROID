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
	        
	        PelouroContainer p1 = new PelouroContainer("Presidência");
	        p1.addPessoa(new Pessoa("Pedro Sereno","Presidente","http://mobile.aeist.pt/dAEISTpics/pres/sereno.jpg","pedro.sereno@aeist.pt"));
	        p1.addPessoa(new Pessoa("Marco Gomes","Vice-Presidente da Administração Financeira","http://mobile.aeist.pt/dAEISTpics/pres/marco.jpg","marco.gomes@aeist.pt"));
	        p1.addPessoa(new Pessoa("Maria de Almeida","Vice-Presidente da Gestão","http://mobile.aeist.pt/dAEISTpics/pres/maria.jpg","maria.almeida@aeist.pt"));
	        pelouroList.add(p1);
	        
	        PelouroContainer p2 = new PelouroContainer("Gestão e Serviços");
	        p2.addPessoa(new Pessoa("Maria de Almeida","Coordenadora","http://mobile.aeist.pt/dAEISTpics/pres/maria.jpg","maria.almeida@aeist.pt"));
	        p2.addPessoa(new Pessoa("Carlos Correia","Vogal - Programador desta Aplicação","http://mobile.aeist.pt/dAEISTpics/gs/eu.jpg","carlos.correia@aeist.pt"));
	        p2.addPessoa(new Pessoa("Diogo Costa","Vogal","http://mobile.aeist.pt/dAEISTpics/gs/diogo_costa.jpg","diogo.costa@aeist.pt"));
	        p2.addPessoa(new Pessoa("Carlos Almeida","Vogal","","carlos.almeida@aeist.pt"));
	        p2.addPessoa(new Pessoa("José Barbosa","Colaborador","http://mobile.aeist.pt/dAEISTpics/gs/joka.jpg","jose.barbosa@aeist.pt"));
	        p2.addPessoa(new Pessoa("Carlos Costa","Colaborador","","carlos.costa@aeist.pt"));
	        p2.addPessoa(new Pessoa("Rafael Schimassek","Colaborador","http://mobile.aeist.pt/dAEISTpics/gs/schima.jpg","rafael.schimassek@aeist.pt"));
	        pelouroList.add(p2);
	        
	        
	        PelouroContainer p3 = new PelouroContainer("Comunicação");
	        p3.addPessoa(new Pessoa("Catarina Almeida","Coordenadora","http://mobile.aeist.pt/dAEISTpics/comunic/catalmeida.jpg","catarina.almeida@aeist.pt"));
	        p3.addPessoa(new Pessoa("Inês Urbano","Vogal","http://mobile.aeist.pt/dAEISTpics/comunic/urbano.jpg","ines.urbano@aeist.pt"));
	        p3.addPessoa(new Pessoa("Beatriz Matafome","Vogal","http://mobile.aeist.pt/dAEISTpics/comunic/matafome.jpg","beatriz.matafome@aeist.pt"));
	        p3.addPessoa(new Pessoa("Mariana Patanita","Vogal","http://mobile.aeist.pt/dAEISTpics/comunic/patanita.jpg","mariana.patanita@aeist.pt"));
	        p3.addPessoa(new Pessoa("João Tomázio","Colaborador","http://mobile.aeist.pt/dAEISTpics/comunic/tomazio.jpg","joao.tomazio@aeist.pt"));
	        p3.addPessoa(new Pessoa("Laura Silva","Colaboradora","http://mobile.aeist.pt/dAEISTpics/comunic/laurasilva.jpg","laura.silva@aeist.pt"));
	        p3.addPessoa(new Pessoa("Mariana Loureiro","Colaboradora","http://mobile.aeist.pt/dAEISTpics/comunic/marianaloureiro.jpg","mariana.loureiro@aeist.pt"));
	        pelouroList.add(p3);
	        
	        PelouroContainer p4 = new PelouroContainer("Desporto");
	        p4.addPessoa(new Pessoa("Rodrigo Lourenço","Coordenador","http://mobile.aeist.pt/dAEISTpics/desporto/rodrigolourenco.jpg","rodrigo.lourenco@aeist.pt"));
	        p4.addPessoa(new Pessoa("Afonso Gonçalves","Vogal","http://mobile.aeist.pt/dAEISTpics/desporto/afonsogoncalves.jpg","afonso.goncalves@aeist.pt"));
	        p4.addPessoa(new Pessoa("Inês Daniel","Colaboradora","http://mobile.aeist.pt/dAEISTpics/desporto/inesdaniel.jpg","ines.daniel@aeist.pt"));
	        p4.addPessoa(new Pessoa("Ricardo Almeida","Colaborador","http://mobile.aeist.pt/dAEISTpics/desporto/ricardoalmeida.jpg","ricardo.almeida@aeist.pt"));
	        p4.addPessoa(new Pessoa("João Reis","Colaborador","http://mobile.aeist.pt/dAEISTpics/desporto/joaoreis.jpg","joao.reis@aeist.pt"));
	        pelouroList.add(p4);
	        
	        PelouroContainer p5 = new PelouroContainer("GEFE");
	        p5.addPessoa(new Pessoa("Maria Tavares","Coordenadora","http://mobile.aeist.pt/dAEISTpics/gefe/mariatavares.jpg","maria.tavares@aeist.pt"));
	        p5.addPessoa(new Pessoa("Nuno Bajanca","Vogal","http://mobile.aeist.pt/dAEISTpics/gefe/nunobajanca.jpg","nuno.bajanca@aeist.pt"));
	        p5.addPessoa(new Pessoa("José Rego","Vogal","http://mobile.aeist.pt/dAEISTpics/gefe/joserego.jpg","jose.rego@aeist.pt"));
	        p5.addPessoa(new Pessoa("Laura Barroso","Vogal","http://mobile.aeist.pt/dAEISTpics/gefe/laurabarroso.jpg","laura.barroso@aeist.pt"));
	        p5.addPessoa(new Pessoa("Andreia Santos","Colaboradora","http://mobile.aeist.pt/dAEISTpics/gefe/andreiasantos.jpg","andreia.santos@aeist.pt"));
	        pelouroList.add(p5);
	        
	        PelouroContainer p8 = new PelouroContainer("Política Educativa");
	        p8.addPessoa(new Pessoa("Eunice Afonso","Coordenadora","http://mobile.aeist.pt/dAEISTpics/pe/euniceafonso.jpg","eunice.afonso@aeist.pt"));
	        p8.addPessoa(new Pessoa("Marta Santos","Vogal","http://mobile.aeist.pt/dAEISTpics/pe/martasantos.jpg","marta.santos@aeist.pt"));
	        pelouroList.add(p8);
	        
	        PelouroContainer p6 = new PelouroContainer("Recreativa");
	        p6.addPessoa(new Pessoa("Filipa East","Coordenadora","http://mobile.aeist.pt/dAEISTpics/recreativa/filipaeast.jpg","filipa.east@aeist.pt"));
	        p6.addPessoa(new Pessoa("Ricardo Cardão","Vogal","http://mobile.aeist.pt/dAEISTpics/recreativa/ricardocardao.jpg","ricardo.cardao@aeist.pt"));
	        p6.addPessoa(new Pessoa("Valdo Lopes","Vogal","http://mobile.aeist.pt/dAEISTpics/recreativa/valdolopes.jpg","valdo.lopes@aeist.pt"));
	        p6.addPessoa(new Pessoa("António Morgado","Vogal","http://mobile.aeist.pt/dAEISTpics/recreativa/antoniomorgado.jpg","antonio.morgado@aeist.pt"));
	        p6.addPessoa(new Pessoa("Manuel Cardoso","Colaborador","http://mobile.aeist.pt/dAEISTpics/recreativa/manuelcardoso.jpg","manuel.cardoso@aeist.pt"));
	        p6.addPessoa(new Pessoa("André Paluch","Colaborador","http://mobile.aeist.pt/dAEISTpics/recreativa/andrepaluch.jpg","andre.paluch@aeist.pt"));
	        p6.addPessoa(new Pessoa("João Carvalho","Colaborador","http://mobile.aeist.pt/dAEISTpics/recreativa/joaocarvalho.jpg","joao.carvalho@aeist.pt"));
	        pelouroList.add(p6);
	        
	        PelouroContainer p7 = new PelouroContainer("Cultural");
	        p7.addPessoa(new Pessoa("Stephano Pugliese","Coordenador","http://mobile.aeist.pt/dAEISTpics/cultural/stephanopugliese.jpg","stephano.pugliese@aeist.pt"));
	        p7.addPessoa(new Pessoa("António Fitas","Vogal","http://mobile.aeist.pt/dAEISTpics/cultural/antoniofitas.jpg","antonio.fitas@aeist.pt"));
	        p7.addPessoa(new Pessoa("Valdo Lopes","Vogal","http://mobile.aeist.pt/dAEISTpics/recreativa/valdolopes.jpg","valdo.lopes@aeist.pt"));
	        p7.addPessoa(new Pessoa("Paloma Gonçalves","Colaboradora","http://mobile.aeist.pt/dAEISTpics/cultural/palomagoncalves.jpg","paloma.goncalves@aeist.pt"));
	        pelouroList.add(p7);
	        
	        PelouroContainer p9 = new PelouroContainer("Relações Internas e Associados");
	        p9.addPessoa(new Pessoa("Francisca Rey","Coordenadora","http://mobile.aeist.pt/dAEISTpics/ria/franciscarey.jpg","francisca.rey@aeist.pt"));
	        p9.addPessoa(new Pessoa("João Sabino","Vogal","http://mobile.aeist.pt/dAEISTpics/ria/joaosabino.jpg","joao.sabino@aeist.pt"));
	        p9.addPessoa(new Pessoa("Edgar Ramalho","Colaborador","","edgar.ramalho@aeist.pt"));
	        p9.addPessoa(new Pessoa("André Costa","Colaborador","http://mobile.aeist.pt/dAEISTpics/ria/andrecosta.jpg","andre.costa@aeist.pt"));
	        p9.addPessoa(new Pessoa("Bernardo Caldeira","Colaborador","","bernardo.caldeira@aeist.pt"));
	        pelouroList.add(p9);
	        
	        PelouroContainer p10 = new PelouroContainer("Relações Externas");
	        p10.addPessoa(new Pessoa("Inês Henriques","Coordenadora","http://mobile.aeist.pt/dAEISTpics/re/ineshenriques.jpg","ines.henriques@aeist.pt"));
	        p10.addPessoa(new Pessoa("Danise Caetano","Colaboradora","http://mobile.aeist.pt/dAEISTpics/re/danisecaetano.jpg","danise.caetano@aeist.pt"));
	        pelouroList.add(p10);
	        
	        PelouroContainer p11 = new PelouroContainer("Taguspark");
	        p11.addPessoa(new Pessoa("João Valado","Coordenador","http://mobile.aeist.pt/dAEISTpics/joaovalado.jpg","joao.valado@aeist.pt"));
	        pelouroList.add(p11);
	        
	        
	        
	        //pelouroListAdapter = new PelouroAdapter();
          	pelListAdapter = new PelAdapter();
	        
	       ExpandableListView listaPelouros = (ExpandableListView)rootView.findViewById(R.id.listView2);
	       ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_aeist, listaPelouros,
                   false);
       	   listaPelouros.addHeaderView(header, null, false);
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
         	 
	        return rootView;
	    }    	
	    
	    
	}
