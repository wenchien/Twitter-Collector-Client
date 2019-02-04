package utilies;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IDGeneration {
	static final String lIdentifiers = "abcdefghijklmnopqrstuvwxyz";
	static final String uIdentifiers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String nIdentifiers = "1234567890";
	static final String[] identifiers = {lIdentifiers, uIdentifiers, nIdentifiers};
	
	public static String generateID() {
		int answer = 0;
		int index = 0;
		StringBuilder str1 = new StringBuilder("");
		for (int i = 0; i < 10; i++) {
			answer = ThreadLocalRandom.current().nextInt(0, 3);
			char[] temp = identifiers[answer].toCharArray();
			index = ThreadLocalRandom.current().nextInt(0, temp.length);
			str1.append(temp[index]);
		}
		return str1.toString();
	}
}
