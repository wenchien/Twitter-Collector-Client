package utilies;
import java.util.*;
import java.io.*;

public class SettingsParser {
	private static String filePath = "files/config.txt";
	private static SettingsParser sp;
	private static ClassLoader classLoader;
	private static Map<String, Command> commands = new HashMap<>();
	private static SearchConfig sc;
	private SettingsParser(SearchConfig sc) {
		classLoader = getClass().getClassLoader();
		SettingsParser.sc = sc;
		commands.put("OAuthConsumerKey", new Command() {
			public void runCommand(String o) {
				setOAuthConsumerKey(o);
			}
		});
		commands.put("OAuthConsumerSecret", new Command() {
			public void runCommand(String o) {
				setOAuthConsumerSecret(o);
			}
		});
		commands.put("OAuthConsumerAccessToken", new Command() {
			public void runCommand(String o) {
				setOAuthAccessToken(o);
			}
		});
		commands.put("OAuthConsumerAccessTokenSecret", new Command() {
			public void runCommand(String o) {
				setOAuthAccessTokenSecret(o);
			}
		});
	}

	public static SettingsParser getInstance(SearchConfig sc) {
		if (sp == null) {
			sp = new SettingsParser(sc);
		}
		return sp;
	}

	public void load() {
		//Get file from resources folder
		File file = new File(classLoader.getResource(filePath).getFile());
		String checker = "";
		try (Scanner scanner = new Scanner(file)){
			while(scanner.hasNextLine()) {
				checker = scanner.nextLine();
				if (checker.startsWith("[")) {
					continue;
				}
				else {
					String[] parser = checker.split("=");
					commands.get(parser[0]).runCommand(parser[1]);
					System.out.println("Settings Parsed Successfully");
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void setOAuthConsumerKey(String o) {
		if (sc != null) {
			sc.setOAuthConsumerKey(o);
		}
		else {

		}
	}
	public static void setOAuthConsumerSecret(String o) {
		if (sc != null) {
			sc.setOAuthConsumerSecret(o);
		}
		else {

		}
	}
	public static void setOAuthAccessToken(String o) {
		if (sc != null) {
			sc.setOAuthAccessToken(o);
		}
		else {

		}
	}
	public static void setOAuthAccessTokenSecret(String o) {
		if (sc != null) {
			sc.setOAuthAccessTokenSecret(o);
		}
		else {

		}
	}

	public static SearchConfig getSearchConfig() {
		return sc;
	}

	public static void setSearchConfig(SearchConfig sc) {
		SettingsParser.sc = sc;
	}
}
