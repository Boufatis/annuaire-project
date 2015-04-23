package com.insta.annuaire.activity;

import Enum.EnumOrder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.insta.annuaire.dao.DAOContact;
import com.insta.gen.annuaire.R;

public class ListContactActivity extends Activity{
	
	private ListView lvContact;
	ContactAdapter adaptateur;
	EditText inputSearch;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcontact);
        DAOContact daoContact = new DAOContact();
        lvContact = (ListView)findViewById(R.id.listViewContact);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        adaptateur = new ContactAdapter(this,daoContact.getListContact());
        adaptateur.getCount();
        lvContact.setAdapter(adaptateur);
        lvContact.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView adapterView, View view,int position, long id) 
			{
				String  contactid = ((TextView)view.findViewById(R.id.hiddenId)).getText().toString();
				int idContact = Integer.parseInt(contactid);
				Intent showContact = new Intent(ListContactActivity.this, ShowContactActivity.class);
				showContact.putExtra("contactid", idContact);
				ListContactActivity.this.startActivity(showContact);
			}
        });
        inputSearch.addTextChangedListener(new TextWatcher() {
            
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
            	lvContact = (ListView)findViewById(R.id.listViewContact);
                inputSearch = (EditText) findViewById(R.id.inputSearch);
                DAOContact daoContact = new DAOContact();
                adaptateur = new ContactAdapter(ListContactActivity.this,daoContact.getListContactByCriteria(cs.toString(), "all", EnumOrder.ASC));
                
                lvContact.setAdapter(adaptateur);
//                lvContact.setOnItemClickListener(new OnItemClickListener() {
//        			public void onItemClick(AdapterView adapterView, View view,int position, long id) 
//        			{
//        				String  contactid = ((TextView)view.findViewById(R.id.hiddenId)).getText().toString();
//        				int idContact = Integer.parseInt(contactid);
//        				Intent showContact = new Intent(ListContactActivity.this, ShowContactActivity.class);
//        				showContact.putExtra("contactid", idContact);
//        				ListContactActivity.this.startActivity(showContact);
//        			}
//                });
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
        });
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.menulistcontact, menu);
	        return true;
	    }
	  
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	  	switch (item.getItemId()) {
	  	case R.id.menu_favoris:
	  		Intent goFavoris = new Intent(ListContactActivity.this, FavoriteActivity.class);
			ListContactActivity.this.startActivity(goFavoris);
	  		return true;	
	  	case R.id.menu_deconexion:
	  		Intent deconexion = new Intent(ListContactActivity.this, MainActivity.class);
			ListContactActivity.this.startActivity(deconexion);
			return true;
	  	default:
	  		return super.onOptionsItemSelected(item);
	  	}
	  }
}
