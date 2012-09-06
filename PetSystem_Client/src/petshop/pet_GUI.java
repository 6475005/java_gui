package petshop;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import client.Client;

import object.Pet;

public class pet_GUI {
	JFrame jf = new JFrame("����������V1.0");
	private ButtonPanel logPanel = new ButtonPanel("", new String[] {});
	private ButtonPanel loginPanel = new ButtonPanel("�û���¼", new String[] {});
	private ButtonPanel welcomePanel = new ButtonPanel("��¼�ɹ�", new String[] {});
	private ButtonPanel managerPanel = new ButtonPanel("�������", new String[] {});
	private ButtonPanel dataPanel = new ButtonPanel("����ͬ���뱸��", new String[] {});
	private ButtonPanel queryPanel = new ButtonPanel(null, new String[] {});
	private ButtonPanel versionPanel = new ButtonPanel("�汾��Ϣ", new String[] {});
	private JPanel panel = new JPanel();// ��¼���
	private JPanel panel_ = new JPanel();// ��ӭ���
	private JPanel panel0 = new JPanel();// �������
	private JPanel panel2 = new JPanel();// ��ѯ���
	private JPanel panel3 = new JPanel();// ��ʾ���
	private JPanel panel4 = new JPanel();// �汾���
	private String txtSource = "error";
	private String IP = "127.0.0.1";
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField();
	private JTextField username = new JTextField("�˺�", 10);
	private JTextField newpassword = new JTextField("", 10);
	private JPasswordField password = new JPasswordField(10);
	private JTextField name = new JTextField("��������", 10);
	private JTextField age = new JTextField("��������", 10);
	private JTextField color = new JTextField("������ɫ", 10);
	private JTextField rename = new JTextField("��������", 10);
	private JTextField reage = new JTextField("��������", 10);
	private JTextField recolor = new JTextField("������ɫ", 10);
	private JTextField queryname = new JTextField("��������", 10);
	private JTextField updataname = new JTextField("��������", 10);
	private JTextField deletename = new JTextField("��������", 10);
	private JTextField IPaddress = new JTextField("������ip", 10);
	private JLabel lname1 = new JLabel("��������:");
	private JLabel lname2 = new JLabel("��������:");
	private JLabel lname3 = new JLabel("��������:");
	private JLabel username1 = new JLabel("�û��ǳ�:");
	private JLabel password1 = new JLabel("��¼����:");
	private JComboBox comboBox = new JComboBox();
	private JComboBox comboBoxType = new JComboBox();
	private JButton insert = new JButton("��ӳ���");
	private JButton query = new JButton("��ѯ����");
	private JButton delet = new JButton("ɾ������");
	private JButton updata = new JButton("�޸ĳ���");
	private JButton petNum = new JButton("��ѯ��������");
	private JButton Drop = new JButton("��ճ����̵�");
	private JButton login = new JButton("��¼�����̵�");
	private JButton regist = new JButton("ע������̵�");
	private JButton relogin = new JButton("�л��˺ŵ�¼");
	private JButton getDate = new JButton("��ѯ��ǰʱ��");
	private JButton download = new JButton("�������ͬ������");
	private JButton upload = new JButton("�������ݵ�������");
	private JButton repassword = new JButton("�û������޸�");
	private JButton deleteuser = new JButton("�û���Ϣע��");
	private JTable table = new JTable();
	private JTextField filenameTF = new JTextField(), dirTF = new JTextField();

