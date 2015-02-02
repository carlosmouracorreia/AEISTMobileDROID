package pt.aeist.mobile.eventos;

import java.util.List;



import pt.aeist.mobile.R;
import pt.aeist.mobile.res.AppController;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

 public class EventoAdapter extends BaseAdapter {
	 private List<EventoContainer> _eventosList;
	 private Activity _act;
	 private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
		public EventoAdapter(List<EventoContainer> eventosList,
			FragmentActivity activity) {
			_eventosList = eventosList;
			_act = activity;
	}

		@Override
		public int getCount() {
			return _eventosList.size();
		}

		@Override
		public EventoContainer getItem(int arg0) {
			return _eventosList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			//Sao desenhados os views em lista nesta seccao.

			// Retrieves an image specified by the URL, displays it in the UI.
			
			
			if(arg1==null)
			{
				LayoutInflater inflater = (LayoutInflater) _act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				arg1 = inflater.inflate(R.layout.listitem, arg2,false);
			}
			if (imageLoader == null)
				imageLoader = AppController.getInstance().getImageLoader();
			
			NetworkImageView thumbNail = (NetworkImageView) arg1
					.findViewById(R.id.thumbnail);
			TextView eventoNome = (TextView)arg1.findViewById(R.id.textView1);
			TextView eventoDesc = (TextView)arg1.findViewById(R.id.textView2);
			//final ImageView eventoFtg = (ImageView)arg1.findViewById(R.id.imageView1);
			EventoContainer chapter = _eventosList.get(arg0);
			
			eventoNome.setText(chapter.getEventoNome());
			eventoDesc.setText(chapter.getEventoDesc());
			thumbNail.setImageUrl(chapter.getImageLink(), imageLoader);
			//eventoFtg.setImageBitmap(chapter.image);
			
			return arg1;
		}
		
		public EventoContainer getEventoNr(int position)
		{
			return _eventosList.get(position);
		}

    } 
    	
