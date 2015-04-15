package com.example.pnr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DAOContact extends DAO {
	
	private String[] unContact;
	private String URL;
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	
	public DAOContact()
	{
		super("",new ArrayList<NameValuePair>());
	}
	
	public  List<HashMap<String, String>> getListeContactByExploitationId(String Exploitation_Id){
 		this.URL = "http://www.projet-ppe.fr/pnr/android/getListeContactByExploitationId.php";
		nameValuePairs.add(new BasicNameValuePair("Exploitation_Id",Exploitation_Id));
		super.setParametre(this.nameValuePairs);
		super.setURL(URL);
	        // prepare the list of all records
	        List<HashMap<String, String>> lesContact = new ArrayList<HashMap<String, String>>();
	        JSONArray jArray;
			try {
				jArray = new JSONArray(super.readResult());
				for(int i = 0; i < jArray.length(); i++){
		        	JSONObject json_data = jArray.getJSONObject(i);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("Id", "" + json_data.getString("Exploitation_Contact_Id"));
		            map.put("Nom", "" + json_data.getString("Exploitation_Contact_Nom"));
		            map.put("Prenom", json_data.getString("Exploitation_Contact_Prenom"));
		            if(map!=null)
		            {
		            	lesContact.add(map);
		            }	            
		        }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	        
	        return lesContact;
	}
	public  String getTelContactById(String Exploitation_Contact_Id){
		String Contact_Telephone="";
 		this.URL = "http://www.projet-ppe.fr/pnr/android/getContactTelByID.php";
		nameValuePairs.add(new BasicNameValuePair("Exploitation_Contact_Id",Exploitation_Contact_Id));
		super.setParametre(this.nameValuePairs);
		super.setURL(URL);
	        // prepare the list of all records
			try {
				JSONArray jArray = new JSONArray(super.readResult());
				JSONObject json_data = jArray.getJSONObject(0);	
				Contact_Telephone = "tel:"+json_data.getString("NUMERO_TELEPHONE");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
	        return Contact_Telephone;
	}
}

