package Assignment_4;

import java.util.Observable;
import java.util.Observer;

public class TranscriptObserver implements Observer {
	private String message;

	@Override
	public void update(Observable o, Object arg) {
		setMessage(arg.toString());
		System.out.println(getMessage());

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
