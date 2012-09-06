package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private String line = "";

	public String setClient(String string, String method, String IP) {
		try {
			Socket socket = new Socket(IP, 8080);// 建立连接
			OutputStream ous = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(ous));
			InputStream ins = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					ins));
			socket.setSoTimeout(3000);
			writer.println(string);
			writer.println(method);
			writer.flush();
			line = reader.readLine();
			reader.close();
			writer.close();
			socket.close();
		} catch (Exception ex) {
			line = "serverStop";
		}
		return line;
	}
}
