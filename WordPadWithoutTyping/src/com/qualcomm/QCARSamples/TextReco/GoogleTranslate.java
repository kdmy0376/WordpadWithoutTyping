package com.qualcomm.QCARSamples.TextReco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class GoogleTranslate {

	 public static String callGoogleTranslate(String fromLanguage,	String toLanguage, String textToTranslate) {
			String yourKey = "AIzaSyAoi0NOgCd6Mt7PRXqT5We3R8EIpi8M-l8";
			String result = null;
			String URL = "https://www.googleapis.com/language/translate/v2";
			String key = "?key=" + yourKey;
			String sourceParam = "&source=" + fromLanguage;
			String toParam = "&target=" + toLanguage;
			String textParam = "&q=" + textToTranslate.replaceAll(" ", "%20").replace("\n", "%20");
			String fullURL = URL + key + sourceParam + toParam + textParam;
			System.out.println(fullURL);

			HttpClient httpClient = new DefaultHttpClient();
			HttpGet del = new HttpGet(fullURL);
			HttpResponse resp;
			try {
				resp = httpClient.execute(del);
				String respStr = EntityUtils.toString(resp.getEntity());
				// Parse Result http
				result = proccesResult(respStr);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return result;
		}

		static String proccesResult(String jsonStringData) {

			try {
				JSONObject data = new JSONObject(jsonStringData);
				JSONObject jsoObj2 = data.getJSONObject("data");
				JSONArray jArray = jsoObj2.getJSONArray("translations");
				JSONObject steps = jArray.getJSONObject(0);
				String txtTraducido = steps.getString("translatedText");

				// TRANSLATE TEXT
				Log.i("Prueba", "Resultado-> " + txtTraducido);
				
				return txtTraducido;

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return "";
		}
}
