package pt.aeist.mobile;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class EmentasFrag extends Fragment {
	 
	RequestQueue queue = null;
	String url ="http://www.google.com";
	
	
	
	
	
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.ementas_frag, container, false);
	        final TextView listaEventos = (TextView)rootView.findViewById(R.id.listView1);
	        queue = Volley.newRequestQueue(EmentasFrag.this.getActivity());
	        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
	                new Response.Listener<String>() {
	        @Override
	        public void onResponse(String response) {
	            // Display the first 500 characters of the response string.
	            listaEventos.setText("Response is: "+ response.substring(0,500));
	        }
	    }, new Response.ErrorListener() {
	        @Override
	        public void onErrorResponse(VolleyError error) {
	            listaEventos.setText("oops.");
	        }
	    });
	        queue.add(stringRequest);
	        return rootView;
	    }
	}
