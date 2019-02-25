package com.github.grimthereaper.javagitgaze.githubutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

// Partly borrowed from https://stackoverflow.com/a/4308662
// Using gson instead of org.json because it is better.
public class JsonReader {

	private static final String token = "";
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static String readJsonFromUrl(String url) throws IOException {		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		
		con.setRequestProperty("Authorization", "token " + token);
		
		
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			String jsonText = readAll(rd);

			return jsonText;
		} finally {
			con.getInputStream().close();
		}
	}

}
