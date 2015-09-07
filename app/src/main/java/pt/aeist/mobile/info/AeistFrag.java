package pt.aeist.mobile.info;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import pt.aeist.mobile.ActInicio;
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

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);
		if(menuVisible)
			ActInicio.getInstance().getMenu().getItem(0).setVisible(false);
	}



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
	        p1.addPessoa(new Pessoa("Rodrigo Barbosa","Presidente","http://mobile.aeist.pt/dAEISTpics/2015/pres/rodrigobarbosa.png","rodrigo.barbosa@aeist.pt"));
	        p1.addPessoa(new Pessoa("Ricardo Lopes", "Vice-Presidente", "http://mobile.aeist.pt/dAEISTpics/2015/pres/ricardolopes.png", "ricardo.lopes@aeist.pt"));
	        p1.addPessoa(new Pessoa("Marta Brissos Santos","Tesoureira","http://mobile.aeist.pt/dAEISTpics/2015/pres/martabrissos.png","marta.brissos@aeist.pt"));
	        pelouroList.add(p1);
	        
	        PelouroContainer p2 = new PelouroContainer("Gestão e Serviços");
	        p2.addPessoa(new Pessoa("Pedro Santos","Coordenador","http://mobile.aeist.pt/dAEISTpics/2015/gs/pedrosantos.png","pedro.santos@aeist.pt"));
	       // p2.addPessoa(new Pessoa("Carlos Correia","Colaborador - Programador desta Aplicação","http://mobile.aeist.pt/dAEISTpics/gs/eu.jpg","carlos.correia@aeist.pt"));
	        p2.addPessoa(new Pessoa("Miguel Abelho", "Vogal", "http://mobile.aeist.pt/dAEISTpics/2015/gs/miguelabelho.jpg", "miguel.abelho@aeist.pt"));
	        p2.addPessoa(new Pessoa("José Magalhães","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/gs/josemagalhaes.jpg","jose.magalhaes@aeist.pt"));
	        p2.addPessoa(new Pessoa("José Damasceno","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/gs/josedamasceno.jpg","jose.damasceno@aeist.pt"));
	        p2.addPessoa(new Pessoa("Diogo Mendes","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/gs/diogomendes.jpg","diogo.mendes@aeist.pt"));
	        p2.addPessoa(new Pessoa("Laura Silva", "Colaboradora", "http://mobile.aeist.pt/dAEISTpics/2015/gs/laurasilva.jpg", "laura.silva@aeist.pt"));
	        p2.addPessoa(new Pessoa("Laura Barroso","Colaboradora","http://mobile.aeist.pt/dAEISTpics/2015/gs/laurabarroso.jpg","laura.barroso@aeist.pt"));
	        pelouroList.add(p2);

			PelouroContainer p3 = new PelouroContainer("Comunicação");
			p3.addPessoa(new Pessoa("Patrícia Silva","Coordenadora","http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/patriciasilva.png","patricia.silva@aeist.pt"));
			p3.addPessoa(new Pessoa("Sara Pereira","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/sarapereira.jpg","sara.pereira@aeist.pt"));
			p3.addPessoa(new Pessoa("David Cavaco","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/davidcavaco.jpg","david.cavaco@aeist.pt"));
			p3.addPessoa(new Pessoa("Pedro Carvalho","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/pedrocarvalho.jpg","pedro.carvalho@aeist.pt"));
			p3.addPessoa(new Pessoa("André Monteiro", "Colaborador", "http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/andremonteiro.jpg", "andre.monteiro@aeist.pt"));
			p3.addPessoa(new Pessoa("Sofia Martins","Colaboradora","http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/sofiamartins.jpg","sofia.martins@aeist.pt"));
			p3.addPessoa(new Pessoa("Patrícia Ferreira","Colaboradora","http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/patriciaferreira.jpg","patricia.ferreira@aeist.pt"));
			p3.addPessoa(new Pessoa("Nuno Silva","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/nunosilva.jpg","nuno.silva@aeist.pt"));
			p3.addPessoa(new Pessoa("Ana Raposo", "Colaboradora", "http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/anaraposo.jpg", "ana.raposo@aeist.pt"));
			p3.addPessoa(new Pessoa("Briana Vieira","Colaboradora","http://mobile.aeist.pt/dAEISTpics/2015/comunicacao/brianavieira.jpg","briana.vieira@aeist.pt"));
			pelouroList.add(p3);


			PelouroContainer p9 = new PelouroContainer("Informática");
			p9.addPessoa(new Pessoa("Carlos Branco","Coordenador","http://mobile.aeist.pt/dAEISTpics/2015/info/carlosbranco.png","carlos.branco@aeist.pt"));
			p9.addPessoa(new Pessoa("André Mendonça", "Colaborador", "http://mobile.aeist.pt/dAEISTpics/2015/info/andremendonca.jpg", "andre.mendonca@aeist.pt"));
			p9.addPessoa(new Pessoa("João Pestana","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/info/joaopestana.jpg","joao.pestana@aeist.pt"));
			p9.addPessoa(new Pessoa("Leo Valente","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/info/leovalente.jpg","leo.valente@aeist.pt"));
			p9.addPessoa(new Pessoa("Diogo Monteiro","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/info/diogomonteiro.jpg","diogo.monteiro@aeist.pt"));
			p9.addPessoa(new Pessoa("André Faustino", "Colaborador", "http://mobile.aeist.pt/dAEISTpics/2015/info/andrefaustino.jpg", "andre.faustino@aeist.pt"));
			p9.addPessoa(new Pessoa("Alexandr Ignatiev","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/info/alexandrignatiev.jpg","alexandr.ignatiev@aeist.pt"));
			pelouroList.add(p9);

	        
	        PelouroContainer p4 = new PelouroContainer("Desporto");
	        p4.addPessoa(new Pessoa("João Formiga","Coordenador","http://mobile.aeist.pt/dAEISTpics/2015/desporto/joaoformiga.jpg","joao.formiga@aeist.pt"));
	        p4.addPessoa(new Pessoa("André Santos","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/desporto/andresantos.jpg","andre.santos@aeist.pt"));
	        p4.addPessoa(new Pessoa("André Lopes","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/desporto/andrelopes.jpg","andre.lopes@aeist.pt"));
	        p4.addPessoa(new Pessoa("Alexandre Passo","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/desporto/alexandrepasso.jpg","alexandre.passo@aeist.pt"));
	        p4.addPessoa(new Pessoa("André Korolev","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/desporto/andrekorolev.jpg","andre.korolev@aeist.pt"));
	        p4.addPessoa(new Pessoa("Renato Amorim","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/desporto/renatoamorim.jpg","renato.amorim@aeist.pt"));
	        p4.addPessoa(new Pessoa("Guilherme Nogueira","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/desporto/guilhermenogueira.jpg","guilherme.nogueira@aeist.pt"));
	        pelouroList.add(p4);
	        
	        PelouroContainer p5 = new PelouroContainer("GEFE");
	        p5.addPessoa(new Pessoa("Mafalda Ramos","Coordenadora","http://mobile.aeist.pt/dAEISTpics/2015/gefe/mafaldaramos.png","mafalda.ramos@aeist.pt"));
	        p5.addPessoa(new Pessoa("João Dionísio","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/gefe/joaodionisio.jpg","joao.dionisio@aeist.pt"));
	        p5.addPessoa(new Pessoa("Constança Barroso","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/gefe/constancabarroso.jpg","constanca.barroso@aeist.pt"));
	        p5.addPessoa(new Pessoa("Duarte Soares", "Vogal", "http://mobile.aeist.pt/dAEISTpics/2015/gefe/duartesoares.jpg", "duarte.soares@aeist.pt"));
	        p5.addPessoa(new Pessoa("Francisco Agostinho","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/gefe/franciscoagostinho.jpg","francisco.agostinho@aeist.pt"));
	        p5.addPessoa(new Pessoa("Filipa Dias","Colaboradora","http://mobile.aeist.pt/dAEISTpics/2015/gefe/filipadias.jpg","filipa.dias@aeist.pt"));
	        p5.addPessoa(new Pessoa("Andrei Negara", "Colaborador", "http://mobile.aeist.pt/dAEISTpics/2015/gefe/andreinegara.jpg", "andrei.negara@aeist.pt"));
	        p5.addPessoa(new Pessoa("Miguel Cunha","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/gefe/miguelcunha.jpg","miguel.cunha@aeist.pt"));
	        pelouroList.add(p5);
	        
	        PelouroContainer p8 = new PelouroContainer("Política Educativa");
	        p8.addPessoa(new Pessoa("João Catarino","Coordenador","http://mobile.aeist.pt/dAEISTpics/2015/pe/joaocatarino.jpg","joao.catarino@aeist.pt"));
	        p8.addPessoa(new Pessoa("Luís Matias","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/pe/luismatias.jpg","luis.matias@aeist.pt"));
	        p8.addPessoa(new Pessoa("Pedro Reganha","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/tagus/pedroreganha.jpg","pedro.reganha@aeist.pt"));
	        pelouroList.add(p8);
	        
	        PelouroContainer p6 = new PelouroContainer("Recreativa e Cultural");
	        p6.addPessoa(new Pessoa("Carlos Costa","Coordenador","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/carloscosta.png","carlos.costa@aeist.pt"));
	        p6.addPessoa(new Pessoa("Luís Prior","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/luisprior.jpg","luis.prior@aeist.pt"));
	        p6.addPessoa(new Pessoa("Helena Martinho","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/helenamartinho.jpg","helena.martinho@aeist.pt"));
	        p6.addPessoa(new Pessoa("Artur Esteves","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/arturesteves.jpg","artur.esteves@aeist.pt"));
	        p6.addPessoa(new Pessoa("Filipe Afonso","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/filipeafonso.jpg","filipe.afonso@aeist.pt"));
	        p6.addPessoa(new Pessoa("João Lobo","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/joaolobo.jpg","joao.lobo@aeist.pt"));
	        p6.addPessoa(new Pessoa("Guilherme Lopes","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/guilhermelopes.jpg","guilherme.lopes@aeist.pt"));
	        p6.addPessoa(new Pessoa("Mafalda Toscano","Colaboradora","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/mafaldatoscano.jpg","mafalda.toscano@aeist.pt"));
	        p6.addPessoa(new Pessoa("Patrícia Marques", "Colaboradora", "http://mobile.aeist.pt/dAEISTpics/2015/recreativa/patriciamarques.jpg", "patricia.marques@aeist.pt"));
	        p6.addPessoa(new Pessoa("João Cabo","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/joaocabo.jpg","joao.cabo@aeist.pt"));
	        p6.addPessoa(new Pessoa("João Pedro Carvalho","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/joaocarvalho.jpg","joao.carvalho@aeist.pt"));
	        p6.addPessoa(new Pessoa("Luís Freixinho","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/luisfreixinho.jpg","luis.freixinho@aeist.pt"));
	        p6.addPessoa(new Pessoa("João Santos","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/recreativa/joaosantos.jpg","joao.santos@aeist.pt"));
	        pelouroList.add(p6);

	        
	        PelouroContainer p12 = new PelouroContainer("Relações Internas e Associados");
	        p12.addPessoa(new Pessoa("Pedro Sousa","Coordenador","http://mobile.aeist.pt/dAEISTpics/2015/ria/pedrosousa.png","pedro.sousa@aeist.pt"));
	        p12.addPessoa(new Pessoa("João Francisco Silva","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/ria/joaosilva.jpg","joao.f.silva@aeist.pt"));
	        p12.addPessoa(new Pessoa("Tiago Dias","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/ria/tiagodias.jpg","tiago.dias@aeist.pt"));
	        p12.addPessoa(new Pessoa("Tomás Jacob","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/ria/tomasjacob.jpg","tomas.jacob@aeist.pt"));
	        p12.addPessoa(new Pessoa("Filipe Soares","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/ria/filipesoares.jpg","filipe.soares@aeist.pt"));
	        pelouroList.add(p12);
	        
	        PelouroContainer p10 = new PelouroContainer("Relações Externas");
	        p10.addPessoa(new Pessoa("Gustavo Carita","Coordenador","http://mobile.aeist.pt/dAEISTpics/2015/re/gustavocarita.png","gustavo.carita@aeist.pt"));
	        p10.addPessoa(new Pessoa("João Morgado","Vogal","http://mobile.aeist.pt/dAEISTpics/2015/re/joaomorgado.jpg","joao.morgado@aeist.pt"));
	        p10.addPessoa(new Pessoa("Pedro Vieira","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/re/pedrovieira.jpg","pedro.vieira@aeist.pt"));
	        p10.addPessoa(new Pessoa("Sara Cunha","Colaboradora","http://mobile.aeist.pt/dAEISTpics/2015/re/saracunha.jpg","sara.cunha@aeist.pt"));
	        p10.addPessoa(new Pessoa("João Tomázio","Colaborador","http://mobile.aeist.pt/dAEISTpics/2015/re/joaotomazio.jpg","joao.tomazio@aeist.pt"));
	        pelouroList.add(p10);
	        
	        PelouroContainer p11 = new PelouroContainer("Taguspark");
	        p11.addPessoa(new Pessoa("Pedro Reganha","Coordenador","http://mobile.aeist.pt/dAEISTpics/2015/tagus/pedroreganha.jpg","pedro.reganha@aeist.pt"));
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
