package httpclient;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import utilies.LanguageTable;
import utilies.SearchConfig;
import utilies.SettingsParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;



public class ClientSide {
	//Address Variables
	public static String serviceAddress = "http://localhost:8080/test";
	public static String postAddress = "http://localhost:8080/post";
	
	//Utility Classes Variables
	private SearchConfig sc;
	private SettingsParser sp;
	
	//Java Swing Variables
	private JFrame frame;
	private JTextField longTextField;
	private JTextField latTextField;
	private JTextField queryCountTextField;
	JComboBox languageTableCom = new JComboBox();
	
	//State variables
	private String langState;
	private JTextField radiusText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSide window = new ClientSide();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientSide() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Call SettingsParser and SearchConfig
		//in order to prepare them
		sc = SearchConfig.getInstance();
		sp = SettingsParser.getInstance(sc);
		sp.load();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 293, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel langLabel = new JLabel("Language: ");
		langLabel.setBounds(12, 34, 72, 16);
		frame.getContentPane().add(langLabel);
		//need to add time duration
		//need tot add types of search: tweet, user...etc
		//LanguageTable GUI Settings
		languageTableCom.setMaximumRowCount(35);
		languageTableCom.setBounds(95, 31, 153, 22);
		languageTableCom.setModel(new DefaultComboBoxModel(LanguageTable.langTableCombo));
		frame.getContentPane().add(languageTableCom);

		JLabel lblLongtitude = new JLabel("Longtitude: ");
		lblLongtitude.setBounds(12, 63, 72, 16);
		frame.getContentPane().add(lblLongtitude);

		final JLabel lblLatitude = new JLabel("Latitude: ");
		lblLatitude.setBounds(12, 92, 56, 16);
		frame.getContentPane().add(lblLatitude);

		longTextField = new JTextField();
		longTextField.setBounds(95, 60, 153, 22);
		frame.getContentPane().add(longTextField);
		longTextField.setColumns(10);

		latTextField = new JTextField();
		latTextField.setBounds(95, 89, 153, 22);
		frame.getContentPane().add(latTextField);
		latTextField.setColumns(10);

		JLabel lblQueryCount = new JLabel("Query Count: ");
		lblQueryCount.setBounds(12, 152, 80, 16);
		frame.getContentPane().add(lblQueryCount);

		queryCountTextField = new JTextField();
		queryCountTextField.setBounds(95, 149, 153, 22);
		frame.getContentPane().add(queryCountTextField);
		queryCountTextField.setColumns(10);

		JRadioButton languageState = new JRadioButton("");
		languageState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (languageTableCom.isEnabled() == true) {
					languageTableCom.setEnabled(false);
					langState = "English";
				}
				else {
					languageTableCom.setEnabled(true);
					langState = languageTableCom.getSelectedItem().toString();
					System.out.println(langState);
				}
			}
		});
		languageState.setBounds(256, 30, 127, 25);
		frame.getContentPane().add(languageState);
		
		JRadioButton locationState = new JRadioButton("");
		locationState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (longTextField.isEnabled() == true 
						&& latTextField.isEnabled() == true 
						&& radiusText.isEnabled() == true) {
					longTextField.setEnabled(false);
					latTextField.setEnabled(false);
					radiusText.setEnabled(false);
				}
				else {
					longTextField.setEnabled(true);
					latTextField.setEnabled(true);
					radiusText.setEnabled(true);
				}
			}
		});
		locationState.setBounds(256, 59, 127, 25);
		frame.getContentPane().add(locationState);
		
		JLabel radiusLabel = new JLabel("Radius(mi):");
		radiusLabel.setBounds(12, 123, 80, 16);
		frame.getContentPane().add(radiusLabel);
		
		radiusText = new JTextField();
		radiusText.setBounds(95, 120, 153, 22);
		frame.getContentPane().add(radiusText);
		radiusText.setColumns(10);
		
		JButton searchBtn = new JButton("SEARCH");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Prepare variables
				//Security Checks
				String language = "";
				String lat = "";
				String longt = "";
				String radius = "";
				String qCount = "";
				if (languageState.isSelected() == true) {
					language = "English";
				}
				else {
					language = languageTableCom.getSelectedItem().toString();
				}
				
				if (locationState.isSelected() == true) {
					//Set default value
					lat = "0";
					longt = "0";
					radius = "3959";
					
				}
				else {
					
					lat = latTextField.getText();
					longt = longTextField.getText();
					radius = radiusText.getText();
				}
				
				if (queryCountTextField.getText().isEmpty()) {
					qCount = "10";
				}
				else {
					qCount = queryCountTextField.getText();
				}
				
				//Prepare SearchConfig with the appropriate variables
				sc = SearchConfig.getInstance().setLanguage(LanguageTable.langTable.get(language))
						.setLatitude(lat)
						.setLongtitude(longt)
						.setRadius(radius)
						.setQueryCount(Integer.parseInt(qCount)); 
				
				//Prepare Http client, post, and response variables and settings
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(postAddress);
				HttpResponse response;
				HttpGet get = new HttpGet(serviceAddress);

				//Send SearchConfig to server
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				try {//use a method here
					String json = ow.writeValueAsString(sc);
					StringEntity se = new StringEntity(json);
					se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
					get.addHeader("accept", "application/json");

					post.setEntity(se);
					response = client.execute(post);
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String ResponseSummaryTable = EntityUtils.toString(entity);
						System.out.println("Body: " + ResponseSummaryTable);
					}
					
					if (response.getStatusLine().getStatusCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ response.getStatusLine().getStatusCode()); 
					}
					BufferedReader br = new BufferedReader(
							new InputStreamReader((response.getEntity().getContent())));
					client.getConnectionManager().shutdown();

					
				} catch (JsonProcessingException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		searchBtn.setBounds(151, 184, 97, 25);
		frame.getContentPane().add(searchBtn);
		
		
	}
}
