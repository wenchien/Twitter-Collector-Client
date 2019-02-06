package utilies;

import java.util.*;
public class SearchConfig {
	private static SearchConfig sc;
	//Request ID
	private String id;
	
	//OAuth Consumer and AccessToken
	private String OAuthConsumerKey;
	private String OAuthConsumerSecret;
	private String OAuthAccessToken;
	private String OAuthAccessTokenSecret;
	
	//Location Long/Lat Strings
	private String latitude;
	private String longtitude;
	private String radius;
	
	//Language Strings
	//language are usually in the format of "langtag:language code" 
	//Example: "lang:ja"
	//There is a default value for this
	private String language = "lang:en";
	
	//Query Count
	private int queryCount;
	//this class will do method chaining
	private SearchConfig() {
		setId(IDGeneration.generateID());
	}
	
	public static SearchConfig getInstance() {
		if (sc == null) {
			sc = new SearchConfig();
		}
		return sc;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOAuthConsumerKey() {
		return OAuthConsumerKey;
	}
	public SearchConfig setOAuthConsumerKey(String oAuthConsumerKey) {
		OAuthConsumerKey = oAuthConsumerKey;
		return this;
	}
	public String getOAuthConsumerSecret() {
		return OAuthConsumerSecret;
	}
	public SearchConfig setOAuthConsumerSecret(String oAuthConsumerSecret) {
		OAuthConsumerSecret = oAuthConsumerSecret;
		return this;
	}
	public String getOAuthAccessToken() {
		return OAuthAccessToken;
	}
	public SearchConfig setOAuthAccessToken(String oAuthAccessToken) {
		OAuthAccessToken = oAuthAccessToken;
		return this;
	}
	public String getOAuthAccessTokenSecret() {
		return OAuthAccessTokenSecret;
	}
	public SearchConfig setOAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
		OAuthAccessTokenSecret = oAuthAccessTokenSecret;
		return this;
	}
	public String getLatitude() {
		return latitude;
	}
	public SearchConfig setLatitude(String latitude) {
		this.latitude = latitude;
		return this;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public SearchConfig setLongtitude(String longtitude) {
		this.longtitude = longtitude;
		return this;
	}
	public String getLanguage() {
		return language;
	}
	public SearchConfig setLanguage(String language) {
		this.language = language;
		return this;
	}
	public int getQueryCount() {
		return queryCount;
	}
	public SearchConfig setQueryCount(int queryCount) {
		this.queryCount = queryCount;
		return this;
	}

	public String getRadius() {
		return radius;
	}

	public SearchConfig setRadius(String radius) {
		this.radius = radius;
		return this;
	}
}
