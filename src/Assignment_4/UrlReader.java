package Assignment_4;

import java.io.File;
import java.io.IOException;
import java.util.Observer;
import java.util.Scanner;

public class UrlReader {

	private String filename;
	private Factory_UrlConnection urlConnection;

	public UrlReader(String filename, Factory_UrlConnection urlConnection) {
		this.filename = filename;
		this.urlConnection = urlConnection;
	}

	public void parse() throws IOException {

		Scanner fileScanner = new Scanner(new File(filename));

		String[] lineTokens;
		while (fileScanner.hasNextLine()) {
			String nextURL = fileScanner.nextLine();
			lineTokens = nextURL.split(" ");

			Subject_webPage aWebPage = new Subject_webPage(lineTokens[0],
					urlConnection);
			for (int i = 1; i < lineTokens.length; i++) {
				if (lineTokens[i].compareToIgnoreCase("mail") == 0) {
					Observer mailObserver = new MailObserver();
					aWebPage.addObserver(mailObserver);
				} else if (lineTokens[i].compareToIgnoreCase("transcript") == 0) {
					Observer transcriptObserver = new TranscriptObserver();
					aWebPage.addObserver(transcriptObserver);
				}

			}

			aWebPage.connection();
		}
		fileScanner.close();
	}
}
