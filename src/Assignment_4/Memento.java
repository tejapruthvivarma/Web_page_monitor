package Assignment_4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Memento {
	private long modifiedDate;
	private int contentLength;

	public Memento(String url) {
		File file = new File("back_up.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			getPreviousState(file, url);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void getPreviousState(File file, String url)
			throws FileNotFoundException, IOException {

		// BufferedReader bfr = new BufferedReader(new FileReader(file));
		Scanner fileScanner = new Scanner(file);
		String[] lineTokens;
		while (fileScanner.hasNextLine()) {
			String nextURL = fileScanner.nextLine();
			lineTokens = nextURL.split(" ");
			if (lineTokens[0].compareToIgnoreCase(url) == 0) {
				modifiedDate = Long.parseLong(lineTokens[1]);
				contentLength = Integer.parseInt(lineTokens[2]);
			}

		}

	}

	public void writeFile(String url, long modifiedDate)
			throws FileNotFoundException, IOException {

		File file = new File("back_up.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Scanner fileScanner = new Scanner(file);
		String lines = "";
		String[] lineTokens;
		boolean urlFound = false;
		while (fileScanner.hasNextLine()) {
			String nextLine = fileScanner.nextLine();
			lineTokens = nextLine.split(" ");
			if (!lineTokens[0].equalsIgnoreCase(url)) {
				lines = lines + nextLine + System.lineSeparator();
			}

		}
		lines = lines + url + " " + modifiedDate + " " + contentLength
				+ System.lineSeparator();

		BufferedWriter aBufferedWriter = new BufferedWriter(
				new FileWriter(file));
		aBufferedWriter.write(lines);
		aBufferedWriter.close();

	}

	protected void setState(String url, long modifiedDate, int contentLength) {

		try {
			writeFile(url, modifiedDate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected long getState_modifiedDate() {
		return modifiedDate;

	}

	protected int getState_contentLength() {
		return contentLength;

	}

}
