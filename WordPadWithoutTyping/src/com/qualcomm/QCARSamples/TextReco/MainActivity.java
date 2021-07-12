package com.qualcomm.QCARSamples.TextReco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button mGoTextReco;
	Button mGoWordPad;
	Button mGoStudy;

	Handler handler = new Handler();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mGoTextReco = (Button) findViewById(R.id.goTextReco);
		mGoWordPad = (Button) findViewById(R.id.goWordPad);
		mGoStudy = (Button) findViewById(R.id.GoStudy);

		mGoTextReco.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, TextReco.class));
			}
		});

		mGoWordPad.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, WordPad.class));
			}
		});

		mGoStudy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_LONG).show();
					}
				});
			}
		});

		// startActivity(new Intent(MainActivity.this, TextReco.class));

		// finish();

	}
}
