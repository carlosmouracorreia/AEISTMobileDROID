package pt.aeist.mobile.eventos;

import java.util.HashMap;
import java.util.List;




import java.util.Map;

import pt.aeist.mobile.R;
import pt.aeist.mobile.res.AppController;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

 public class EventoExpAdapter extends BaseExpandableListAdapter {
	 private Map<Integer,EventoContainer> _eventosList;
	 private Activity _act;
	 private static final String TAG = "fuck";
	 private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
		public EventoExpAdapter(Map<Integer,EventoContainer> eventosList,
			FragmentActivity activity) {
			_eventosList = eventosList;
			_act = activity;
	}
		@Override
		public Object getChild(int arg0, int arg1) {
			return _eventosList.get(_eventosList.size() - arg0 - 1).getMisc(arg1);
		}
		@Override
		public long getChildId(int arg0, int arg1) {
			return arg1;
		}
		@Override
	    public View getChildView(int groupPosition, final int childPosition,
	            boolean isLastChild, View convertView, ViewGroup parent) {

			LayoutInflater infalInflater = (LayoutInflater) _act
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			String misc;
			 if (childPosition == 0) {
				 
		            convertView = infalInflater.inflate(R.layout.date_view, null);
		            TextView textView = (TextView) convertView.findViewById(R.id.date);
		            String data = "Publicado a " + getGroup(groupPosition).getDate();
		            textView.setText(data);
		            return convertView;
			 } 
			if(!_eventosList.get(_eventosList.size() - groupPosition - 1).getEventoLink().equals("")) {
				 if (childPosition == 1) {
					 
			            convertView = infalInflater.inflate(R.layout.link_view, null);
			            TextView textView = (TextView) convertView.findViewById(R.id.link);
			            SpannableString content = new SpannableString("Visitar Página");
			            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
			            textView.setText(content);
			            return convertView;
				 } 
				misc = (String) getChild(groupPosition, childPosition-2);
			}
			else { 
				
				misc = (String) getChild(groupPosition, childPosition-1); }
   
			convertView = infalInflater.inflate(R.layout.item_misc, null);
			TextView miscNome = (TextView)convertView.findViewById(R.id.textView1);
			miscNome.setText(misc);
			 return convertView;
	    }
		@Override
		public int getChildrenCount(int arg0) {
			if(!_eventosList.get(_eventosList.size() - arg0 - 1).getEventoLink().equals(""))
				return _eventosList.get(_eventosList.size() - arg0 - 1).getMisc().size()+2;
			return _eventosList.get(_eventosList.size() - arg0 - 1).getMisc().size()+1;
		}
		@Override
		public EventoContainer getGroup(int arg0) {
			return _eventosList.get(_eventosList.size() - arg0 - 1);
		}
		@Override
		public int getGroupCount() {
			return _eventosList.size();
		}
		@Override
		public long getGroupId(int arg0) {
			return arg0;
		}
		
		@Override
	    public View getGroupView(int groupPosition, boolean isExpanded,
	            View convertView, ViewGroup parent) {
	        EventoContainer evento = getGroup(groupPosition);
	        if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) _act
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.listitem, null);
	        }
	 
	        if (imageLoader == null)
				imageLoader = AppController.getInstance().getImageLoader();
			
			NetworkImageView thumbNail = (NetworkImageView) convertView
					.findViewById(R.id.thumbnail);
			thumbNail.setDefaultImageResId(R.drawable.loader);
			thumbNail.setErrorImageResId(R.drawable.loader);
			TextView eventoNome = (TextView)convertView.findViewById(R.id.textView1);
			TextView eventoDesc = (TextView)convertView.findViewById(R.id.textView2);
	 
			eventoNome.setText(evento.getEventoNome());
			eventoDesc.setText(evento.getEventoDesc());
			thumbNail.setImageUrl(evento.getImageLink(), imageLoader);
	        return convertView;
	    }
		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}

		

    } 
    	
