package weather;

import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class weather
{
	JFrame frame;
//	JLabel current_1,city_1,currenti_1,condition_1,minl_1,maxl_1,next_temp_0,hour_next_0,temp_next_0,next_temp_1,hour_next_1,temp_next_1,next_temp_2,hour_next_2,temp_next_2,next_temp_3,hour_next_3,temp_next_3,next_temp_4,hour_next_4,temp_next_4,next_temp_5,hour_next_5,temp_next_5,week_name_0,weak_icon_0,week_min_0,week_max_0,week_name_1,weak_icon_1,week_min_1,week_max_1,week_name_2,weak_icon_2,week_min_2,week_max_2;
	HashMap<String,JLabel> map ; 
	
	
	weather(JFrame frame)
	{
		this.frame=frame;
		render();
		api();
	}
	
	//api call here
	
	public void api()
	{
		
		try {
		URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=London,uk&units=metric&APPID=8032cb689d95229e594b7c5751da6355");

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
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            JSONArray arr = (JSONArray) data_obj.get("list");
            
            for(int i=0,j=0;i<arr.size() && j<3;i++)
            {
            	JSONObject new_obj = (JSONObject) arr.get(i);
            	JSONObject mainy = (JSONObject) new_obj.get("main");
            	
            	if(i==0)
            	{
            		JLabel temp ;
            		
            		JSONArray wthr = (JSONArray) new_obj.get("weather");
            		
            		JSONObject wthr_obj = (JSONObject) wthr.get(0);
            		
            		temp = map.get("lblNewLabel");
            		temp.setIcon(new ImageIcon("img\\"+wthr_obj.get("icon")+".jpg"));
            		
            		temp = map.get("lblNewLabel_1");
            		temp.setIcon(new ImageIcon("img\\"+wthr_obj.get("icon")+"b.jpg"));
            		
            		temp = map.get("lblNewLabel_1_1");
            		temp.setIcon(new ImageIcon("img\\"+wthr_obj.get("icon")+"b.jpg"));
            		
            		ImageIcon imageIcon = new ImageIcon("img\\"+wthr_obj.get("icon")+".png"); 
            		Image image = imageIcon.getImage(); 
            		Image newimg = image.getScaledInstance(45, 45 ,  java.awt.Image.SCALE_SMOOTH);   
            		imageIcon = new ImageIcon(newimg);
            		
            		temp = map.get("currenti");
            		temp.setIcon(imageIcon);
            		
            		temp = map.get("currenti_1");
            		temp.setIcon(imageIcon);
            		
            		
            	}
            	
            }

       
        }

    } //try end 
		
	catch (Exception e) {
        e.printStackTrace();
    }
	
	}//function end
	
	//api call end here
	
	//render element
	public void render()
	{
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");  
		   LocalDateTime now = LocalDateTime.now();  
		   
		   map = new HashMap<String,JLabel>();
		   
		
		JPanel panel = new JPanel();
		panel.setBounds(419, 0, 419, 680);
		panel.setBackground(new Color(26, 26, 26, 123));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel current_1 = new JLabel("55\u00B0");
		current_1.setForeground(Color.WHITE);
		current_1.setFont(new Font("Poor Richard", Font.PLAIN, 150));
		current_1.setBounds(46, 192, 196, 150);
		panel.add(current_1);
		
		JLabel arrw1_1 = new JLabel("\t\u2191");
		arrw1_1.setForeground(Color.WHITE);
		arrw1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		arrw1_1.setBounds(46, 160, 63, 30);
		panel.add(arrw1_1);
		
		JLabel arrw2_1 = new JLabel("\t\u2193");
		arrw2_1.setForeground(Color.WHITE);
		arrw2_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		arrw2_1.setBounds(127, 154, 63, 42);
		panel.add(arrw2_1);
		
		JLabel maxl_1 = new JLabel("57");
		maxl_1.setForeground(Color.WHITE);
		maxl_1.setFont(new Font("Poor Richard", Font.BOLD, 30));
		maxl_1.setBounds(66, 134, 103, 91);
		panel.add(maxl_1);
		
		JLabel minl_1 = new JLabel("53");
		minl_1.setForeground(Color.WHITE);
		minl_1.setFont(new Font("Poor Richard", Font.BOLD, 30));
		minl_1.setBounds(147, 134, 103, 91);
		panel.add(minl_1);
		
		JLabel condition_1 = new JLabel("Clear Sky");
		condition_1.setForeground(Color.WHITE);
		condition_1.setFont(new Font("Poor Richard", Font.BOLD, 30));
		condition_1.setBounds(101, 102, 308, 58);
		panel.add(condition_1);
		
		JLabel currenti_1 = new JLabel();
		currenti_1.setIcon(new ImageIcon("img\\04d.png"));
		currenti_1.setHorizontalAlignment(SwingConstants.CENTER);
		currenti_1.setForeground(Color.BLACK);
		currenti_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		currenti_1.setBounds(31, 90, 70, 81);
		panel.add(currenti_1);
		
		JLabel lblNewLabel_2_1 = new JLabel(dtf.format(now)+" ");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Poor Richard", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(10, 66, 399, 23);
		panel.add(lblNewLabel_2_1);
		
		JLabel city_1 = new JLabel("New York");
		city_1.setHorizontalAlignment(SwingConstants.CENTER);
		city_1.setForeground(Color.WHITE);
		city_1.setFont(new Font("Poor Richard", Font.BOLD, 26));
		city_1.setBounds(10, 26, 399, 45);
		panel.add(city_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(45, 350, 340, 319);
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(26, 26, 26, 110));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel forecast = new JLabel("Forecast");
		forecast.setForeground(Color.WHITE);
		forecast.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		forecast.setFont(new Font("Poor Richard", Font.BOLD, 25));
		forecast.setBounds(10, -2, 320, 58);
		panel_1.add(forecast);
		
		JLabel next_temp_0 = new JLabel("");
		next_temp_0.setIcon(new ImageIcon("img\\10d.png"));
		next_temp_0.setHorizontalAlignment(SwingConstants.CENTER);
		next_temp_0.setForeground(Color.BLACK);
		next_temp_0.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		next_temp_0.setBounds(1, 64, 65, 81);
		panel_1.add(next_temp_0);
		
		JLabel hour_next_0 = new JLabel("12PM");
		hour_next_0.setHorizontalAlignment(SwingConstants.CENTER);
		hour_next_0.setForeground(Color.WHITE);
		hour_next_0.setFont(new Font("Poor Richard", Font.BOLD, 17));
		hour_next_0.setBounds(1, 57, 65, 37);
		panel_1.add(hour_next_0);
		
		JLabel temp_next_0 = new JLabel("61\u00B0");
		temp_next_0.setHorizontalAlignment(SwingConstants.CENTER);
		temp_next_0.setForeground(Color.WHITE);
		temp_next_0.setFont(new Font("Poor Richard", Font.BOLD, 17));
		temp_next_0.setBounds(0, 115, 65, 37);
		panel_1.add(temp_next_0);
		
		JLabel next_temp_1 = new JLabel("");
		next_temp_1.setIcon(new ImageIcon("img\\11n.png"));
		next_temp_1.setHorizontalAlignment(SwingConstants.CENTER);
		next_temp_1.setForeground(Color.BLACK);
		next_temp_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		next_temp_1.setBounds(58, 64, 65, 81);
		panel_1.add(next_temp_1);
		
		JLabel hour_next_1 = new JLabel("12PM");
		hour_next_1.setHorizontalAlignment(SwingConstants.CENTER);
		hour_next_1.setForeground(Color.WHITE);
		hour_next_1.setFont(new Font("Poor Richard", Font.BOLD, 17));
		hour_next_1.setBounds(58, 57, 65, 37);
		panel_1.add(hour_next_1);
		
		JLabel temp_next_1 = new JLabel("61\u00B0");
		temp_next_1.setHorizontalAlignment(SwingConstants.CENTER);
		temp_next_1.setForeground(Color.WHITE);
		temp_next_1.setFont(new Font("Poor Richard", Font.BOLD, 17));
		temp_next_1.setBounds(57, 115, 65, 37);
		panel_1.add(temp_next_1);
		
		JLabel next_temp_2 = new JLabel("");
		next_temp_2.setIcon(new ImageIcon("img\\13d.png"));
		next_temp_2.setHorizontalAlignment(SwingConstants.CENTER);
		next_temp_2.setForeground(Color.BLACK);
		next_temp_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		next_temp_2.setBounds(115, 64, 65, 81);
		panel_1.add(next_temp_2);
		
		JLabel hour_next_2 = new JLabel("12PM");
		hour_next_2.setHorizontalAlignment(SwingConstants.CENTER);
		hour_next_2.setForeground(Color.WHITE);
		hour_next_2.setFont(new Font("Poor Richard", Font.BOLD, 17));
		hour_next_2.setBounds(115, 57, 65, 37);
		panel_1.add(hour_next_2);
		
		JLabel temp_next_2 = new JLabel("61\u00B0");
		temp_next_2.setHorizontalAlignment(SwingConstants.CENTER);
		temp_next_2.setForeground(Color.WHITE);
		temp_next_2.setFont(new Font("Poor Richard", Font.BOLD, 17));
		temp_next_2.setBounds(114, 115, 65, 37);
		panel_1.add(temp_next_2);
		
		JLabel next_temp_3 = new JLabel("");
		next_temp_3.setIcon(new ImageIcon("img\\01d.png"));
		next_temp_3.setHorizontalAlignment(SwingConstants.CENTER);
		next_temp_3.setForeground(Color.BLACK);
		next_temp_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		next_temp_3.setBounds(170, 64, 65, 81);
		panel_1.add(next_temp_3);
		
		JLabel hour_next_3 = new JLabel("12PM");
		hour_next_3.setHorizontalAlignment(SwingConstants.CENTER);
		hour_next_3.setForeground(Color.WHITE);
		hour_next_3.setFont(new Font("Poor Richard", Font.BOLD, 17));
		hour_next_3.setBounds(170, 57, 65, 37);
		panel_1.add(hour_next_3);
		
		JLabel temp_next_3 = new JLabel("61\u00B0");
		temp_next_3.setHorizontalAlignment(SwingConstants.CENTER);
		temp_next_3.setForeground(Color.WHITE);
		temp_next_3.setFont(new Font("Poor Richard", Font.BOLD, 17));
		temp_next_3.setBounds(169, 115, 65, 37);
		panel_1.add(temp_next_3);
		
		JLabel next_temp_4 = new JLabel("");
		next_temp_4.setIcon(new ImageIcon("img\\50n.png"));
		next_temp_4.setHorizontalAlignment(SwingConstants.CENTER);
		next_temp_4.setForeground(Color.BLACK);
		next_temp_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		next_temp_4.setBounds(226, 64, 65, 81);
		panel_1.add(next_temp_4);
		
		JLabel hour_next_4 = new JLabel("12PM");
		hour_next_4.setHorizontalAlignment(SwingConstants.CENTER);
		hour_next_4.setForeground(Color.WHITE);
		hour_next_4.setFont(new Font("Poor Richard", Font.BOLD, 17));
		hour_next_4.setBounds(226, 57, 65, 37);
		panel_1.add(hour_next_4);
		
		JLabel temp_next_4 = new JLabel("61\u00B0");
		temp_next_4.setHorizontalAlignment(SwingConstants.CENTER);
		temp_next_4.setForeground(Color.WHITE);
		temp_next_4.setFont(new Font("Poor Richard", Font.BOLD, 17));
		temp_next_4.setBounds(225, 115, 65, 37);
		panel_1.add(temp_next_4);
		
		JLabel next_temp_5 = new JLabel("");
		next_temp_5.setHorizontalAlignment(SwingConstants.CENTER);
		next_temp_5.setIcon(new ImageIcon("img\\01n.png"));
		next_temp_5.setForeground(Color.BLACK);
		next_temp_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		next_temp_5.setBounds(278, 64, 65, 81);
		panel_1.add(next_temp_5);
		
		JLabel hour_next_5 = new JLabel("12PM");
		hour_next_5.setHorizontalAlignment(SwingConstants.CENTER);
		hour_next_5.setForeground(Color.WHITE);
		hour_next_5.setFont(new Font("Poor Richard", Font.BOLD, 17));
		hour_next_5.setBounds(278, 57, 65, 37);
		panel_1.add(hour_next_5);
		
		JLabel temp_next_5 = new JLabel("61\u00B0");
		temp_next_5.setHorizontalAlignment(SwingConstants.CENTER);
		temp_next_5.setForeground(Color.WHITE);
		temp_next_5.setFont(new Font("Poor Richard", Font.BOLD, 17));
		temp_next_5.setBounds(277, 115, 65, 37);
		panel_1.add(temp_next_5);
		
		JLabel forecast_1 = new JLabel("");
		forecast_1.setForeground(Color.WHITE);
		forecast_1.setFont(new Font("Poor Richard", Font.BOLD, 25));
		forecast_1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		forecast_1.setBounds(10, 100, 320, 58);
		panel_1.add(forecast_1);
		
		JLabel week_name_0 = new JLabel("Monday\r\n");
		week_name_0.setForeground(Color.WHITE);
		week_name_0.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		week_name_0.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_name_0.setBounds(10, 160, 320, 50);
		panel_1.add(week_name_0);
		
		JLabel weak_icon_0 = new JLabel("");
		weak_icon_0.setIcon(new ImageIcon("img\\03d.png"));
		weak_icon_0.setHorizontalAlignment(SwingConstants.CENTER);
		weak_icon_0.setForeground(Color.BLACK);
		weak_icon_0.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		weak_icon_0.setBounds(140, 160, 65, 45);
		panel_1.add(weak_icon_0);
		
		JLabel week_min_0 = new JLabel("53\u00B0");
		week_min_0.setForeground(Color.LIGHT_GRAY);
		week_min_0.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_min_0.setBounds(290, 160, 45, 45);
		panel_1.add(week_min_0);
		
		JLabel week_max_0 = new JLabel("89\u00B0");
		week_max_0.setForeground(Color.WHITE);
		week_max_0.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_max_0.setBounds(235, 160, 45, 45);
		panel_1.add(week_max_0);
		
		JLabel week_name_1 = new JLabel("Monday\r\n");
		week_name_1.setForeground(Color.WHITE);
		week_name_1.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_name_1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		week_name_1.setBounds(10, 210, 320, 50);
		panel_1.add(week_name_1);
		
		JLabel weak_icon_1 = new JLabel("");
		weak_icon_1.setIcon(new ImageIcon("img\\02d.png"));
		weak_icon_1.setHorizontalAlignment(SwingConstants.CENTER);
		weak_icon_1.setForeground(Color.BLACK);
		weak_icon_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		weak_icon_1.setBounds(140, 210, 65, 45);
		panel_1.add(weak_icon_1);
		
		JLabel week_min_1 = new JLabel("53\u00B0");
		week_min_1.setForeground(Color.LIGHT_GRAY);
		week_min_1.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_min_1.setBounds(290, 210, 45, 45);
		panel_1.add(week_min_1);
		
		JLabel week_max_1 = new JLabel("89\u00B0");
		week_max_1.setForeground(Color.WHITE);
		week_max_1.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_max_1.setBounds(235, 210, 45, 45);
		panel_1.add(week_max_1);
		
		JLabel week_name_2 = new JLabel("Monday\r\n");
		week_name_2.setForeground(Color.WHITE);
		week_name_2.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_name_2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
		week_name_2.setBounds(10, 260, 320, 50);
		panel_1.add(week_name_2);
		
		JLabel weak_icon_2 = new JLabel("");
		weak_icon_2.setIcon(new ImageIcon("img\\10n.png"));
		weak_icon_2.setHorizontalAlignment(SwingConstants.CENTER);
		weak_icon_2.setForeground(Color.BLACK);
		weak_icon_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		weak_icon_2.setBounds(140, 260, 65, 45);
		panel_1.add(weak_icon_2);
		
		JLabel week_min_2 = new JLabel("53\u00B0");
		week_min_2.setForeground(Color.LIGHT_GRAY);
		week_min_2.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_min_2.setBounds(290, 260, 45, 45);
		panel_1.add(week_min_2);
		
		JLabel week_max_2 = new JLabel("89\u00B0");
		week_max_2.setForeground(Color.WHITE);
		week_max_2.setFont(new Font("Poor Richard", Font.BOLD, 25));
		week_max_2.setBounds(235, 260, 45, 45);
		panel_1.add(week_max_2);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(838, 0, 419, 680);
		panel2.setBackground(new Color(26, 26, 26, 123));
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JTextField search = new JTextField();
		search.setBounds(30, 120, 303, 30);
		panel2.add(search);
		search.setForeground(Color.WHITE);
		search.setFont(new Font("Poor Richard", Font.PLAIN, 14));
		search.setText(" New York");
		search.setEditable(false);
		search.setBorder(BorderFactory.createLineBorder(Color.white));
		search.setOpaque(false);
		search.setColumns(10);
		
		JButton searchb = new JButton("");
		searchb.setBorder(null);
        searchb.setBorderPainted(false);
        searchb.setContentAreaFilled(false);
        searchb.setOpaque(false);
		searchb.setIcon(new ImageIcon("img\\search.png"));
		searchb.setBounds(337, 120, 49, 30);
		panel2.add(searchb);
		
		JLabel lblNewLabel_2_2 = new JLabel(dtf.format(now)+" ");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Poor Richard", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(10, 66, 399, 23);
		panel2.add(lblNewLabel_2_2);
		
		JLabel city_2 = new JLabel("New York");
		city_2.setHorizontalAlignment(SwingConstants.CENTER);
		city_2.setForeground(Color.WHITE);
		city_2.setFont(new Font("Poor Richard", Font.BOLD, 26));
		city_2.setBounds(10, 26, 399, 45);
		panel2.add(city_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(26, 26, 26, 110));
		panel_2.setBounds(30, 172, 356, 188);
		panel2.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("img\\windmill.png"));
		lblNewLabel_3.setBounds(23, 0, 143, 186);
		panel_2.add(lblNewLabel_3);
		
		JLabel city_2_1 = new JLabel("Wind :");
		city_2_1.setForeground(Color.WHITE);
		city_2_1.setFont(new Font("Poor Richard", Font.BOLD, 17));
		city_2_1.setBounds(159, 31, 65, 45);
		panel_2.add(city_2_1);
		
		JLabel city_2_1_1 = new JLabel("Degree :");
		city_2_1_1.setForeground(Color.WHITE);
		city_2_1_1.setFont(new Font("Poor Richard", Font.BOLD, 17));
		city_2_1_1.setBounds(281, 80, 65, 45);
		panel_2.add(city_2_1_1);
		
		JLabel wind = new JLabel("3.1 m/s");
		wind.setForeground(Color.WHITE);
		wind.setFont(new Font("Poor Richard", Font.PLAIN, 24));
		wind.setBounds(159, 55, 187, 45);
		panel_2.add(wind);
		
		JLabel wind_degree = new JLabel("61\u00B0");
		wind_degree.setHorizontalAlignment(SwingConstants.RIGHT);
		wind_degree.setForeground(Color.WHITE);
		wind_degree.setFont(new Font("Poor Richard", Font.PLAIN, 24));
		wind_degree.setBounds(176, 107, 158, 45);
		panel_2.add(wind_degree);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("img\\wind.png"));
		lblNewLabel_4.setBounds(240, 76, 44, 51);
		panel_2.add(lblNewLabel_4);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(26, 26, 26, 110));
		panel_2_1.setBounds(30, 371, 356, 188);
		panel2.add(panel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon("img\\humidity.png"));
		lblNewLabel_3_1.setBounds(23, 0, 143, 186);
		panel_2_1.add(lblNewLabel_3_1);
		
		JLabel city_2_1_3 = new JLabel("Humidity :");
		city_2_1_3.setForeground(Color.WHITE);
		city_2_1_3.setFont(new Font("Poor Richard", Font.BOLD, 17));
		city_2_1_3.setBounds(159, 31, 124, 45);
		panel_2_1.add(city_2_1_3);
		
		JLabel city_2_1_1_1 = new JLabel("Visiblity :");
		city_2_1_1_1.setForeground(Color.WHITE);
		city_2_1_1_1.setFont(new Font("Poor Richard", Font.BOLD, 17));
		city_2_1_1_1.setBounds(281, 80, 65, 45);
		panel_2_1.add(city_2_1_1_1);
		
		JLabel humidity = new JLabel("90%");
		humidity.setForeground(Color.WHITE);
		humidity.setFont(new Font("Poor Richard", Font.PLAIN, 24));
		humidity.setBounds(159, 55, 187, 45);
		panel_2_1.add(humidity);
		
		JLabel visibility = new JLabel("10 Km");
		visibility.setHorizontalAlignment(SwingConstants.RIGHT);
		visibility.setForeground(Color.WHITE);
		visibility.setFont(new Font("Poor Richard", Font.PLAIN, 24));
		visibility.setBounds(176, 107, 170, 45);
		panel_2_1.add(visibility);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(new ImageIcon("img\\eye.png"));
		lblNewLabel_4_1.setBounds(250, 76, 33, 51);
		panel_2_1.add(lblNewLabel_4_1);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(new Color(26, 26, 26, 110));
		panel_2_1_1.setBounds(30, 570, 356, 90);
		panel2.add(panel_2_1_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setIcon(new ImageIcon("img\\barometer.png"));
		lblNewLabel_3_1_1.setBounds(0, 0, 140, 90);
		panel_2_1_1.add(lblNewLabel_3_1_1);
		
		JLabel city_2_1_3_1 = new JLabel("Pressure :");
		city_2_1_3_1.setForeground(Color.WHITE);
		city_2_1_3_1.setFont(new Font("Poor Richard", Font.BOLD, 17));
		city_2_1_3_1.setBounds(128, 11, 124, 45);
		panel_2_1_1.add(city_2_1_3_1);
		
		JLabel pressure = new JLabel("1013 Pa");
		pressure.setForeground(Color.WHITE);
		pressure.setFont(new Font("Poor Richard", Font.PLAIN, 24));
		pressure.setBounds(128, 35, 124, 45);
		panel_2_1_1.add(pressure);
		
		JLabel city_2_1_3_1_1 = new JLabel("Sea Pressure :");
		city_2_1_3_1_1.setForeground(Color.WHITE);
		city_2_1_3_1_1.setFont(new Font("Poor Richard", Font.BOLD, 17));
		city_2_1_3_1_1.setBounds(262, 11, 124, 45);
		panel_2_1_1.add(city_2_1_3_1_1);
		
		JLabel sea_pressure = new JLabel("1013 Pa");
		sea_pressure.setForeground(Color.WHITE);
		sea_pressure.setFont(new Font("Poor Richard", Font.PLAIN, 24));
		sea_pressure.setBounds(262, 35, 124, 45);
		panel_2_1_1.add(sea_pressure);
		
		JLabel current = new JLabel("55\u00B0");
		current.setFont(new Font("Poor Richard", Font.PLAIN, 150));
		current.setForeground(Color.WHITE);
		current.setBounds(15, 530, 196, 150);
		frame.getContentPane().add(current);
		

		JLabel city = new JLabel("New York");
		city.setHorizontalAlignment(SwingConstants.CENTER);
		city.setForeground(Color.WHITE);
		city.setFont(new Font("Poor Richard", Font.BOLD, 26));
		city.setBounds(15, 64, 399, 45);
		frame.getContentPane().add(city);
		
		JLabel lblNewLabel_2 = new JLabel(dtf.format(now)+" ");
		lblNewLabel_2.setFont(new Font("Poor Richard", Font.BOLD, 16));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(15, 104, 399, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel arrw1 = new JLabel("\t\u2191");
		arrw1.setFont(new Font("Tahoma", Font.BOLD, 30));
		arrw1.setForeground(Color.WHITE);
		arrw1.setBounds(15, 496, 63, 30);
		frame.getContentPane().add(arrw1);

		JLabel arrw2 = new JLabel("\t\u2193");
		arrw2.setForeground(Color.WHITE);
		arrw2.setFont(new Font("Tahoma", Font.BOLD, 30));
		arrw2.setBounds(96, 489, 63, 45);
		frame.getContentPane().add(arrw2);
		
		JLabel maxl = new JLabel("57");
		maxl.setFont(new Font("Poor Richard", Font.BOLD, 30));
		maxl.setForeground(Color.WHITE);
		maxl.setBounds(35, 470, 103, 91);
		frame.getContentPane().add(maxl);
		
		JLabel minl = new JLabel("53");
		minl.setForeground(Color.WHITE);
		minl.setFont(new Font("Poor Richard", Font.BOLD, 30));
		minl.setBounds(116, 470, 103, 91);
		frame.getContentPane().add(minl);
		
		JLabel condition = new JLabel("Clear Sky");
		condition.setFont(new Font("Poor Richard", Font.BOLD, 30));
		condition.setForeground(Color.WHITE);
		condition.setBounds(70, 436, 308, 58);
		frame.getContentPane().add(condition);

		JLabel currenti = new JLabel("");
		currenti.setIcon(new ImageIcon("img\\09d.png"));
		currenti.setHorizontalAlignment(SwingConstants.CENTER);
		currenti.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		currenti.setForeground(Color.BLACK);
		currenti.setBounds(0, 424, 75, 81);
		frame.getContentPane().add(currenti);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("img\\09d.jpg"));
		lblNewLabel.setBounds(0, 0, 419, 680);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("img\\09db.jpg"));
		lblNewLabel_1.setBounds(419, 0, 419, 680);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("57");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1_1.setIcon(new ImageIcon("img\\09db.jpg"));
		lblNewLabel_1_1.setBounds(838, 0, 419, 680);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		
		map.put("city_1",city_1);
		map.put("currenti_1", currenti_1);
		map.put("condition_1", condition_1);
		map.put("minl_1",  minl_1);
		map.put("maxl_1", maxl_1);
		map.put("current_1", current_1);
		map.put("temp_next_0",temp_next_0);
		map.put("hour_next_0",hour_next_0);
		map.put("next_temp_0",next_temp_0);
		map.put("temp_next_1",temp_next_1);
		map.put("hour_next_1",hour_next_1);
		map.put("next_temp_1",next_temp_1);
		map.put("temp_next_2",temp_next_2);
		map.put("hour_next_2",hour_next_2);
		map.put("next_temp_2",next_temp_2);
		map.put("temp_next_3",temp_next_3);
		map.put("hour_next_3",hour_next_3);
		map.put("next_temp_3",next_temp_3);
		map.put("temp_next_4",temp_next_4);
		map.put("hour_next_4",hour_next_4);
		map.put("next_temp_4",next_temp_4);
		map.put("temp_next_5",temp_next_5);
		map.put("hour_next_5",hour_next_5);
		map.put("next_temp_5",next_temp_5);
		map.put("week_max_0",week_max_0);
		map.put("week_min_0",week_min_0);
		map.put("weak_icon_0",weak_icon_0);
		map.put("week_name_0",week_name_0);
		map.put("week_max_1",week_max_1);
		map.put("week_min_1",week_min_1);
		map.put("weak_icon_1",weak_icon_1);
		map.put("week_name_1",week_name_1);
		map.put("week_max_2",week_max_2);
		map.put("week_min_2",week_min_2);
		map.put("weak_icon_2",weak_icon_2);
		map.put("week_name_2",week_name_2);
		map.put("city_2", city_2);
		map.put("wind", wind);
		map.put("wind_degree", wind_degree);
		map.put("humidity", humidity);
		map.put("visibility", visibility);
		map.put("pressure", pressure);
		map.put("sea_pressure", sea_pressure);
		map.put("current", current);
		map.put("city", city);
		map.put("maxl",maxl);
		map.put("minl", minl);
		map.put("condition", condition);
		map.put("currenti", currenti);
		map.put("lblNewLabel", lblNewLabel);
		map.put("lblNewLabel_1", lblNewLabel_1);
		map.put("lblNewLabel_1_1", lblNewLabel_1_1);
		
		
		
	}
	
}

public class main {
	
	
	public static void frame()
	{
		JFrame frame =  new JFrame("hii");
		new weather(frame);
		frame.setBounds(40, 5, 1269, 713);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 try {
	            // select Look and Feel
	            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
	            // start application
	             frame();
	        }
	        catch (Exception ex) {
	            ex.printStackTrace();
	        }

		
	}

}
