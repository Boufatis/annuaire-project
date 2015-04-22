package com.insta.annuaire.activity;

import com.insta.annuaire.CircleTransform;
import com.insta.annuaire.Contact;
import com.insta.annuaire.dao.DAOContact;
import com.insta.gen.annuaire.R;
import com.squareup.picasso.Picasso;

import Enum.EnumOrder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
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

public class ListContactActivity extends Activity{
	
	private ListView lvContact;
	ContactAdapter adaptateur;
	EditText inputSearch;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcontact);
        DAOContact daoContact = new DAOContact();
        lvContact = (ListView)findViewById(R.id.listViewContact);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        adaptateur = new ContactAdapter(this,daoContact.getListContact());
        lvContact.setAdapter(adaptateur);
        lvContact.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView adapterView, View view,int position, long id) 
			{
				String  contactid = ((TextView)view.findViewById(R.id.hiddenId)).getText().toString();
				int idContact = Integer.parseInt(contactid);
				DAOContact daoContact = new DAOContact();
				Contact contact = new Contact();
				try {
					contact = daoContact.getContactById(idContact);
				} catch (Exception e) {
					e.printStackTrace();
				}
//				Toast.makeText(ListContactActivity.this, contact.getNom(), Toast.LENGTH_SHORT).show();
		        LayoutInflater factory = LayoutInflater.from(ListContactActivity.this);
		        final View alertDialogView = factory.inflate(R.layout.showcontact, null);
		        AlertDialog.Builder adb = new AlertDialog.Builder(ListContactActivity.this);
		        adb.setView(alertDialogView);
		        adb.setTitle(contact.getPrenom()+ " "+contact.getNom().toUpperCase());       
		        ImageView photo = (ImageView)alertDialogView.findViewById(R.id.photoContact);
		        if(!contact.getPhoto().isEmpty())
		        {
					Picasso.with(ListContactActivity.this).load(contact.getPhoto()).transform(new CircleTransform()).into(photo);
		        }else{
		        	Picasso.with(ListContactActivity.this).load(R.drawable.user).transform(new CircleTransform()).into(photo);
		        }
		        //adb.setIcon(android.R.drawable.ic_dialog_alert);
		        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {

		          } });
		        adb.show();
			}
        });
        inputSearch.addTextChangedListener(new TextWatcher() {
            
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
            	lvContact = (ListView)findViewById(R.id.listViewContact);
                inputSearch = (EditText) findViewById(R.id.inputSearch);
                DAOContact daoContact = new DAOContact();
                adaptateur = new ContactAdapter(ListContactActivity.this,daoContact.getListContactByCriteria(cs.toString(), "all", EnumOrder.ASC));
                lvContact.setAdapter(adaptateur);
                lvContact.setOnItemClickListener(new OnItemClickListener() {
        			public void onItemClick(AdapterView adapterView, View view,int position, long id) 
        			{
        				String  contactid = ((TextView)view.findViewById(R.id.hiddenId)).getText().toString();
        				int idContact = Integer.parseInt(contactid);
        				DAOContact daoContact = new DAOContact();
        				Contact contact = new Contact();
        				try {
        					contact = daoContact.getContactById(idContact);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
//        				Toast.makeText(ListContactActivity.this, contact.getNom(), Toast.LENGTH_SHORT).show();
        		        LayoutInflater factory = LayoutInflater.from(ListContactActivity.this);
        		        final View alertDialogView = factory.inflate(R.layout.showcontact, null);
        		        AlertDialog.Builder adb = new AlertDialog.Builder(ListContactActivity.this);
        		        adb.setView(alertDialogView);
        		        adb.setTitle(contact.getPrenom()+ " "+contact.getNom().toUpperCase());       
        		        ImageView photo = (ImageView)alertDialogView.findViewById(R.id.photoContact);
        		        if(!contact.getPhoto().isEmpty())
        		        {
        					Picasso.with(ListContactActivity.this).load(contact.getPhoto()).transform(new CircleTransform()).into(photo);
        		        }else{
        		        	Picasso.with(ListContactActivity.this).load(R.drawable.user).transform(new CircleTransform()).into(photo);
        		        }
        		        //adb.setIcon(android.R.drawable.ic_dialog_alert);
        		        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        		            public void onClick(DialogInterface dialog, int which) {

        		          } });
        		        adb.show();
        			}
                });
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
        });
	}
}
