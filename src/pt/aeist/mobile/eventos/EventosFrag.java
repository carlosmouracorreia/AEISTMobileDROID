package pt.aeist.mobile.eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.aeist.mobile.R;
import pt.aeist.mobile.res.AppController;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EventosFrag extends Fragment {
	private static final String TAG = "EmentasFrag";
	String url ="http://mobile.aeist.pt/service2.php";
	String url2 ="http://mobile.aeist.pt/service3.php";
	// Tag used to cancel the request
	String tag_json_arry = "json_array_req";
	private ExpandableListView listaEventos;
	private EventoExpAdapter eventosListAdapter;
	private Map<Integer,EventoContainer> eventosList;
	
	
	
	public void doAfter(final LayoutInflater inflater) {
		 JsonArrayRequest req2 = new JsonArrayRequest(url2,
	                new Response.Listener<JSONArray>() {
	                    @Override
	                    public void onResponse(JSONArray response) {
	                      //be proud of this one
	                       
	                        try {
	                        	int evAnt = ((JSONObject) response.get(0)).getInt("evento_id");
		                        for (int i = 0,k=0; i < response.length(); i++) {
		                            JSONObject evento = (JSONObject) response.get(i);	
		                            //caso o Evento nao tenha info adicional
		                            if(evento.getString("texto_add").equals("nada")) {
		                            	Log.d(TAG, "it happens");
		                            	evAnt=evento.getInt("evento_id");
		                            	k++;
		                            	continue;
		                            } 
		                            //se o proximo evento for diferente do anterior
		                            if(evAnt!=evento.getInt("evento_id")) {
			                            evAnt=evento.getInt("evento_id");
			                            k++;
		                            }
		                            EventoContainer temp = eventosList.get(k);
		                            temp.addMisc(evento.getString("texto_add"));
		                            eventosList.put(k, temp);
		                        }
	                        
	                        } catch(JSONException e) { 
	                        	e.printStackTrace();
	                            Toast.makeText(getActivity().getApplicationContext(),
	                                    "Error: " + e.getMessage(),
	                                    Toast.LENGTH_LONG).show();
	                        }
	                        //DEPOIS DE RECEBER DADOS JSON
	                        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_eventos, listaEventos,
	                                false);
	                    	listaEventos.addHeaderView(header, null, false);
	                        eventosListAdapter = new EventoExpAdapter(eventosList,getActivity());
	        	          	listaEventos.setAdapter(eventosListAdapter);
	        	          	listaEventos.setOnChildClickListener(new OnChildClickListener() {
	        	          		 
	        	                @Override
	        	                public boolean onChildClick(ExpandableListView parent, View v,
	        	                        int groupPosition, int childPosition, long id) {
	        	                	if(childPosition==1) {
	        	                		int realPos = eventosList.size() - groupPosition - 1;
	        	                		String link = eventosList.get(realPos).getEventoLink();
		        						if(link.contains("http://") || link.contains("https://")) {
		        							Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
		        							startActivity(browserIntent);
		        						}
	        	                	}
	        	                    return false;
	        	                }
	        	            });
	                        AppController.getInstance().hidepDialog();
	                        }
	                    }
	                , new Response.ErrorListener() {
	                    @Override
	                    public void onErrorResponse(VolleyError error) {
	                        VolleyLog.d(TAG, "Erroir: " + error.getMessage());
	                        AppController.getInstance().hidepDialog();
	                    }
	                });
	        AppController.getInstance().addToRequestQueue(req2); 
	}
	
	
	    @Override
	    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	    	
	    	
	        View rootView = inflater.inflate(R.layout.eventos_frag, container, false); 
	        AppController.getInstance().initpDialog(getActivity());
	        AppController.getInstance().showpDialog();

	        JsonArrayRequest req = new JsonArrayRequest(url,
	                new Response.Listener<JSONArray>() {
	                    @Override
	                    public void onResponse(JSONArray response) {
	                      
	                        eventosList = new HashMap<Integer,EventoContainer>();
	                        try {
		                        for (int i = 0; i < response.length(); i++) {
		                        	final EventoContainer chapter = new EventoContainer();
		                            JSONObject evento = (JSONObject) response.get(i);
		                            chapter.setEventoNome(evento.getString("evento_titulo"));
		                            chapter.setEventoDesc(evento.getString("evento_desc"));
		                            chapter.setImageLink(evento.getString("evento_foto"));
		                            chapter.setEventoLink(evento.getString("evento_link"));
		                            chapter.setDate(evento.getString("data"));
		                      	    eventosList.put(i,chapter);   
		                      	    //HOW IN A MILLION YEARS YOU WOULD REMEMBER THIS? HAHAHA
		                      	    //PRIMARY KEYS START AT 1 :D 
		                        }
	                        
	                        } catch(JSONException e) { 
	                        	e.printStackTrace();
	                            Toast.makeText(getActivity().getApplicationContext(),
	                                    "Error: " + e.getMessage(),
	                                    Toast.LENGTH_LONG).show();
	                        }
	                        //DEPOIS DE RECEBER DADOS JSON
	                        doAfter(inflater);
	                        }
	                    }
	                , new Response.ErrorListener() {
	                    @Override
	                    public void onErrorResponse(VolleyError error) {
	                        VolleyLog.d(TAG, "Erroir: " + error.getMessage());
	                        AppController.getInstance().hidepDialog();
	                    }
	                });
	        AppController.getInstance().addToRequestQueue(req);
	        
	        listaEventos = (ExpandableListView)rootView.findViewById(R.id.listView1);
	        return rootView;
	    }
	}
