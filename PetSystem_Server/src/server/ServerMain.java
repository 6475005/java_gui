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
			// ���������ServerSocket���󣬲���Ϊ�������˿��ŵĶ˿ں�
			ss = new ServerSocket(8080);
			System.out.println("������׼��������");
			state = true;
			// ��ѭ������ʹ�������������ڽ��տͻ���״̬
			while (true) {
				if(!state){
					ss.close();
					System.out.println("�������ѹرգ�");
					break;
				}else{
				// �÷���ʹ�����������ȴ��ͻ��˵����ӣ����������ͻ��˵����ӣ�����һ��Socket������ͻ��˵����Ự
				Socket s = ss.accept();
				// Ϊ�˲�Ӱ����������������ͻ��ˣ����￪����һ���̣߳����̴߳���������ͻ��˵ĻỰ
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
			// ��Socket�л�ȡ�����������������������ֻ��һ���򵥵��ַ���ͨѶ�����Բ���BufferedRead��PrintStream����װ���롢�����
			read = new BufferedReader(new InputStreamReader(s.getInputStream()));
			print = new PrintStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			// ����ѭ������ʹ�����������Ľ��տͻ�����Ϣ��read.readLine()ͨ����������ȡһ���ַ�������ֵ��message���������message�ַ�����Ϊ��exit����ѭ�����������ѭ��
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
			// �� finally �������������ζ���ִ��������룺
			try {
				// ���û�йر�Socket
				if (!s.isClosed()) {
					// �ر�Socket����
					s.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}