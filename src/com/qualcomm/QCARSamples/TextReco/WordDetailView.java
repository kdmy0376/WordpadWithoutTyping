package com.qualcomm.QCARSamples.TextReco;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WordDetailView extends Activity {

	TextView mWordTitleView;
	TextView mMeaningView;
	TextView mNounView;

	boolean isTranslated = false;
	
	Button mAddToLib;
	Button mTranslate;
	Button mGoWordPad;
	
	static String meaning;
	static String searchWord;
	static String noun;
	static String translated_meaning;
	Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.worddetail);

		Bundle extras = getIntent().getExtras();
		String word = extras.getString("word");

		mAddToLib = (Button) findViewById(R.id.details_addToLib);
		mTranslate = (Button) findViewById(R.id.details_translate);
		mGoWordPad = (Button) findViewById(R.id.details_goWordPad);
		
		mNounView = (TextView) findViewById(R.id.nounView);
		mMeaningView = (TextView) findViewById(R.id.meaningView);
		mWordTitleView = (TextView) findViewById(R.id.wordTitleView);
		mWordTitleView.setText(word);
		searchWord = word;

		mAddToLib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "단어장에 넣었습니다.",
								Toast.LENGTH_SHORT).show();
						DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
						dh.addWord(new Word(searchWord, noun, noun, meaning));
					}
				});

			}
		});

		mTranslate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(!isTranslated){
							isTranslated = !isTranslated;
							mTranslate.setText("원본보기");
							Toast.makeText(getApplicationContext(), "번역합니다.",
									Toast.LENGTH_SHORT).show();
							mMeaningView.setText(translated_meaning);
						}else{
							isTranslated = !isTranslated;
							mTranslate.setText("번역보기");
							Toast.makeText(getApplicationContext(), "원본으로 되돌립니다..",
									Toast.LENGTH_SHORT).show();
							mMeaningView.setText(meaning);
						}
						
					}
				});
			}
		});

		mGoWordPad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(), WordPad.class));
			}
		});
		
		new Thread() {
			public void run() {
				callGoogleDic(searchWord);
				translated_meaning = GoogleTranslate.callGoogleTranslate("en", "ko", meaning);
			}
		}.start();

	}

	String callGoogleDic(String textToTranslate) {
		String result = null;
		String fullURL = "http://www.google.com/dictionary/json?callback=dic&q="
				+ textToTranslate + "&sl=en&tl=en&restrict=pr%2Cde&client=te";
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet del = new HttpGet(fullURL);
		HttpResponse resp;
		try {
			resp = httpClient.execute(del);
			String respStr = EntityUtils.toString(resp.getEntity());
			String tmp = respStr.substring(4, respStr.length() - 1);
			System.out.println(tmp);
			result = proccesResult(tmp);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	String proccesResult(String jsonStringData) {

		try {
			JSONObject data = new JSONObject(jsonStringData);
			JSONArray primaries = data.getJSONArray("primaries");
			JSONArray terms = primaries.getJSONObject(0).getJSONArray("terms");
			JSONArray labels = terms.getJSONObject(0).getJSONArray("labels");
			JSONArray entries = primaries.getJSONObject(0).getJSONArray(
					"entries");
			JSONArray entries_terms = entries.getJSONObject(0).getJSONArray(
					"terms");

			String s = "";
			for (int i = 1; i < entries.length(); i++) {
				s += i
						+ ". "
						+ entries.getJSONObject(i).getJSONArray("terms")
								.getJSONObject(0).getString("text") + "\n";

			}
			meaning = s;
			noun = labels.getJSONObject(0).get("text") + "\n"
					+ terms.getJSONObject(0).get("text");
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mNounView.setText(noun);
					mMeaningView.setText(meaning);
				}
			});

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return "";
	}

}
