package com.insta.annuaire;

import java.util.HashMap;
import java.util.List;

import com.insta.annuaire.dao.DAOContact;

public class unContact extends DAOContact{
	public unContact()
	{
		super();
	}
	public List<HashMap<String, String>> getListeContactByExploitationId(String Exploitation_Id)
	{
		return super.getListeContactByExploitationId(Exploitation_Id);		
	}
	public String getTelContactById(String Exploitation_Contact_Id)
	{
		return super.getTelContactById(Exploitation_Contact_Id);
	}
}
