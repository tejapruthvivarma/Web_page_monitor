package Assignment_4;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Factory_UrlConnection {

	public URLConnection CreateURLConnection(URL address) throws IOException {

		return address.openConnection();

	}

}
