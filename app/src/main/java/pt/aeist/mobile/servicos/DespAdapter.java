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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class DespAdapter extends BaseExpandableListAdapter {
	
	private class ServicoContainer {
		String _name;
		
		public ServicoContainer(String name) {
			_name = name;
		}
	}	
	private Activity _act;
	private List<ServicoContainer> _container = new ArrayList<ServicoContainer>();	
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	public DespAdapter(Activity act) {
		_act = act;
		ServicoContainer inst = new ServicoContainer("Competição");
		ServicoContainer inst2 = new ServicoContainer("Modalidades");
		ServicoContainer inst3 = new ServicoContainer("Piscina");
		ServicoContainer inst4 = new ServicoContainer("Horários/Contactos");
		ServicoContainer inst5 = new ServicoContainer("Sobre");

		_container.add(inst);
		_container.add(inst2);
		_container.add(inst3);
		_container.add(inst4);
		_container.add(inst5);
		
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
		  LayoutInflater infalInflater = (LayoutInflater) _act
                  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(groupPosition==0) {
			convertView = null;
		       
            switch(childPosition) {
            case 0:
            	convertView = infalInflater.inflate(R.layout.under_construction, null);
            break;
            
            default:
            }
	            
	         
		}
		if(groupPosition==1) {
			convertView = null;
	       
	            switch(childPosition) {
	            case 0:
	            	convertView = infalInflater.inflate(R.layout.under_construction, null);
	            break;
	            
	            default:
	            }
	            
	         
		}
		if(groupPosition==2) {
			convertView = null;
		       
            switch(childPosition) {
            case 0:
            	convertView = infalInflater.inflate(R.layout.under_construction, null);
            break;
            
            default:
            }
	         
		}
		if(groupPosition==3) {
			convertView = null;
	            switch(childPosition) {
	            case 0:
	            	convertView = infalInflater.inflate(R.layout.desp_schedule_row, null);
	            break;
	            case 1:
	            	convertView = infalInflater.inflate(R.layout.desp_contacts_row, null);
	            break;
	            
	            default:
	            }
	            
	         
		}
		
		if(groupPosition==4) {
			convertView = null;
	            switch(childPosition) {
	            case 0:
	            	convertView = infalInflater.inflate(R.layout.desp_about_row, null);
	            break;
	            default:
	            }
	            
	         
		}
        return convertView;
    }

	@Override
	public int getChildrenCount(int groupPosition) {
		if(groupPosition==0) {
			return 1;
		} if(groupPosition==1) {
			return 1;
		} else if(groupPosition==2) {
			return 1;
		} else if(groupPosition==3) {
			return 2;
		} else if(groupPosition==4) {
			return 1;
		}
		else {
			return 0;
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