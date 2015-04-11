public class GameException extends Exception {

	String message = null;

	public GameException() {

	}

	public GameException(String message) {
		super(message);
		this.message = message;
	}

}