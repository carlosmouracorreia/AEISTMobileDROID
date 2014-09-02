package pt.aeist.mobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class EventosFrag extends Fragment {
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.eventos_frag, container, false);
	        
	        String[] codeLearnChapters = new String[] { "Android Introduction","Android Setup/Installation","Android Hello World",
	        		"Android Layouts/Viewgroups","Android Activity & Lifecycle","Intents in Android"};
	        
	        ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, codeLearnChapters);
	        //inflate views in Fragment...
	        ListView codeLearnLessons = (ListView)rootView.findViewById(R.id.listView1);
	        codeLearnLessons.setAdapter(codeLearnArrayAdapter);
	        
	        return rootView;
	    }

}
