package utilies;

public interface Command {
	default void runCommand(String o) {}
	default void runCommandNum(int i) {}
}
