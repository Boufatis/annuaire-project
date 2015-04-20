package com.insta.annuaire;

public class Contact {
	private int id;
	private String nom;
	private String prenom;
	private String profil;
	private String promo;
	private String photo;
	
	public Contact(int id,String nom,String prenom, String profil, String promo,String photo)
	{
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.profil = profil;
		this.promo = promo;
		this.setPhoto(photo);
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
	
}
