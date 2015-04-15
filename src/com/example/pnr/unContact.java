package com.example.pnr;

import java.util.HashMap;
import java.util.List;

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
