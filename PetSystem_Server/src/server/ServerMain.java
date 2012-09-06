package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	private boolean state = true;
	ServerSocket ss;
	public void StartServer() {
		try {
			// 构造服务器ServerSocket对象，参数为服务器端开放的端口号
			ss = new ServerSocket(8080);
			System.out.println("服务器准备就绪！");
			state = true;
			// 死循环可以使服务器持续处于接收客户端状态
			while (true) {
				if(!state){
					ss.close();
					System.out.println("服务器已关闭！");
					break;
				}else{
				// 该方法使程序阻塞，等待客户端的链接，当监听到客户端的链接，创建一个Socket对象与客户端单独会话
				Socket s = ss.accept();
				// 为了不影响服务器监听其它客户端，这里开启了一个线程，由线程处理与这个客户端的会话
				new ServerThread(s).start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void StopServer() throws IOException {
		ss.close();
		state = false;
	}
}

class ServerThread extends Thread {
	private Socket s = null;
	private BufferedReader read = null;
	private PrintStream print = null;

	public ServerThread(Socket s) {
		this.s = s;
		try {
			// 从Socket中获取输入流和输出流，由于我们只做一个简单的字符串通讯，所以采用BufferedRead和PrintStream来封装输入、输出流
			read = new BufferedReader(new InputStreamReader(s.getInputStream()));
			print = new PrintStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			// 这里循环可以使服务器持续的接收客户端信息。read.readLine()通过输入流读取一段字符串，赋值给message变量，如果message字符串不为“exit”则循环，否则结束循环
			String line = read.readLine();
			String method = read.readLine();
			if (method.equals("login")) {
				userManager manager = new userManager();
				String loginResult = manager.checkLogin(line);
				if (!loginResult.equals("")) {
					print.println(loginResult);
				}
			}
			if (method.equals("regist")) {
				userManager manager = new userManager();
				String registResult = manager.regist(line);
				if (!registResult.equals("")) {
					print.println(registResult);
				}
			}
			if (method.equals("download")) {
				userManager manager = new userManager();
				String downloadResult = manager.download(line);
				if (!downloadResult.equals("")) {
					print.println(downloadResult);
				}
			}
			if (method.equals("upload")) {
				userManager manager = new userManager();
				String uploadResult = manager.upload(line);
				if (uploadResult.equals("true")) {
					print.println(uploadResult);
				}
			}
			if (method.equals("delete")) {
				userManager manager = new userManager();
				String deleteResult = manager.delete(line);
				if (deleteResult.equals("true")) {
					print.println(deleteResult);
				}
			}
			if (method.equals("repassword")) {
				userManager manager = new userManager();
				String repasswordResult = manager.repassword(line);
				if (repasswordResult.equals("true")) {
					print.println(repasswordResult);
				}
			}
		} catch (IOException e) {
		} finally {
			// 在 finally 代码块中无论如何都会执行下面代码：
			try {
				// 如果没有关闭Socket
				if (!s.isClosed()) {
					// 关闭Socket链接
					s.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}