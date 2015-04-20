package com.insta.annuaire.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

public class DAO {
	private String URL = "";
	private ArrayList<NameValuePair> Parametre = new ArrayList<NameValuePair>();
	public DAO(String URL,ArrayList<NameValuePair> Parametre)
	{
		this.URL = URL;
		this.Parametre = Parametre;
	}
	public String readResult() {
		String result = "";
		if(this.URL != "")
		{
			InputStream is = null;		
			//Envoyer la requête au script PHP.
			try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(URL);
				//httppost.setEntity(new UrlEncodedFormEntity(this.Parametre));
				if (android.os.Build.VERSION.SDK_INT > 9) {
				    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				    StrictMode.setThreadPolicy(policy);
				}
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();		
			}catch(Exception e){
				Log.e("log_tag", "Error in http connection " + e.toString());
			}
		
			// Convertion de la requête en string
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result=sb.toString();
			}catch(Exception e){
				Log.e("log_tag", "Error converting result " + e.toString());
			}
			
		}
		return result;
	}
	public ArrayList<NameValuePair> getParametre() {
		return Parametre;
	}
	public void setParametre(ArrayList<NameValuePair> parametre) {
		Parametre = parametre;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String URL) {
		this.URL = URL;
	}

}

