package com.insta.annuaire.activity;

import com.insta.annuaire.DatabaseHandler;
import com.insta.gen.annuaire.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoriteActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.favorite);
        
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        
        // Create the adapter to convert the array to views
        ContactAdapter adapter = new ContactAdapter(this, db.getUser());
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listViewFavoris);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView adapterView, View view,int position, long id) 
			{
				String  contactid = ((TextView)view.findViewById(R.id.hiddenId)).getText().toString();
				int idContact = Integer.parseInt(contactid);
				Intent showContact = new Intent(FavoriteActivity.this, ShowContactActivity.class);
				showContact.putExtra("contactid", idContact);
				FavoriteActivity.this.startActivity(showContact);
			}
        });
    } 
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.favorismenu, menu);
	        return true;
	    }
	 
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	  	switch (item.getItemId()) {
	  	case R.id.menu_back:
	  		Intent retour = new Intent(this, ListContactActivity.class);
	  		FavoriteActivity.this.startActivity(retour);
	  		return true;	
	  	case R.id.menu_deconexion:
	  		Intent deconnexion = new Intent(this, MainActivity.class);
	  		FavoriteActivity.this.startActivity(deconnexion);
			return true;
	  	default:
	  		return super.onOptionsItemSelected(item);
	  	}
	  }
	
}