	public void init() {
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!getIP().equals("null")) {
					Client login = new Client();
					if (username.getText().equals("�˺�")) {
						JOptionPane.showMessageDialog(null, "�������˺ţ�", "����",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (password.getText().equals("����")) {
							JOptionPane.showMessageDialog(null, "���������룡", "����",
									JOptionPane.ERROR_MESSAGE);
						} else {

							String loginresult = login.setClient(
									username.getText(), "login", IP);
							if (loginresult.equals("serverStop")) {
								JOptionPane.showMessageDialog(null, "������δ������",
										"����", JOptionPane.ERROR_MESSAGE);
							} else {
								if (loginresult.equals(password.getText())) {
									txtSource = "users_pet/"
											+ username.getText() + "_pet.txt";
									JOptionPane.showMessageDialog(null,
											"��¼�ɹ���", "��ϲ",
											JOptionPane.DEFAULT_OPTION);
									File f = new File("users_pet/"
											+ username.getText() + "_pet.txt");
									if (!f.exists()) {
										try {
											f.createNewFile();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									// ��ӭ����
									JPanel panel_01 = new JPanel();
									JPanel panel_02 = new JPanel();
									JPanel panel_03 = new JPanel();
									JPanel panel_04 = new JPanel();
									JPanel panel_05 = new JPanel();
									JPanel panel_06 = new JPanel();
									JPanel panel_07 = new JPanel();
									panel_06.add(relogin);
									panel_06.add(getDate);
									panel_07.add(repassword);
									panel_07.add(deleteuser);
									Date now = new Date();
									DateFormat date = DateFormat
											.getDateTimeInstance(
													DateFormat.FULL,
													DateFormat.FULL);
									String str = date.format(now);

									Box welcome = Box.createVerticalBox();
									welcome.add(panel_01
											.add(new JLabel("��¼�ɹ���")));
									welcome.add(panel_02.add(new JLabel("��ӭ����"
											+ username.getText() + "   !")));
									welcome.add(panel_03.add(new JLabel(
											"��¼ʱ��Ϊ��")));
									welcome.add(panel_04.add(new JLabel(
											"������ʱ�䣩")));
									welcome.add(panel_05.add(new JLabel(str)));
									welcome.add(panel_06);
									welcome.add(panel_07);
									welcomePanel.add(welcome);
									panel_.add(welcomePanel);

									tabs.removeAll();
									tabs.add("��ӭ����", panel_);
									tabs.add("�������", panel0);
									tabs.add("��ѯ����", panel2);
									tabs.add("��ʾ����", panel3);
									tabs.add("�汾��Ϣ", panel4);
								} else {
									JOptionPane.showMessageDialog(null,
											"�û������������", "����",
											JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				} else {
					IPaddress.setText(JOptionPane.showInputDialog(IPaddress,
							"�״�ʹ�������÷����IP", JOptionPane.DEFAULT_OPTION));
					setIP(IPaddress.getText());
				}
			}
		});
		relogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtSource = "error";
				tabs.removeAll();
				panel_.removeAll();
				welcomePanel.removeAll();
				tabs.add("��¼����", panel);
				tabs.add("�������", panel0);
				tabs.add("��ѯ����", panel2);
				tabs.add("��ʾ����", panel3);
				tabs.add("�汾��Ϣ", panel4);
			}
		});
		repassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newpassword.setText(null);
				newpassword.setText(JOptionPane.showInputDialog(newpassword,
						"������������:", JOptionPane.DEFAULT_OPTION));
				Client repassword = new Client();
				if (repassword.setClient(
						username.getText() + "/" + newpassword.getText(),
						"repassword", IP).equals("true")) {
					JOptionPane.showMessageDialog(null, "�����޸ĳɹ���", "��ϲ",
							JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "�����޸�ʧ�ܣ�", "����",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteuser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null,
						"ע���󲻿ɻָ�,��ȷ��Ҫע���˺�?", "�˺�ע��", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					Client deleteuser = new Client();
					if (deleteuser.setClient(username.getText(), "delete", IP)
							.equals("true")) {
						JOptionPane.showMessageDialog(null, "ע���ɹ���", "��ϲ",
								JOptionPane.DEFAULT_OPTION);
						File f = new File("users_pet/" + username.getText()
								+ "_pet.txt");
						f.delete();
						txtSource = "error";
						tabs.removeAll();
						panel_.removeAll();
						welcomePanel.removeAll();
						tabs.add("��¼����", panel);
						tabs.add("�������", panel0);
						tabs.add("��ѯ����", panel2);
						tabs.add("��ʾ����", panel3);
						tabs.add("�汾��Ϣ", panel4);
					} else {
						JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�", "����",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				username.setText("�˺�");
				password.setText("");
			}
		});
		regist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!getIP().equals("null")) {
					Client regist = new Client();
					if (username.getText().equals("�˺�")) {
						JOptionPane.showMessageDialog(null, "�������˺ţ�", "����",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (password.getText().equals("����")) {
							JOptionPane.showMessageDialog(null, "���������룡", "����",
									JOptionPane.ERROR_MESSAGE);
						} else {
							String registresult = regist.setClient(
									username.getText() + "/"
											+ password.getText(), "regist", IP);
							if (registresult.equals("serverStop")) {
								JOptionPane.showMessageDialog(null, "������δ������",
										"����", JOptionPane.ERROR_MESSAGE);
							} else {
								if (registresult.equals(username.getText())) {
									JOptionPane.showMessageDialog(null,
											"ע��ɹ���", "��ϲ",
											JOptionPane.DEFAULT_OPTION);
								} else {
									JOptionPane.showMessageDialog(null,
											"�û����Ѵ��ڣ�", "����",
											JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
					username.setText("�˺�");
					password.setText("");
				} else {
					IPaddress.setText(JOptionPane.showInputDialog(IPaddress,
							"�״�ʹ�������÷����IP", JOptionPane.DEFAULT_OPTION));
					setIP(IPaddress.getText());
				}
			}
		});
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PetShop petshop = new PetShop();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (name.getText().equals("��������")) {
						JOptionPane.showMessageDialog(null, "���������������", "����",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (age.getText().equals("��������")) {
							JOptionPane.showMessageDialog(null, "������������䣡",
									"����", JOptionPane.ERROR_MESSAGE);
						} else {
							if (color.getText().equals("������ɫ")) {
								JOptionPane.showMessageDialog(null, "�����������ɫ��",
										"����", JOptionPane.ERROR_MESSAGE);
							} else {
								if (name.getText().length() < 2
										|| name.getText().length() > 6) {
									JOptionPane.showMessageDialog(null,
											"�������ֳ���ӦΪ2-6���ַ���", "����",
											JOptionPane.ERROR_MESSAGE);
								} else {
									if (!isInteger(age.getText())
											|| Integer.valueOf(age.getText()) <= 1
											|| Integer.valueOf(age.getText()) >= 99) {
										JOptionPane.showMessageDialog(null,
												"��������ӦΪ1-99��������", "����",
												JOptionPane.ERROR_MESSAGE);
									} else {
										if (color.getText().length() < 1
												|| color.getText().length() > 5) {
											JOptionPane.showMessageDialog(null,
													"������ɫ����ӦΪ1-5���ַ���", "����",
													JOptionPane.ERROR_MESSAGE);
										} else {
											if (comboBox.getSelectedIndex() == 1) {
												Pet pet = new Pet();
												pet.setPet(name.getText(),
														"���ﹷ",
														Integer.valueOf(age
																.getText()),
														color.getText());
												petshop.add(pet, txtSource);
												txt.setText("���ﹷ��"
														+ pet.getName()
														+ "����ɹ���");
											} else {
												Pet pet = new Pet();
												pet.setPet(name.getText(),
														"����è",
														Integer.valueOf(age
																.getText()),
														color.getText());
												petshop.add(pet, txtSource);
												txt.setText("����è��"
														+ pet.getName()
														+ "����ɹ���");
											}
										}
									}
								}
							}
						}
					}
				}
				name.setText("��������");
				age.setText("��������");
				color.setText("������ɫ");
			}
		});
		query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PetShop petshop = new PetShop();
				String qname = queryname.getText();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (qname.equals("/")) {
						JOptionPane.showMessageDialog(null, "���������������", "����",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (qname.equals("��������")) {
							JOptionPane.showMessageDialog(null, "���������������",
									"����", JOptionPane.ERROR_MESSAGE);
						} else {
							if (petshop.search(qname, txtSource) != null) {
								txt.setText(petshop.search(qname, txtSource));
							} else {
								txt.setText("δ��ѯ�������ֳ���");
							}
						}
					}
				}
				queryname.setText("��������");
			}
		});
		delet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PetShop petshop = new PetShop();
				String qname = deletename.getText();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (qname.equals("/")) {
						JOptionPane.showMessageDialog(null, "���������������", "����",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (deletename.getText().equals("��������")) {
							JOptionPane.showMessageDialog(null, "���������������",
									"����", JOptionPane.ERROR_MESSAGE);
						} else {
							if (petshop.delete(deletename.getText(), txtSource)) {
								txt.setText("����ɾ���ɹ���");
							} else {
								txt.setText("δ��ѯ�������ֳ��");
							}
						}
					}
				}
				deletename.setText("��������");
			}
		});
		updata.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PetShop petshop = new PetShop();
				String qname = updataname.getText();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (qname.equals("/")) {
						JOptionPane.showMessageDialog(null, "���������������", "����",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (updataname.getText().equals("��������")) {
							JOptionPane.showMessageDialog(null, "���������������",
									"����", JOptionPane.ERROR_MESSAGE);
						} else {
							if (rename.getText().equals("��������")) {
								JOptionPane.showMessageDialog(null,
										"�������޸ĺ�ĳ���������", "����",
										JOptionPane.ERROR_MESSAGE);
							} else {
								if (reage.getText().equals("��������")) {
									JOptionPane.showMessageDialog(null,
											"�������޸ĺ�ĳ������䣡", "����",
											JOptionPane.ERROR_MESSAGE);
								} else {
									if (recolor.getText().equals("������ɫ")) {
										JOptionPane.showMessageDialog(null,
												"�������޸ĺ�ĳ�����ɫ��", "����",
												JOptionPane.ERROR_MESSAGE);
									} else {
										if (rename.getText().length() < 2
												|| rename.getText().length() > 6) {
											JOptionPane.showMessageDialog(null,
													"�������ֳ���ӦΪ2-6���ַ���", "����",
													JOptionPane.ERROR_MESSAGE);
										} else {
											if (!isInteger(reage.getText())
													|| Integer.valueOf(reage
															.getText()) <= 1
													|| Integer.valueOf(reage
															.getText()) >= 99) {
												JOptionPane
														.showMessageDialog(
																null,
																"��������ӦΪ1-99��������",
																"����",
																JOptionPane.ERROR_MESSAGE);
											} else {
												if (recolor.getText().length() < 1
														|| recolor.getText()
																.length() > 5) {
													JOptionPane
															.showMessageDialog(
																	null,
																	"������ɫ����ӦΪ1-5���ַ���",
																	"����",
																	JOptionPane.ERROR_MESSAGE);
												} else {
													if (comboBoxType
															.getSelectedIndex() == 0) {
														if (petshop.updata(
																updataname
																		.getText(),
																rename.getText(),
																"����è",
																reage.getText(),
																recolor.getText(),
																txtSource)) {
															txt.setText("�����޸ĳɹ���");
														} else {
															txt.setText("δ��ѯ�������ֳ��");
														}
													} else {
														if (petshop.updata(
																updataname
																		.getText(),
																rename.getText(),
																"���ﹷ",
																reage.getText(),
																recolor.getText(),
																txtSource)) {
															txt.setText("�����޸ĳɹ���");
														} else {
															txt.setText("δ��ѯ�������ֳ��");
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				updataname.setText("��������");
				rename.setText("��������");
				reage.setText("��������");
				recolor.setText("������ɫ");
			}
		});
		petNum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PetShop petshop = new PetShop();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String str = "��ǰ��������г��" + petshop.getPetNUM(txtSource)
							+ "ֻ��";
					JOptionPane.showMessageDialog(null, str, "��������",
							JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		Drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int response = JOptionPane.showConfirmDialog(null,
							"��պ󲻿ɻָ�,��ȷ��Ҫ��ճ����̵�?", "��ճ����̵�",
							JOptionPane.YES_NO_OPTION);
					if (response == 0) {
						PetShop petshop = new PetShop();
						petshop.clean(txtSource);
						txt.setText("�����̵��Ѿ���գ�");
					}
				}
			}
		});
		getDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date now = new Date();
				DateFormat date = DateFormat.getDateTimeInstance(
						DateFormat.FULL, DateFormat.FULL);
				String str = date.format(now);
				JOptionPane.showMessageDialog(null, "��ǰ����ʱ�䣺" + str, "��ǰʱ��",
						JOptionPane.DEFAULT_OPTION);
			}
		});
		download.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Client download = new Client();
					String downloadresult = download.setClient(txtSource,
							"download", IP);
					if (!downloadresult.equals(null)) {
						JFileChooser jc = new JFileChooser();
						int rVal = jc.showSaveDialog(jc);
						if (rVal == JFileChooser.APPROVE_OPTION) {
							File dir = jc.getCurrentDirectory();
							File file = jc.getSelectedFile();
							filenameTF.setText(file.getName());
							dirTF.setText(dir.toString());
							write(new File(dir, file.getName()), downloadresult);
						}
						if (rVal == JFileChooser.APPROVE_OPTION) {
							filenameTF.setText("You pressed cancel");
							dirTF.setText("");
						}
						JOptionPane.showMessageDialog(null, "����ͬ���ɹ���", "��ϲ",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "����ͬ��ʧ�ܣ�", "����",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		upload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String uploadresult = "false";
					JFileChooser jc = new JFileChooser();
					int rVal = jc.showOpenDialog(jc);
					if (rVal == JFileChooser.APPROVE_OPTION) {
						File dir = jc.getCurrentDirectory();
						File file = jc.getSelectedFile();
						filenameTF.setText(file.getName());
						dirTF.setText(dir.toString());
						String reader = read(new File(dir, file.getName()));
						Client upload = new Client();
						uploadresult = upload.setClient(txtSource + "~"
								+ reader, "upload", IP);
					}
					if (rVal == JFileChooser.APPROVE_OPTION) {
						filenameTF.setText("You pressed cancel");
						dirTF.setText("");
					}

					if (uploadresult.equals("true")) {
						JOptionPane.showMessageDialog(null, "���ݱ��ݳɹ���", "��ϲ",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "���ݱ���ʧ�ܣ�", "����",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		name.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (name.getText().equals("")) {
					name.setText("��������");
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (name.getText().equals("��������")) {
					name.setText("");
				}
			}
		});
		deletename.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (deletename.getText().equals("")) {
					deletename.setText("��������");
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (deletename.getText().equals("��������")) {
					deletename.setText("");
				}
			}
		});
		updataname.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (updataname.getText().equals("")) {
					updataname.setText("��������");
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (updataname.getText().equals("��������")) {
					updataname.setText("");
				}
			}
		});
		age.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (age.getText().equals("")) {
					age.setText("��������");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (age.getText().equals("��������")) {
					age.setText("");
				}
			}
		});
		color.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (color.getText().equals("")) {
					color.setText("������ɫ");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (color.getText().equals("������ɫ")) {
					color.setText("");
				}
			}
		});
		queryname.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (queryname.getText().equals("")) {
					queryname.setText("��������");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (queryname.getText().equals("��������")) {
					queryname.setText("");
				}
			}
		});
		rename.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (rename.getText().equals("")) {
					rename.setText("��������");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (rename.getText().equals("��������")) {
					rename.setText("");
				}
			}
		});
		reage.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (reage.getText().equals("")) {
					reage.setText("��������");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (reage.getText().equals("��������")) {
					reage.setText("");
				}
			}
		});
		recolor.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (recolor.getText().equals("")) {
					recolor.setText("������ɫ");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (recolor.getText().equals("������ɫ")) {
					recolor.setText("");
				}
			}
		});
		username.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (username.getText().equals("")) {
					username.setText("�˺�");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (username.getText().equals("�˺�")) {
					username.setText("");
				}
			}
		});
		password.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (password.equals("")) {
					password.setText("����");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (password.equals("����")) {
					password.setText("");
				}
			}
		});
		// ��¼����
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		panel_1.add(username1);
		panel_1.add(username);
		panel_2.add(password1);
		panel_2.add(password);
		panel_3.add(login);
		panel_4.add(regist);
		Box topLogin = Box.createVerticalBox();
		topLogin.add(panel_1);
		topLogin.add(panel_2);
		topLogin.add(panel_3);
		topLogin.add(panel_4);
		loginPanel.add(topLogin);
		panel.add(logPanel);
		panel.add(loginPanel);
		tabs.addTab("��¼����", panel);
		// �������
		JPanel panel0_1 = new JPanel();
		JPanel panel0_2 = new JPanel();
		JPanel panel0_3 = new JPanel();
		panel0_1.add(name);
		panel0_1.add(age);
		panel0_1.add(color);
		comboBox.addItem("����è");
		comboBox.addItem("���ﹷ");
		panel0_2.add(comboBox);
		panel0_2.add(insert);
		panel0_3.add(download);
		panel0_3.add(upload);

		Box top = Box.createVerticalBox();
		top.add(panel0_1);
		top.add(panel0_2);
		managerPanel.add(top);
		dataPanel.add(panel0_3);
		Box top2 = Box.createVerticalBox();
		top2.add(managerPanel);
		top2.add(dataPanel);
		panel0.add(top2);
		tabs.addTab("�������", panel0);

		// ��ѯ����
		JPanel panel2_1 = new JPanel();
		JPanel panel2_2 = new JPanel();
		JPanel panel2_3 = new JPanel();
		JPanel panel2_4 = new JPanel();
		JPanel panel2_5 = new JPanel();
		panel2_1.add(lname1);
		panel2_1.add(queryname);
		panel2_1.add(query);
		panel2_2.add(lname2);
		panel2_2.add(deletename);
		panel2_2.add(delet);
		panel2_3.add(lname3);
		panel2_3.add(updataname);
		comboBoxType.addItem("����è");
		comboBoxType.addItem("���ﹷ");
		panel2_3.add(comboBoxType);
		panel2_3.add(updata);
		panel2_4.add(rename);
		panel2_4.add(reage);
		panel2_4.add(recolor);
		queryPanel.add(panel2_1);
		queryPanel.add(panel2_2);
		queryPanel.add(panel2_3);
		queryPanel.add(panel2_4);
		panel2_5.add(petNum);
		panel2_5.add(Drop);
		Box topLeft = Box.createVerticalBox();
		topLeft.add(queryPanel);
		topLeft.add(panel2_5);
		panel2.add(topLeft);
		tabs.addTab("��ѯ����", panel2);

		// ��ʾ����
		String[] headers = { "��������", "�������", "��������", "������ɫ" };
		Object[][] cellData = null;

		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(cellData, headers) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(485, 190));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel3.add(scroll);
		tabs.addTab("��ʾ����", panel3);

		// �汾��Ϣ
		JPanel panel4_1 = new JPanel();
		JPanel panel4_2 = new JPanel();
		JPanel panel4_3 = new JPanel();
		JPanel panel4_4 = new JPanel();
		JPanel panel4_5 = new JPanel();
		JPanel panel4_7 = new JPanel();
		JPanel panel4_8 = new JPanel();
		JPanel panel4_9 = new JPanel();
		Box topInformation = Box.createVerticalBox();
		topInformation.add(panel4_1.add(new JLabel("�����̵������V1.0")));
		topInformation.add(panel4_2.add(new JLabel("��Ҫ���ܣ�")));
		topInformation.add(panel4_3.add(new JLabel("������Ϣ������ɾ���ġ��飡")));
		topInformation.add(panel4_5.add(new JLabel("������Ϣ��̬�洢��������ͬ����")));
		topInformation.add(panel4_7.add(new JLabel("2010�졪����15�ࡪ53101512")));
		topInformation.add(panel4_4.add(new JLabel("��������ǿ��ѧ�ţ�53101512")));
		topInformation.add(panel4_8.add(new JLabel("2012��4��25��")));
		topInformation.add(panel4_9.add(new JLabel("QQ��6475005")));
		versionPanel.add(topInformation);
		panel4.add(versionPanel);
		tabs.add("�汾��Ϣ", panel4);
		// tabs ҳǩѡ�������
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabs.getSelectedIndex() == 0) {
					txt.setText("��¼����");
				} else {
					if (tabs.getSelectedIndex() == 1) {
						txt.setText("�������");
						if (txtSource.equals("error")) {
							JOptionPane.showMessageDialog(null, "�û�δ��¼��", "����",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						if (tabs.getSelectedIndex() == 2) {
							txt.setText("��ѯ����");
							if (txtSource.equals("error")) {
								JOptionPane.showMessageDialog(null, "�û�δ��¼��",
										"����", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							if (tabs.getSelectedIndex() == 3) {
								txt.setText("��ʾ����");
								if (txtSource.equals("error")) {
									JOptionPane.showMessageDialog(null,
											"�û�δ��¼��", "����",
											JOptionPane.ERROR_MESSAGE);
								} else {
									PetShop petshop = new PetShop();
									fillTable(petshop.showAllInTable(txtSource));
								}
							} else {
								txt.setText("��Ϣ����");
							}
						}
					}
				}
			}
		});
		Container contentPane = jf.getContentPane();
		contentPane.add(BorderLayout.SOUTH, txt);

		contentPane.add(tabs);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		jf.setSize(540, 310);
		Running running = new Running();
		running.start();
	}

	public static void main(String[] args) {
		// JFrame.setDefaultLookAndFeelDecorated(true);
		File f = new File("users_pet");
		if (!f.exists()) {
			f.mkdirs();
		}
		new pet_GUI().init();
	}

	@SuppressWarnings("serial")
	class ButtonPanel extends JPanel {
		private ButtonGroup group;

		public ButtonPanel(String title, String[] options) {
			setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(), title));
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			group = new ButtonGroup();
			for (int i = 0; options != null && i < options.length; i++) {
				JRadioButton b = new JRadioButton(options[i]);
				b.setActionCommand(options[i]);
				add(b);
				group.add(b);
				b.setSelected(i == 0);
			}
		}

		// ����һ�����������ڷ����û�ѡ���ѡ��
		public String getSelection() {
			return group.getSelection().getActionCommand();
		}
	}

	private void write(File file, String str) {
		try {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(file), "GBK"));
			String[] writestring = str.split(";");
			for (int i = 0; i < writestring.length; i++) {
				writer.println(writestring[i]);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String read(File file) {
		String result = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "GBK"));
			String data = null;
			StringBuffer buffer = new StringBuffer();
			while ((data = reader.readLine()) != null) {
				buffer.append(data + ";");
			}
			reader.close();
			result = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public class Running extends Thread {
		private int index = 4;
		private boolean stopFlag = true;

		@Override
		public void run() {
			while (stopFlag) {
				try {
					pet_GUI.this.setBack(index);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (++index > 4) {
					index = 1;
				}
			}
			index = 4;
		}
	}

	public void setBack(int index) {
		// pet_1.jpg���ͼƬ��λ��Ҫ����ǰ�������ͬһ������
		URL url = pet_GUI.class.getResource("pet_" + index + ".jpg");
		ImageIcon img = new ImageIcon(url);
		JLabel background = new JLabel(img);
		logPanel.removeAll();
		logPanel.add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, 311, 211);
	}

	public String getIP() {
		String result = "null";
		File f = new File("server.xml");
		if (f.exists()) {
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(f), "GBK"));
				if ((IP = reader.readLine()) != null) {
					result = IP;
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public void setIP(String IPaddress) {
		File f = new File("server.xml");
		try {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(f), "GBK"));
			writer.println(IPaddress);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillTable(ArrayList<Pet> PetList) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);// ���ԭ����

		// �������
		for (Pet pet : PetList) {
			String[] arr = new String[4];
			arr[0] = pet.getName();
			arr[1] = pet.getType();
			arr[2] = pet.getAge().toString();
			arr[3] = pet.getColor();

			// ������ݵ����
			tableModel.addRow(arr);
		}

		// ���±��
		table.invalidate();
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
