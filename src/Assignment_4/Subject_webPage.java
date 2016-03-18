package Assignment_4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Subject_webPage extends Observable{
	private String url;
	private long modifiedDate;
	private int contentLength;
	private ArrayList<Observer> observers = new ArrayList<>();
	private Factory_UrlConnection urlConnection = new Factory_UrlConnection();
	private Memento memento;

	public Subject_webPage(String url, Factory_UrlConnection urlConnector) {
		this.url = url;
		this.urlConnection = urlConnector;
		restoreState();
	}
public void restoreState(){
	memento = new Memento(url);
	modifiedDate = memento.getState_modifiedDate();
	contentLength = memento.getState_contentLength();
}
	public void connection() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				while (true) {
					URL address = null;

					try {
						address = new URL(url);
					} catch (MalformedURLException e1) {

						e1.printStackTrace();
					}

					URLConnection connect = null;
					try {
						connect = urlConnection.CreateURLConnection(address);
					} catch (IOException e) {

						e.printStackTrace();
					}

					long time = connect.getLastModified();
					int tempContentlength = connect.getContentLength();
					if ((modifiedDate - time != 0) || (contentLength-tempContentlength !=0)) {
						modifiedDate = time;
						contentLength = tempContentlength;
						synchronized(this){memento.setState(url,time,tempContentlength);}
						notifyObserver();
					}
					
				}
			}
		});
		t1.start();
	}

	@Override
	public void addObserver(Observer o) {

		observers.add(o);
	}

	@Override
	public void deleteObserver(Observer O) {
		observers.remove(O);

	}

	public void notifyObserver() {
		for (int i = 0; i < observers.size(); i++) {
			String message = url + " is updated on " + new Date(modifiedDate).toString();
			observers.get(i).update(this, message);
		}
	}

}
