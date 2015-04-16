package com.insta.annuaire.activity;

import com.insta.annuaire.Exploitation;
import com.insta.gen.annuaire.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class Parc extends MainActivity {
	
	TextView txt;
	ListView ListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Exploitation uneExploitation = new Exploitation(0,"","");
		setContentView(R.layout.parc);
		EditText editText = (EditText) findViewById(R.id.Utilisateur_Identifiant); // Déclaration du champ libre "Exploitation_Nom"
		editText.addTextChangedListener(new TextWatcher() { // Mise en place d'une écoute sur editText 
			@Override
			public void afterTextChanged(Editable e) { // Déclaration de la fonction à éxécuter lors d'une touche pressée
				ListView lv= (ListView)findViewById(R.id.listview); // Déclaration de la ListView (contient la liste des exploitation)
				String textFromEditView = e.toString(); // Récupération du text aprés la touche appuyé
				Exploitation uneExploitation = new Exploitation(0,"",""); // Instanciation d'une Exploitation vide
				String[] from = new String[] {"ID", "Nom", "Ville"}; // Création de l'entête des colonnes de la ListView
				int[] to = new int[] { R.id.Exploitation_ID, R.id.Exploitation_Nom, R.id.Exploitation_Ville}; // Identification des TextView qui recevront les données
				SimpleAdapter adapter = new SimpleAdapter(Parc.this, uneExploitation.getListeExploitationSearch(textFromEditView), R.layout.grid_item, from, to); 
				// Déclaration de l'adapter qui contient toutes les données
				lv.setAdapter(adapter); // Mise en place de l'adapter dans la ListView
				lv.setOnItemClickListener(new OnItemClickListener() { // Instanciation d'une class qui écoute le clique de la souris sur la listeview "lv"
					public void onItemClick(AdapterView adapterView, View view,int position, long id)  // Déclaration de la fonction qui sera éxécuté
					{
						String  U_id = ((TextView)view.findViewById(R.id.Exploitation_ID)).getText().toString(); // Récupération de l'ID de l'Exploitation						
						Intent activityChangeIntent = new Intent(Parc.this, TabExploitation.class); // Chargement d'une nouvelle classe (TabExploitation)
						activityChangeIntent.putExtra("Exploitation_Id",U_id); // Mise en parametre dans l'intent de l'ID de l'utilisateur
						Parc.this.startActivity(activityChangeIntent); // Démarrage de l'activité
					}
				});
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				//nothing needed here...
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//nothing needed here...
			}
		});
		ListView lv= (ListView)findViewById(R.id.listview);
		//récupération du bouton grâce à  son ID
		//Button button = (Button) findViewById(R.id.ButtonEnvoyer);
		// create the grid item mapping
		String[] from = new String[] {"ID", "Nom", "Ville"};
		int[] to = new int[] { R.id.Exploitation_ID, R.id.Exploitation_Nom, R.id.Exploitation_Ville};
		// fill in the grid_item layout
		SimpleAdapter adapter = new SimpleAdapter(this, uneExploitation.getListeExploitation(), R.layout.grid_item, from, to);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView adapterView, View view,
					int position, long id) 
			{
				String  U_id = ((TextView)view.findViewById(R.id.Exploitation_ID)).getText().toString();
				
				Intent activityChangeIntent = new Intent(Parc.this, TabExploitation.class);
				activityChangeIntent.putExtra("Exploitation_Id",U_id);
				Parc.this.startActivity(activityChangeIntent);
			}
		});      
	}
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
	    switch (item.getItemId())
	    {
	        case R.id.back_title:
	            startActivity(new Intent(this, MainActivity.class));
	        return true;
	    }
	  return false;
	}
}
