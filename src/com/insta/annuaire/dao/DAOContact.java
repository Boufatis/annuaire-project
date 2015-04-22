package com.insta.annuaire.dao;

import java.io.Console;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Enum.EnumOrder;

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
	
	public List<Contact> getListContactByCriteria(String search, String typeUser, EnumOrder order){
		
		if(search.isEmpty()){return this.getListContact();}
		
		String orderby;
		if(!typeUser.equals("etudiant") || !typeUser.equals("professeur")){
			typeUser = "all";
		}
		if(order == EnumOrder.ASC) { orderby ="ASC";}else{orderby ="DESC";}
		System.out.println("RECHERCHE : " + search + " typeUser : " + " oderby : " + orderby);
 		this.URL = "http://dev-project.it:3000/annuaire/get/" + search + "/" + typeUser + "/" + orderby;
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
	        	Contact contact = new Contact();
	        	contact.setId(json_data.getInt("id"));
	        	contact.setNom(json_data.getString("nom"));
	        	contact.setPrenom(json_data.getString("prenom"));
	        	contact.setProfil(json_data.getString("profil"));
	        	contact.setPromo(json_data.getString("promo"));
	        	contact.setPhoto(json_data.getString("photo"));
	        	contacts.add(contact);       
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	       
	    return contacts;
	}
	
	public Contact getContactById(int id) throws Exception{
		
		this.URL = "http://dev-project.it:3000/annuaire/get/"+id;
		super.setURL(URL);
		Contact contact = new Contact();
        JSONArray jArray;
		try 
		{
			JSONObject jobject = new JSONObject(super.readResult());
			jArray = jobject.getJSONArray("profils");
	        JSONObject json_data = jArray.getJSONObject(0);	    
			contact.setId(json_data.getInt("id"));
			contact.setNom(json_data.getString("nom"));
			contact.setPrenom(json_data.getString("prenom"));
			contact.setDateNaissance(json_data.getString("date_naissance"));
			contact.setProfil(json_data.getString("libelle"));
			contact.setPromo(json_data.getString("promo"));
			contact.setMail(json_data.getString("mail"));
			contact.setTelephone(json_data.getString("telephone"));
			contact.setPhoto(json_data.getString("photo"));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}	       
	    return contact;
	}
	
}

