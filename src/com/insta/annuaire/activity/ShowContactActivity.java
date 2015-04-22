package com.insta.annuaire.activity;

import com.insta.annuaire.CircleTransform;
import com.insta.annuaire.Contact;
import com.insta.annuaire.dao.DAOContact;
import com.insta.gen.annuaire.R;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowContactActivity extends Activity {
	TextView telephone;
	TextView mail;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcontact);
        Bundle extra = getIntent().getExtras();
        int idContact = extra.getInt("contactid");
        DAOContact daoContact = new DAOContact();
        
        Contact contact = new Contact();
		try {
			contact = daoContact.getContactById(idContact);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        ImageView photo = (ImageView)findViewById(R.id.photoContact);

        TextView prenom = (TextView)findViewById(R.id.prenom);
        TextView dateNaissance = (TextView)findViewById(R.id.datenaissance);
        TextView profil = (TextView)findViewById(R.id.profil);
        mail = (TextView)findViewById(R.id.mail);
        telephone = (TextView)findViewById(R.id.telephone);
        TextView promo = (TextView)findViewById(R.id.promo);
        
        prenom.setText(contact.getPrenom()+" "+ contact.getNom().toUpperCase());
        dateNaissance.setText(contact.getDateNaissance());
        profil.setText(contact.getProfil());
        mail.setText(contact.getMail());
        telephone.setText(contact.getTelephone());
        promo.setText(contact.getPromo());
        
        if(!contact.getPhoto().isEmpty())
        {
			Picasso.with(this).load(contact.getPhoto()).transform(new CircleTransform()).into(photo);
        }else{
        	Picasso.with(this).load(R.drawable.user).transform(new CircleTransform()).into(photo);
        }
        
	}
	
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.menu, menu);
	        return true;
	    }
	  
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	  	switch (item.getItemId()) {
	  	case R.id.menu_call:
	  		String tel = telephone.getText().toString();
	  		if(!tel.equals("Inconnu")){
	  			startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+tel)));
	  		}else{
	  			
	  			Toast.makeText(this, "Numéro de téléphone non renseigné", Toast.LENGTH_SHORT).show();
	  		}
	  		
	  	case R.id.menu_sms:
	  		String telMsg = telephone.getText().toString();
	  		if(!telMsg.equals("Inconnu")){
	  			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"+telMsg)));
	  		}else{
	  			
	  			Toast.makeText(this, "Numéro de téléphone non renseigné", Toast.LENGTH_SHORT).show();
	  		}
	  		
	  	case R.id.menu_mail:
	  		String mailSend = mail.getText().toString();
	  		startActivity(new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"+mailSend)));
	  	case R.id.menu_back:
	  		Intent ListActivity = new Intent(ShowContactActivity.this, ListContactActivity.class);
	  		ShowContactActivity.this.startActivity(ListActivity);
	  		return true;
	  	default:
	  		return super.onOptionsItemSelected(item);
	  	}
	  }

}
