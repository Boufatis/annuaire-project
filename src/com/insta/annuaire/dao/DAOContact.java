package com.insta.annuaire.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.insta.annuaire.Contact;


public class DAOContact extends DAO {
	
	private String[] unContact;
	private String URL;
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
	public DAOContact()
	{
		super("",new ArrayList<NameValuePair>());
	}
	
	public List<Contact> getListContact(){
 		this.URL = "http://dev-project.it:3000/annuaire/get";
		super.setURL(URL);
        // prepare the list of all records        
		List<Contact> contacts = new ArrayList<Contact>();
        JSONArray jArray;
		try 
		{
			JSONObject jobject = new JSONObject(super.readResult());
			jArray = jobject.getJSONArray("profils");
			for(int i = 0; i < jArray.length(); i++){
	        	JSONObject json_data = jArray.getJSONObject(i);	
	        	contacts.add(new Contact(json_data.getInt("id"), json_data.getString("nom"), json_data.getString("prenom"), json_data.getString("profil"), json_data.getString("promo"), json_data.getString("photo")));       
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
	    return contacts;
	}
	
}

