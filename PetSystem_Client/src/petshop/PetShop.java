package petshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import object.Pet;

@SuppressWarnings("serial")
public class PetShop extends IOException {

	private int foot;
	PrintWriter out;

	public int getFoot() {
		return foot;
	}

	public void setFoot(int foot) {
		this.foot = foot;
	}

	synchronized public boolean add(Pet pet, String txtSource) {

		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			File f = new File(txtSource);
			if (f.exists()) {
				FileWriter writer = new FileWriter(txtSource, true);
				String nextLine = System.getProperty("line.separator");
				String str = pet.getName() + "/" + pet.getType() + "/"
						+ pet.getAge() + "/" + pet.getColor();
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String s = br.readLine();
				while (s != null) {
					s = br.readLine();
				}

				writer.write(str + nextLine);
				writer.flush();
				writer.close();
			} else {
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public void writerLine(String string, String txtSource) {
		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			FileWriter writer = new FileWriter(txtSource, false);
			writer.write(string);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String search(String pname, String txtSource) {
		String str = null;
		File f = new File(txtSource);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();

			while (s != null) {
				int num = s.indexOf(pname);// ���pname���ڵ��±�
				if (num != -1) {
					String[] ss = s.split("/");
					str = "����--" + ss[0] + "    ���--" + ss[1] + "    ����--"
							+ ss[2] + "    ��ɫ--" + ss[3];
				}
				s = br.readLine();
			}
			br.close();// �رջ�����������ļ�������������.
		} catch (FileNotFoundException e1) {
			System.err.println("File not found!");
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		/*
		 * if (petList.size() > keyWord - 1) { String name = petList.get(keyWord
		 * - 1).getName(); String color = petList.get(keyWord - 1).getColor();
		 * String age = petList.get(keyWord - 1).getAge();
		 * 
		 * System.out.println("�������Ϊ�� ��������" + name + "������ɫ" + color + "��������" +
		 * age); } else { System.out.println("������󣬲�ѯ���ȳ��磡"); }
		 */
		return str;
	}

	/*
	 * public void search(String keyWord) { boolean f = false; for (pet pet :
	 * petList) { if (pet.getName().equals(keyWord)) { String name =
	 * pet.getName(); String color = pet.getColor(); String age = pet.getAge();
	 * f = true; System.out.println("�������Ϊ�� ��������" + name + "������ɫ" + color +
	 * "��������" + age); } } if (!f) System.out.println("�������Ϊ��ѯ�������ֳ��"); }
	 */
	public void clean(String txtSource) {
		try {
			FileWriter writer = new FileWriter(txtSource, false);
			writer.write("");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String showAll(String txtSource) {
		String str = "";
		File f = new File(txtSource);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				str += s;
				str += "\n";
				s = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public ArrayList<Pet> showAllInTable(String txtSource) {
		ArrayList<Pet> petList = new ArrayList<Pet>();
		File f = new File(txtSource);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				Pet pet = new Pet();
				String[] ss = s.split("/");
				pet.setName(ss[0]);
				pet.setType(ss[1]);
				pet.setAge(Integer.valueOf(ss[2]));
				pet.setColor(ss[3]);
				petList.add(pet);
				s = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return petList;
	}

	public boolean delete(String pname, String txtSource) {
		boolean result = false;
		String str = "";
		File f = new File(txtSource);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				int num = s.indexOf(pname);// ���pname���ڵ��±�
				if (num != -1) {
					s = br.readLine();
					result = true;
				} else {
					str += s;
					str += "\n";
					s = br.readLine();
				}
			}
			writerLine(str, txtSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updata(String name, String retype, String rename,
			String reage, String recolor, String txtSource) {
		boolean result = false;
		String str = "";
		File f = new File(txtSource);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				int num = s.indexOf(name);// ���pname���ڵ��±�
				if (num != -1) {
					str += rename + "/" + retype + "/" + reage + "/" + recolor;
					str += "\n";
					s = br.readLine();
					result = true;
				} else {
					str += s;
					str += "\n";
					s = br.readLine();
				}
			}
			writerLine(str, txtSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getPetNUM(String txtSource) {
		int num = 0;
		File f = new File(txtSource);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while (s != null) {
				num += 1;
				s = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
}
