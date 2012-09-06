package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class userManager {
	public String checkLogin(String pname) {
		String result = "null";
		File f = new File("users.txt");
		if(f.exists()){
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();

			while (s != null) {
				int num = s.indexOf(pname);// 获得pname所在的下标
				if (num != -1) {
					String[] ss = s.split("----");
					result = ss[1];
				}
				s = br.readLine();
			}
			br.close();// 关闭缓冲读入流及文件读入流的连接.
		} catch (FileNotFoundException e1) {
			System.err.println("File not found!");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		}else{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public String regist(String unamepword) {
		String result = "null";
		File f = new File("users.txt");
		if(f.exists()){
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			FileWriter writer = new FileWriter("users.txt", true);
			String nextLine = System.getProperty("line.separator");
			String s = br.readLine();
			String[] ss = unamepword.split("/");
			String username = ss[0];
			String password = ss[1];
			if (!queryUsername(username)) {
				String str = username + "----" + password;
				while (s != null) {
					s = br.readLine();
				}
				writer.write(str + nextLine);
				result = username;
				// System.out.println("注册成功内部返回"+result);
				writer.flush();
				writer.close();
			}
		} catch (FileNotFoundException e1) {
			System.err.println("File not found!");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// ystem.out.println("regist返回:"+result);
		if (!result.equals("null")) {
			File user_pet = new File("users_pet/" + result + "_pet.txt");
			try {
				user_pet.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}else{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean queryUsername(String username) {
		boolean result = false;
		File f = new File("users.txt");
		if(f.exists()){
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();

			while (s != null) {
				int num = s.indexOf(username);
				if (num != -1) {
					result = true;
				}
				s = br.readLine();
			}
			br.close();// 关闭缓冲读入流及文件读入流的连接.
		} catch (FileNotFoundException e1) {
			System.err.println("File not found!");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		}else{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public String download(String txtSource) {
		String result = "";
		File f = new File(txtSource);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				result += s;
				result += ";";
				s = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String upload(String txtSource) {
		String result = "false";
		try {
			String[] source = txtSource.split("~");
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(source[0]), "GBK"));
			String[] writestring = source[1].split(";");
			if (!source[1].equals(null)) {
				for (int i = 0; i < writestring.length; i++) {
					writer.println(writestring[i]);
				}
				result = "true";
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String delete(String txtSource) {
		String result = "false";
		String str = "";
		File f = new File("users.txt");
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				int num = s.indexOf(txtSource);// 获得pname所在的下标
				if (num != -1) {
					s = br.readLine();
					result = "true";
					drop(txtSource);
				} else {
					str += s;
					str += "\n";
					s = br.readLine();
				}
			}
			writerLine(str, "users.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void writerLine(String string, String txtSource) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(txtSource, false);
			writer.write(string);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String repassword(String txtSource) {
		String result = "false";
		String str = "";
		File f = new File("users.txt");
		try {
			String[] ss = txtSource.split("/");
			String username = ss[0];
			String newpassword = ss[1];
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				int num = s.indexOf(username);// 获得pname所在的下标
				if (num != -1) {
					str += username + "----" + newpassword;
					str += "\n";
					s = br.readLine();
					result = "true";
				} else {
					str += s;
					str += "\n";
					s = br.readLine();
				}
			}
			writerLine(str, "users.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void drop(String string){
		File f = new File("users_pet/"+string+"_pet.txt");
		System.out.println("users_pet/"+string+"_pet.txt");
		if(f.exists()){
			f.delete();
		}
	}
}
