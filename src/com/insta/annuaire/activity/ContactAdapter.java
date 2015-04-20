package com.insta.annuaire.activity;

import java.util.List;

import com.insta.gen.annuaire.R;
import com.insta.annuaire.Contact;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contact> {
 
    private Integer[] tab_images_pour_la_liste = {
      R.drawable.insta };
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
          getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
        View rowView = inflater.inflate(R.layout.rowcontact, parent, false);
 
        TextView fullname = (TextView) rowView.findViewById(R.id.fullname);
        TextView libelle = (TextView) rowView.findViewById(R.id.libelle);
        TextView hiddenId = (TextView) rowView.findViewById(R.id.hiddenId);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        
        String textLibelle = "";
        
        if (getItem(position).getProfil().equals("etudiant")) {
        	textLibelle = getItem(position).getPromo();
		} else if (getItem(position).getProfil().equals("professeur")) {
			textLibelle = "Professeur";
		} else if (getItem(position).getProfil().equals("administration")) {
			textLibelle = "Administration";
		}
        
        fullname.setText(getItem(position).getPrenom() + " " + getItem(position).getNom());	
        libelle.setText(textLibelle);
        hiddenId.setText("" + getItem(position).getId());
        
        if(convertView == null )
          imageView.setImageResource(tab_images_pour_la_liste[0]);
        else
          rowView = (View)convertView;
 
        return rowView;
    }
 
    public ContactAdapter(Context context,  List<Contact> contact) {
        super(context, R.layout.rowcontact, contact);
    }
}


