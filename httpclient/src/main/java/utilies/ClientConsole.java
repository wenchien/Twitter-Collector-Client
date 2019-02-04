package utilies;

public class ClientConsole {
	private static ClientConsole ccInstance;
	
	private ClientConsole() {
	}
	
	public static ClientConsole getInstance() {
		if (ccInstance == null) {
			ccInstance = new ClientConsole();
		}
		
		return ccInstance;
	}
	
	public static void write() {
		
	}
	
	public static void outputToStreams() {
		
	}
}
