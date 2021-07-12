package com.qualcomm.QCARSamples.TextReco;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class WordPad extends Activity{
	
	ListView listview;
	ArrayList<String> al = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordpad);
        
        DatabaseHandler dh = new DatabaseHandler(this);
        
        List<Word> words = dh.getAllContacts();  
        
        for (Word w : words){
        	al.add(w.getWord_word());
        }
        
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        
        listview = (ListView) findViewById(R.id.wordList);
        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String str = (String)adapter.getItem(arg2);
				//Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getApplicationContext(), WordDetailView.class);
				intent.putExtra("word", str);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    			getApplication().startActivity(intent);
			}
		});
        
        
     /*   for (Word  w : words) {
        	String log = "Id: "+w.getWord_id()+" ,word: " + w.getWord_word() + " ,mean: " + w.getWord_meaning();
        	Log.d("Name: ", log);
        }*/
    }

}
