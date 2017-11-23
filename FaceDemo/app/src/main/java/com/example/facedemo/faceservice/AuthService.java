package com.example.facedemo.faceservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthService {
	private static String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
	private static String clientId = "osGEAMrRs9ubIYZ4QwcXKE2k";
	private static String clientSecret = "Lq4pi3L5GVqnRw8w7jOoCMSKzbIGs15v";
	
	public static String getAuth(){
		String getAccessTokenUrl = authHost
				+ "grant_type=client_credentials"
				+ "&client_id=" + clientId
				+ "&client_secret=" + clientSecret;
		try {
			URL realUrl = new URL(getAccessTokenUrl);
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			Map<String, List<String>> map = connection.getHeaderFields();
			for (String key : map.keySet()) {
				System.out.println(key + "------>" + map.get(key));
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String result = "";
			String line;
			while((line = in.readLine()) != null){
				result += line;
			}
			System.out.println("result: "+result);
			JSONObject jsonObject = new JSONObject(result);
			String access_token = jsonObject.getString("access_token");
			return access_token;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
				
	}
}
