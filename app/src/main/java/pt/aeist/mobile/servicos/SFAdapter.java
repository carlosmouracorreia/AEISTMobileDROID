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

public class SFAdapter extends BaseExpandableListAdapter {
	
	private class ServicoContainer {
		String _name;
		
		public ServicoContainer(String name) {
			_name = name;
		}
	}	
	private Activity _act;
	private List<ServicoContainer> _container = new ArrayList<ServicoContainer>();	
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	public SFAdapter(Activity act) {
		_act = act;
		ServicoContainer inst = new ServicoContainer("Preçário");
		ServicoContainer inst2 = new ServicoContainer("Sebentas");
		ServicoContainer inst3 = new ServicoContainer("Sala de Estudo");
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
	            	convertView = infalInflater.inflate(R.layout.sf_price, null);
	            	 TextView title = (TextView) convertView.findViewById(R.id.textView);
	            	 title.setText("Impressões, fotocópias, digitalização e encadernação");
	            break;
	            case 1:
	            	convertView = infalInflater.inflate(R.layout.sf_go_pdf, null);
	            break;
	            case 2:
	            	convertView = infalInflater.inflate(R.layout.sf_price, null);
	            	 TextView title2 = (TextView) convertView.findViewById(R.id.textView);
	            	 title2.setText("Folhas de testes, material de papelaria, material académico, cacifos e quotas");
	            break;
	            case 3:
	            	convertView = infalInflater.inflate(R.layout.sf_go_pdf, null);
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
	            	convertView = infalInflater.inflate(R.layout.sf_studyroom_row, null);
	            	 if (imageLoader == null)
	                     imageLoader = AppController.getInstance().getImageLoader();
	                 NetworkImageView thumbNail = (NetworkImageView) convertView
	                         .findViewById(R.id.thumbnail);
	                 TextView title = (TextView) convertView.findViewById(R.id.title);
	                 TextView rating = (TextView) convertView.findViewById(R.id.rating);
	                 
	                 thumbNail.setImageUrl("http://193.136.198.90/aeist/mediaRep/editors/AEIST/InfoPelouros/Informacao/FotoSalaDeEstudo.jpg", imageLoader);
	                 
	                 // title
	                 title.setText("Das 8h ás 21h");
	                  
	                 // Desc
	                 rating.setText("Este espaço tem todas as condições necessárias para um dia de estudo,casas de banho próximas, vending machine e máquina de café. A lotação é de 42 pessoas. Durante a época de exames a sala está aberta até ás 3h da manha!");
	            break;
	            
	            default:
	            }
	            
	         
		}
		if(groupPosition==3) {
			convertView = null;
	            switch(childPosition) {
	            case 0:
	            	convertView = infalInflater.inflate(R.layout.sf_schedule_row, null);
	            break;
	            case 1:
	            	convertView = infalInflater.inflate(R.layout.sf_contacts_row, null);
	            break;
	            
	            default:
	            }
	            
	         
		}
		
		if(groupPosition==4) {
			convertView = null;
	            switch(childPosition) {
	            case 0:
	            	convertView = infalInflater.inflate(R.layout.sf_about_row, null);
	            break;
	            default:
	            }
	            
	         
		}
        return convertView;
    }

	@Override
	public int getChildrenCount(int groupPosition) {
		if(groupPosition==0) {
			return 4;
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
