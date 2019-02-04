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



public class ClientSide {
	public static String serviceAddress = "http://localhost:8080/test";
	public static String postAddress = "http://localhost:8080/post";
	private SearchConfig sc;
	private SettingsParser sp;
	private JFrame frame;
	private JTextField longTextField;
	private JTextField latTextField;
	private JTextField queryCountTextField;
	JComboBox languageTableCom = new JComboBox();
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
		frame.setBounds(100, 100, 284, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel langLabel = new JLabel("Language: ");
		langLabel.setBounds(12, 34, 72, 16);
		frame.getContentPane().add(langLabel);

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
		lblQueryCount.setBounds(12, 121, 80, 16);
		frame.getContentPane().add(lblQueryCount);

		queryCountTextField = new JTextField();
		queryCountTextField.setBounds(95, 118, 153, 22);
		frame.getContentPane().add(queryCountTextField);
		queryCountTextField.setColumns(10);

		JButton searchBtn = new JButton("SEARCH");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Prepare SearchConfig
				sc = SearchConfig.getInstance().setLanguage(LanguageTable.langTable.get(languageTableCom.getSelectedItem().toString()))
						.setLatitude(latTextField.getText())
						.setLongtitude(longTextField.getText())
						.setQueryCount(Integer.parseInt(queryCountTextField.getText())); 
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
		searchBtn.setBounds(151, 153, 97, 25);
		frame.getContentPane().add(searchBtn);
	}
}
