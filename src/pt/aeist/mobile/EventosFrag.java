package pt.aeist.mobile;


import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class EventosFrag extends Fragment {
	
	public class eventoContainer {
		String eventoNome;
		String eventoDesc;
		String eventoLink;
		String imageLink;
	}
	private String jsonResult;
	private String url = "http://mobile.aeist.pt/service.php";
	NewsAdapter eventosListAdapter;
	private ListView listaEventos;
	 public void openDialog() {
    	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    			 EventosFrag.this.getActivity());
   
  			// set title
  			alertDialogBuilder.setTitle(R.string.app_name);
   
  			// set dialog message
  			alertDialogBuilder
  				.setMessage(R.string.dialogo_net)
  				.setCancelable(false)
  				.setPositiveButton("Sair",new DialogInterface.OnClickListener() {
  					public void onClick(DialogInterface dialog,int id) {
  						EventosFrag.this.getActivity().finish();
  					}
  				  })
  				.setNegativeButton("Definições",new DialogInterface.OnClickListener() {
  		            public void onClick(DialogInterface dialog,int id) {
  		                Intent intent = new Intent(Settings.ACTION_SETTINGS);
  		                intent.addCategory(Intent.CATEGORY_LAUNCHER);           
  		                startActivity(intent);
  		              EventosFrag.this.getActivity().finish();
  		            }
  		        });
  				
   
  				AlertDialog alertDialog = alertDialogBuilder.create();
   
  				alertDialog.show();
  			}
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.eventos_frag, container, false);
	        
	        ConnectivityManager cm =
                    (ConnectivityManager) EventosFrag.this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();

                if (netInfo == null || !netInfo.isConnectedOrConnecting()) 
               {               
                    openDialog();
               }
                else { accessWebService(); }
	        listaEventos = (ListView)rootView.findViewById(R.id.listView1);
	        
	        
	        return rootView;
	    }
	    //webservice thread - saca info da BD
	    private class JsonReadTask extends AsyncTask<String, Void, String> {
	    	
	    	private ProgressDialog pdia;
	    	
	    	@Override
	    	protected void onPreExecute(){ 
	    	   super.onPreExecute();
	    	   
	    	        pdia = new ProgressDialog(EventosFrag.this.getActivity());
	    	        pdia.setMessage("Carregando...");
	    	        pdia.show();    
	    	}
	    	
	    	  @Override
	    	  protected String doInBackground(String... params) {
	    	   HttpClient httpclient = new DefaultHttpClient();
	    	   HttpPost httppost = new HttpPost(params[0]);
	    	   try {
	    	    HttpResponse response = httpclient.execute(httppost);
	    	    jsonResult = inputStreamToString(
	    	      response.getEntity().getContent()).toString();
	    	   }
	    	 
	    	   catch (ClientProtocolException e) {
	    	    e.printStackTrace();
	    	   } catch (IOException e) {
	    	    e.printStackTrace();
	    	   }
	    	   return null;
	    	  }
	    
	    	  private StringBuilder inputStreamToString(InputStream is) {
	    		   String rLine = "";
	    		   StringBuilder answer = new StringBuilder();
	    		   BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    		 
	    		   try {
	    		    while ((rLine = rd.readLine()) != null) {
	    		     answer.append(rLine);
	    		    }
	    		   }
	    		 
	    		   catch (IOException e) {
	    		    // e.printStackTrace();
	    		   }
	    		   return answer;
	    		  }
	    	  @Override
	    	  protected void onPostExecute(String result){
	    	     super.onPostExecute(result);
	    	          pdia.dismiss();
	    	          eventosListAdapter = new NewsAdapter();
	    	          listaEventos.setAdapter(eventosListAdapter);
	    	          
	    	          listaEventos.setOnItemClickListener(new OnItemClickListener() {
	    		        	//Ao ser clicado  um evento na lista
	    					@Override
	    					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	    							long arg3) {
	    						
	    						eventoContainer chapter = eventosListAdapter.getEventoNr(arg2);
	    						if(chapter.eventoLink.contains("http://")) {
	    							Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chapter.eventoLink));
	    							startActivity(browserIntent);
	    						}
	    					}
	    				});
	    	  }
	    		  
	    		 }// end async task
	    //ennd of webservice thread
	    public void accessWebService() {
	    	  JsonReadTask task = new JsonReadTask();
	    	  // passes values for the urls string array
	    	  task.execute(new String[] { url });
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
				//Sao desenhados os views em lista nesta seccao.
				if(arg1==null)
				{
					LayoutInflater inflater = (LayoutInflater) EventosFrag.this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					arg1 = inflater.inflate(R.layout.listitem, arg2,false);
				}
				ImageLoader imgLoader = new ImageLoader(EventosFrag.this.getActivity().getApplicationContext());
				int loader = R.drawable.loader;
				TextView eventoNome = (TextView)arg1.findViewById(R.id.textView1);
				TextView eventoDesc = (TextView)arg1.findViewById(R.id.textView2);
				ImageView eventoFtg = (ImageView)arg1.findViewById(R.id.imageView1);
				eventoContainer chapter = eventosList.get(arg0);
				
				eventoNome.setText(chapter.eventoNome);
				eventoDesc.setText(chapter.eventoDesc);
				imgLoader.DisplayImage(chapter.imageLink, loader, eventoFtg);
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
	    	
	    	//acede à base de dados
	    	
	    	 try {
	    		   JSONObject jsonResponse = new JSONObject(jsonResult);
	    		   JSONArray jsonMainNode = jsonResponse.optJSONArray("eventos");
	    	
		   for (int i = 0; i < jsonMainNode.length(); i++) {
			   eventoContainer chapter = new eventoContainer();
			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
			    chapter.eventoNome = jsonChildNode.optString("evento_titulo");
			    chapter.eventoDesc = jsonChildNode.optString("evento_desc");
			    chapter.eventoLink = jsonChildNode.optString("evento_link");
			    chapter.imageLink = jsonChildNode.optString("evento_foto");
			    eventosList.add(chapter);
			   }
			  } catch (JSONException e) {
			   Toast.makeText(EventosFrag.this.getActivity().getApplicationContext(), "Error" + e.toString(),
			     Toast.LENGTH_SHORT).show();
			  }
	    	
	    	return eventosList;
	    	
	    }

}
