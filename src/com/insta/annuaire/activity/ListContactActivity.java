package com.insta.annuaire.activity;

import com.insta.annuaire.dao.DAOContact;
import com.insta.gen.annuaire.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        context = this;
        
        ContactAdapter adaptateur = new ContactAdapter(this,daoContact.getListContact());
        setListAdapter(adaptateur);
 
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new OnItemClickListener() { // Instanciation d'une class qui écoute le clique de la souris sur la listeview "lv"
			public void onItemClick(AdapterView adapterView, View view,int position, long id)  // Déclaration de la fonction qui sera éxécuté
			{
//				String  contactId = ((TextView)view.findViewById(R.id.hiddenId)).getText().toString(); // Récupération de l'ID de l'Exploitation						
				showContactDetails(ListContactActivity.context);
			}

			private void showContactDetails(Context context) {
				AlertDialog dlgAlert  = new AlertDialog.Builder(context).create();
				dlgAlert.setMessage("This is an alert with no consequence");
				dlgAlert.setTitle("App Title");
				//dlgAlert.setPositiveButton("OK", null);
				dlgAlert.setCancelable(true);
				dlgAlert.show();
			}
		});
	}

}
