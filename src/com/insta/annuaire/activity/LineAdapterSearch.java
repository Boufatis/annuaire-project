package com.insta.annuaire.activity;
import com.insta.gen.annuaire.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class LineAdapterSearch extends BaseAdapter {

	LayoutInflater inflater; 
	
	public LineAdapterSearch(Context context) { 
		inflater = LayoutInflater.from(context); 	
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.line, parent, false); 
		((TextView)convertView.findViewById(R.id.Exploitation_Nom)).setText("Texte ligne" + position);
		((TextView)convertView.findViewById(R.id.Exploitation_Ville)).setText("Texte ligne" + position); 
		return convertView;
	}

}
