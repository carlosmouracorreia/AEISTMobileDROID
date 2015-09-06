package pt.aeist.mobile.servicos;
 
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
 
public class ServListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Servico> servicos;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
    public ServListAdapter(FragmentActivity activity, List<Servico> servicos) {
        this.activity = activity;
        this.servicos = servicos;
    }
 
    @Override
    public int getCount() {
        return servicos.size();
    }
 
    @Override
    public Object getItem(int location) {
        return servicos.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.servicos_row, null);
 
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
 
        // getting movie data for the row
        Servico m = servicos.get(position);
 
        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
         
        // title
        title.setText(m.getTitle());
         
        // Desc
        rating.setText(m.getDesc());
         
 
        return convertView;
    }
 
}