package pt.aeist.mobile.servicos;

import java.util.ArrayList;
import java.util.List;

import pt.aeist.mobile.R;
import pt.aeist.mobile.info.AeistFrag.Pessoa;
import pt.aeist.mobile.res.AppController;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class MainServiceAdapter extends BaseExpandableListAdapter {
	
	private class ServicoContainer {
		String _name;
		String _desc;
		String _urlFoto;
		
		public ServicoContainer(String name,String desc,String urlFoto) {
			_name = name;
			_desc = desc;
			_urlFoto = urlFoto;
		}
	}	
	private Activity _act;
	private List<ServicoContainer> _container = new ArrayList<ServicoContainer>();	
	public MainServiceAdapter(Activity act) {
		_act = act;
		ServicoContainer inst = new ServicoContainer("Sobre","Cenasssssss e cenas88","");
		ServicoContainer inst2 = new ServicoContainer("Preços","Muitos Preços vão figurar aqui.","");
		_container.add(inst);
		_container.add(inst2);
		
	}

	@Override
	public ServicoContainer getChild(int groupPosition, int childPosition) {
		return _container.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	
	@Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final String nome = getChild(groupPosition, childPosition)._name;
        final String desc = getChild(groupPosition, childPosition)._desc;
        //final String imageLink = getChild(groupPosition, childPosition).imageLink;
        
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) _act
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_pessoa, null);
        }
 
       /* if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		thumbNail.setDefaultImageResId(R.drawable.loader);
		thumbNail.setErrorImageResId(R.drawable.loader); */
		TextView eventoNome = (TextView)convertView.findViewById(R.id.textView1);
		TextView eventoDesc = (TextView)convertView.findViewById(R.id.textView2);
		
		eventoNome.setText(nome);
		eventoDesc.setText(desc);
		//thumbNail.setImageUrl(imageLink, imageLoader);
        return convertView;
    }

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
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
