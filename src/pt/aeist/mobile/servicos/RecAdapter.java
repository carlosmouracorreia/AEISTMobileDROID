package pt.aeist.mobile.servicos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.aeist.mobile.R;
import pt.aeist.mobile.eventos.EventoContainer;
import pt.aeist.mobile.info.AeistFrag.Pessoa;
import pt.aeist.mobile.res.AppController;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

public class RecAdapter extends BaseExpandableListAdapter {
	String url ="http://mobile.aeist.pt/service_churrascos.php";
	 private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	private abstract class MiscContainer {
		String _name;
		String _desc;
		String _urlFoto;
		
		public MiscContainer(String name) {
			_name = name;
		}
		
		public String get_name() {
			return _name;
		}

		public void set_name(String _name) {
			this._name = _name;
		}

		public String get_desc() {
			return _desc;
		}

		public void set_desc(String _desc) {
			this._desc = _desc;
		}

		public String get_urlFoto() {
			return _urlFoto;
		}

		public void set_urlFoto(String _urlFoto) {
			this._urlFoto = _urlFoto;
		}

		public abstract String get_dia();
		
		public abstract void set_dia(String _dia);


	}
	
	private class ServicoContainer extends MiscContainer {
				
		public ServicoContainer(String name) {
			super(name);
		}
		
		@Override
		public String get_dia() {
			 throw new UnsupportedOperationException("Invalid operation.");
		}

		@Override
		public void set_dia(String _dia) {
			 throw new UnsupportedOperationException("Invalid operation.");			
		}
	}	
	
	private class Churrasco extends MiscContainer{
		private String _dia;
		
		public Churrasco() {
			super("");
		}
		
		
		@Override
		public String get_dia() {
			return _dia;
		}
		
		@Override
		public void set_dia(String _dia) {
			this._dia = _dia;
		}

	}	
	
	
	private Activity _act;
	private List<MiscContainer> _container = new ArrayList<MiscContainer>();	
	
	
	private List<Churrasco> _churrascos;
	public RecAdapter(Activity act) {
		_act = act;
		 _churrascos = new ArrayList<Churrasco>();
		//fetch data from web
		AppController.getInstance().initpDialog(_act);
        AppController.getInstance().showpDialog();
		JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                      
                       
                        try {
	                        for (int i = 0; i < response.length(); i++) {
	                        	final Churrasco _churr = new Churrasco();
	                            JSONObject evento = (JSONObject) response.get(i);
	                            _churr.set_name(evento.getString("name"));
	                            _churr.set_desc(evento.getString("desc"));
	                            _churr.set_urlFoto(evento.getString("urlFoto"));
	                            _churr.set_dia(evento.getString("dia"));
	                            _churrascos.add(_churr);
	                        }
                        
                        } catch(JSONException e) { 
                        	e.printStackTrace();
                            Toast.makeText(_act.getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        AppController.getInstance().hidepDialog();
                        RecFrag.getInstance().dumbMove();
                        }
                    }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //VolleyLog.d(TAG, "Erroir: " + error.getMessage());
                        AppController.getInstance().hidepDialog();
                    }
                });
        AppController.getInstance().addToRequestQueue(req);

		//end
		MiscContainer inst = new ServicoContainer("Contactos e informações");
		MiscContainer inst2 = new ServicoContainer("BBQ's");
		_container.add(inst);
		_container.add(inst2);
		
	}

	@Override
	public MiscContainer getChild(int groupPosition, int childPosition) {
		if(groupPosition==0) {
			return _container.get(0);
		} else {
			return _churrascos.get(childPosition);
		}
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	
	@Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
		if(groupPosition==1) {
			convertView = null;
	        final String nome = getChild(groupPosition, childPosition).get_name();
	        final String desc = getChild(groupPosition, childPosition).get_desc();
	        final String imageLink = getChild(groupPosition, childPosition).get_urlFoto();
	       
	            LayoutInflater infalInflater = (LayoutInflater) _act
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.list_pessoa, null);
	        
	 
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
	       
		}
		if(groupPosition==0) {
			convertView = null;
	      
	            LayoutInflater infalInflater = (LayoutInflater) _act
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	       
	            switch(childPosition) {
	            case 0:
	            	convertView = infalInflater.inflate(R.layout.rec_info, null);
	            break;
	            
	            case 1:
	            	convertView = infalInflater.inflate(R.layout.rec_contacts, null);
	            break;
	            
	            default:
	            }
	            
	         
		}
		
		return convertView;
		
    }

	@Override
	public int getChildrenCount(int groupPosition) {
		if(groupPosition==0) {
			return 2;
		} else {
			return _churrascos.size();
		}
	}

	@Override
	public String getGroup(int groupPosition) {
		return _container.get(groupPosition)._name;
	}

	@Override
	public int getGroupCount() {
		return _container.size();
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
            LayoutInflater infalInflater = (LayoutInflater) _act
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
