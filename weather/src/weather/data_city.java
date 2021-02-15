package weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class data_city {
	
	
	
	static JSONArray city_data() throws IOException
	{
	
        
        String s="";

		
		try {
			URL url = new URL("https://pkgstore.datahub.io/core/world-cities/world-cities_json/data/5b3dd46ad10990bca47b04b4739a02ba/world-cities_json.json");

	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.connect();

	        //Getting the response code
	        int responsecode = conn.getResponseCode();

	        if (responsecode != 200) {
	            throw new RuntimeException("HttpResponseCode: " + responsecode);
	        } else {
	        	String inline = "";
	            Scanner scanner = new Scanner(url.openStream());

	            //Write all the JSON data into a string using a scanner
	            while (scanner.hasNext()) {
	                inline += scanner.nextLine();
	            }

	            //Close the scanner
	            scanner.close();

	            //Using the JSON simple library parse the string into a json object
	            JSONParser parse = new JSONParser();
	            JSONArray data_obj = (JSONArray) parse.parse(inline);
	            return data_obj;
	        }
		}
		catch (Exception e)
		{
			JFrame errory=new JFrame();  
		    JOptionPane.showMessageDialog(errory,"Soory cannot Fetch Data Of This City Please Try Later","Error",JOptionPane.WARNING_MESSAGE); 
		}
        
		
        
		Object obj = JSONValue.parse(s);
		JSONArray array = (JSONArray)obj;
		return array;
	}
	


}
