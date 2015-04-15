package com.example.pnr;



public class Utilisateur extends DAOUtilisateur{
	
	private String Utilisateur_Id;
	private String Utilisateur_Identifiant;
	private String Utilisateur_Mdp;
	private String Utilisateur_Utilisateur_Nom;
	private String Utilisateur_Utilisateur_Prenom;
	private String Utilisateur_Utilisateur_Email;
	
	public Utilisateur()
	{
		super();
	}
	
	public int checkUtilisateur(String Utilisateur_Identifiant,String Utilisateur_Mdp)
	{
		return super.checkIdentifiant(Utilisateur_Identifiant, Utilisateur_Mdp);
	}
	public String getUtilisateur_Id() {
		return Utilisateur_Id;
	}
	public void setUtilisateur_Id(String utilisateur_Id) {
		Utilisateur_Id = utilisateur_Id;
	}
	public String getUtilisateur_Identifiant() {
		return Utilisateur_Identifiant;
	}
	public void setUtilisateur_Identifiant(String utilisateur_Identifiant) {
		Utilisateur_Identifiant = utilisateur_Identifiant;
	}
	public String getUtilisateur_Utilisateur_Nom() {
		return Utilisateur_Utilisateur_Nom;
	}
	public void setUtilisateur_Utilisateur_Nom(
			String utilisateur_Utilisateur_Nom) {
		Utilisateur_Utilisateur_Nom = utilisateur_Utilisateur_Nom;
	}
	public String getUtilisateur_Mdp() {
		return Utilisateur_Mdp;
	}
	public void setUtilisateur_Mdp(String utilisateur_Mdp) {
		Utilisateur_Mdp = utilisateur_Mdp;
	}
	public String getUtilisateur_Utilisateur_Prenom() {
		return Utilisateur_Utilisateur_Prenom;
	}
	public void setUtilisateur_Utilisateur_Prenom(
			String utilisateur_Utilisateur_Prenom) {
		Utilisateur_Utilisateur_Prenom = utilisateur_Utilisateur_Prenom;
	}
	public String getUtilisateur_Utilisateur_Email() {
		return Utilisateur_Utilisateur_Email;
	}
	public void setUtilisateur_Utilisateur_Email(
			String utilisateur_Utilisateur_Email) {
		Utilisateur_Utilisateur_Email = utilisateur_Utilisateur_Email;
	}
	
	
}
