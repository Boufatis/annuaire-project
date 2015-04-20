package com.insta.annuaire.activity;

import com.insta.gen.annuaire.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListContactActivity extends ListActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        String[] values = new String[] { "Device",
            "Géo localisation", "Accéléromètre",
            "Navigateur internet", "Dialogues", "Album photos",
            "Connexion réseau", "Gestion des fichiers",
            "Carnet de contacts" };
 
        ContactAdapter adaptateur = new ContactAdapter(this, values);
        setListAdapter(adaptateur);
	}

}
