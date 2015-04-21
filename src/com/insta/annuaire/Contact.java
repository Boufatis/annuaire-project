package com.insta.annuaire;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.sql.*;

import android.text.method.DateTimeKeyListener;

public class Contact {
	private int id;
	private String nom;
	private String prenom;
	private String profil;
	private String promo;
	private String photo;
	private String mail;
	private Date dateNaissance;
	private String telephone;
	
	public Contact(int id,String nom,String prenom, String profil, String promo,String photo)
	{
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.profil = profil;
		this.promo = promo;
		this.setPhoto(photo);
	}
	public Contact() {
		
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPromo() {
		return promo;
	}
	public void setPromo(String promo) {
		this.promo = promo;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) throws ParseException  {
		TimeZone tz = TimeZone.getTimeZone("Europe/Paris");
		Calendar cal = Calendar.getInstance(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		sdf.setCalendar(cal);
		cal.setTime(sdf.parse(dateNaissance));
		Date date = cal.getTime();
		this.dateNaissance = date;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
