package com.insta.annuaire.activity;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.insta.annuaire.CircleTransform;
import com.insta.annuaire.Contact;
import com.insta.gen.annuaire.R;
import com.squareup.picasso.Picasso;

public class ContactAdapter extends BaseAdapter {
	
	private Context context;
	private List<Contact> contact;
	
	@Override
    public int getCount() {
        
        return this.contact.size();
    }

    @Override
    public Contact getItem(int arg0) {
        
        return this.contact.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }
    
    public ContactAdapter(Context context,  List<Contact> contact) {  
    	this.contact = contact;
        this.context = context;
    }
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
        convertView = inflater.inflate(R.layout.rowcontact, parent, false); 
        holder.fullName  = (TextView) convertView.findViewById(R.id.fullname);
        holder.promo = (TextView) convertView.findViewById(R.id.libelle);
        holder.id = (TextView) convertView.findViewById(R.id.hiddenId);
        holder.photo = (ImageView) convertView.findViewById(R.id.icon);
       
        
        String textLibelle = "";
        
        if (getItem(position).getProfil().equals("etudiant")) {
        	textLibelle = getItem(position).getPromo();
		} else if (getItem(position).getProfil().equals("professeur")) {
			textLibelle = "Professeur";
		} else if (getItem(position).getProfil().equals("administration")) {
			textLibelle = "Administration";
		}
        
        holder.fullName.setText(getItem(position).getPrenom() + " " + getItem(position).getNom().toUpperCase());	
        holder.promo.setText(textLibelle);
        holder.id.setText("" + getItem(position).getId());
        convertView.setTag(holder);
        
    	if(convertView != null ){    	
    		if(!getItem(position).getPhoto().isEmpty())
            {
    			Picasso.with(this.context).load(getItem(position).getPhoto()).transform(new CircleTransform()).into(holder.photo);
            }else{
            	Picasso.with(this.context).load(R.drawable.user).transform(new CircleTransform()).into(holder.photo);
            }
    	}
    	
        return convertView;
    }
 
    
    static class ViewHolder {
    	  TextView fullName;
    	  TextView promo;
    	  TextView id;
    	  ImageView photo;
    	  int position;
    	}
    
    
}


