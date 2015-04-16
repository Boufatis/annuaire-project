package com.insta.annuaire.dao;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAOUtilisateur extends DAO{

	private String[] unUtilisateur;
	private String URL;
	private ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
	public DAOUtilisateur()
	{
		super("",new ArrayList<NameValuePair>());
	}
	
	public int checkIdentifiant(String Utilisateur_Identifiant,String Utilisateur_Mdp){
		this.URL = "http://www.projet-ppe.fr/pnr/android/checkUtilisateur.php";
		nameValuePairs.add(new BasicNameValuePair("Utilisateur_Identifiant",Utilisateur_Identifiant));
		nameValuePairs.add(new BasicNameValuePair("Utilisateur_Mdp",Utilisateur_Mdp));
		super.setParametre(this.nameValuePairs);
		super.setURL(URL);
		int retour = 0;
		try
		{		
			JSONArray jArray = new JSONArray(super.readResult());
			JSONObject json_data = jArray.getJSONObject(0);	
			retour = json_data.getInt("NB_UTILISATEUR");	
			// BUG ICI, enlever passe par le catch alors que il détecte la bonne valeur (1)
			
		}catch(JSONException e)
		{
			retour = 0;
		}		
		return retour;
	}
	
	public String[] getUtilisateurById(String Param){

		this.URL = "http://www.projet-ppe.fr/pnr/android/getExploitationById.php";
		nameValuePairs.add(new BasicNameValuePair("Exploitation_Id",Param));
		super.setParametre(this.nameValuePairs);
		super.setURL(URL);
		try
		{		
			JSONArray jArray = new JSONArray(super.readResult());
			JSONObject json_data = jArray.getJSONObject(0);	
			this.unUtilisateur = new String[]{json_data.getString("Exploitation_Id"), json_data.getString("Exploitation_Nom"), json_data.getString("Exploitation_Ville")};			
		}catch(JSONException e)
		{
		}
		return unUtilisateur;
	}
}
