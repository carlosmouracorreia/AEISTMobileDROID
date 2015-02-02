package pt.aeist.mobile.eventos;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EventosFrag extends Fragment {
	private static final String TAG = "EmentasFrag";
	String url ="http://mobile.aeist.pt/service2.php";
	// Tag used to cancel the request
	String tag_json_arry = "json_array_req";
	private ListView listaEventos;
	private EventoAdapter eventosListAdapter;
	private List<EventoContainer> eventosList;
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	    	
	    	
	        View rootView = inflater.inflate(R.layout.eventos_frag, container, false); 
	        AppController.getInstance().initpDialog(getActivity());
	        AppController.getInstance().showpDialog();

	        JsonArrayRequest req = new JsonArrayRequest(url,
	                new Response.Listener<JSONArray>() {
	                    @Override
	                    public void onResponse(JSONArray response) {
	                        Log.d(TAG, response.toString());
	                        eventosList = new ArrayList<EventoContainer>();
	                        try {
		                        for (int i = 0; i < response.length(); i++) {
		                        	final EventoContainer chapter = new EventoContainer();
		                            JSONObject evento = (JSONObject) response.get(i);
		                            chapter.setEventoNome(evento.getString("evento_titulo"));
		                            chapter.setEventoDesc(evento.getString("evento_desc"));
		                            chapter.setImageLink(evento.getString("evento_foto"));
		                            chapter.setEventoLink(evento.getString("evento_link"));
		                      	    eventosList.add(chapter);   
		                        }
	                        
	                        } catch(JSONException e) { 
	                        	e.printStackTrace();
	                            Toast.makeText(getActivity().getApplicationContext(),
	                                    "Error: " + e.getMessage(),
	                                    Toast.LENGTH_LONG).show();
	                        }
	                        //DEPOIS DE RECEBER DADOS JSON
	                        AppController.getInstance().hidepDialog();
	                        eventosListAdapter = new EventoAdapter(eventosList,getActivity());
	        	          	listaEventos.setAdapter(eventosListAdapter);
	        	          	listaEventos.setOnItemClickListener(new OnItemClickListener() {
	        		        	//Ao ser clicado  um evento na lista
	        					@Override
	        					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	        							long arg3) {
	        						
	        						EventoContainer chapter = eventosListAdapter.getEventoNr(arg2);
	        						if(chapter.getEventoLink().contains("http://")) {
	        							Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chapter.getEventoLink()));
	        							startActivity(browserIntent);
	        						}
	        					}
	        				}); 
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
	        listaEventos = (ListView)rootView.findViewById(R.id.listView1);
	        return rootView;
	    }
	}
