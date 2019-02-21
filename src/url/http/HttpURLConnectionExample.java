/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author mthoming
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonValue;

import javax.net.ssl.HttpsURLConnection;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.json.JSONObject;
import javax.json.JsonWriter;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Send Http GET request");
		http.sendGet();
		
		System.out.println("\nSend Http POST request");
		http.sendPost();              
	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "https://www.motability.co.uk/cars-scooters-and-powerchairs/types-of-cars/by-makes-and-models";
                        
                        //"http://www.google.com/search?q=mkyong";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
                                             
                Document doc = Jsoup.parse(response.toString()); 

                //System.out.println(doc);
   
                //create an arraylist and insert all values with "td" tags into it
                ArrayList<Element> rows = doc.select("td");
                //output the entire list to the user's view
                System.out.println(rows);
                
                //create iterator variable to use in counting rows during iteration
                int i = 0;
                //create a variable that gets the values tagged as "td" in each row
                Element link = doc.select("td").get(i);
                //loop through each row in ROWS, grab the "td" tagged data and 
                //print it out with the html formatting stripped off
                for(i = 0; i < rows.size(); i++) {
                    link = doc.select("td").get(i);
                    System.out.println(link.html());
                }                
	}
	
	// HTTP POST request
	private void sendPost() throws Exception {

                String url = "https://selfsolve.apple.com/wcResults.do";
		//String url = "http://localhost:8080/WebApplication1/";
                URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());
	}
}
