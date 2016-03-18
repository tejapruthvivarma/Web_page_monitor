package Assignment_4;

import java.io.IOException;

public class Lab3 {
	public static void main(String args[]) throws IOException {
		String filename = "URL.txt";
		Factory_UrlConnection realUrlConnection = new Factory_UrlConnection();
		//Factory_UrlConnection mockUrlConnection = new MockUrlCreator();
		UrlReader aUrlReader = new UrlReader(filename,realUrlConnection);
		aUrlReader.parse();

	}

}
