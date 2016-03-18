package Assignment_4;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import static org.mockito.Mockito.*;

public class MockUrlCreator extends Factory_UrlConnection {
	public URLConnection CreateURLConnection(URL address) throws IOException {
		URLConnection urlConnection = mock(URLConnection.class);
		when(urlConnection.getLastModified()).thenReturn(
				System.currentTimeMillis());
		return urlConnection;
	}

}
