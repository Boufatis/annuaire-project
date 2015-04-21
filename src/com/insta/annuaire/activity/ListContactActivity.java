package com.insta.annuaire.activity;

import com.insta.annuaire.CircleTransform;
import com.insta.annuaire.Contact;
import com.insta.annuaire.dao.DAOContact;
import com.insta.gen.annuaire.R;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListContactActivity extends ListActivity{
	
	
	public static ListContactActivity context;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DAOContact daoContact = new DAOContact();
        ContactAdapter adaptateur = new ContactAdapter(this,daoContact.getListContact());
        setListAdapter(adaptateur);
	}
	
	@Override
	protected void onListItemClick (ListView l, View v, int position, long id) {
		
		String contactid = ((TextView)v.findViewById(R.id.hiddenId)).getText().toString();
		int idContact = Integer.parseInt(contactid);
		DAOContact daoContact = new DAOContact();
		Contact contact = new Contact();
		try {
			contact = daoContact.getContactById(idContact);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Toast.makeText(ListContactActivity.this, contact.getNom(), Toast.LENGTH_SHORT).show();
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.showcontact, null);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(alertDialogView);
        adb.setTitle(contact.getPrenom()+ " "+contact.getNom().toUpperCase());       
        ImageView photo = (ImageView)alertDialogView.findViewById(R.id.photoContact);
        if(!contact.getPhoto().isEmpty())
        {
			Picasso.with(this).load(contact.getPhoto()).transform(new CircleTransform()).into(photo);
        }else{
        	Picasso.with(this).load(R.drawable.user).transform(new CircleTransform()).into(photo);
        }
        //adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

          } });
        adb.show();
	}
}
