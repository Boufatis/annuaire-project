package com.insta.annuaire.activity;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import com.insta.annuaire.Utilisateur;
import com.insta.gen.annuaire.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText editTxt;
	TextView txtVue;
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accueil);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {            	
                // Perform action on click    
            	TextView lblInfoConnect = (TextView)findViewById(R.id.lblInfoConnect);
            	EditText Utilisateur_Identifiant = (EditText)findViewById(R.id.Utilisateur_Identifiant);
            	EditText Utilisateur_Password = (EditText)findViewById(R.id.Utilisateur_Password);
            	if(!connectionAvailable())
            	{    
            		lblInfoConnect.setText("Veuillez vérifier votre connexion à internet.");
            	}
            	else
            	{
            		if(Utilisateur_Identifiant.getText().toString().equals("") || Utilisateur_Password.getText().toString().equals(""))
	            	{
	            		lblInfoConnect.setText("Erreur, vous n'avez indiquer aucune information.");            		
	            	}
	            	else
	            	{
	            		Utilisateur unUtilisateur = new Utilisateur();
	            		switch(unUtilisateur.checkIdentifiant(Utilisateur_Identifiant.getText().toString(), Utilisateur_Password.getText().toString()))
	            		{
		            		case 1:
		            			lblInfoConnect.setText("L'utilisateur est bien identifié.");
		            			Intent activityChangeIntent = new Intent(MainActivity.this, Parc.class);
		                        MainActivity.this.startActivity(activityChangeIntent);
		            			break;
		            		case 0:
		            			lblInfoConnect.setText("Les identifiants renseignés ne sont pas correct.");
		            			break;
	            		}	            		
	            	}
            	}
            }
        });
	}


	private boolean connectionAvailable() {
	    boolean connected = false; // Déclaration du booléen (valeur = false)
	    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); // Instanciation de la classe connectivityManager
	    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
	            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {// Test si le téléphone a accés à internet
	        connected = true; // Changement de la valeur du booléen a true
	    }
	    return connected; // Retournne la variable connected
	}


}
