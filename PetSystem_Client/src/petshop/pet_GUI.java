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
	JFrame jf = new JFrame("宠物店网络版V1.0");
	private ButtonPanel logPanel = new ButtonPanel("", new String[] {});
	private ButtonPanel loginPanel = new ButtonPanel("用户登录", new String[] {});
	private ButtonPanel welcomePanel = new ButtonPanel("登录成功", new String[] {});
	private ButtonPanel managerPanel = new ButtonPanel("宠物管理", new String[] {});
	private ButtonPanel dataPanel = new ButtonPanel("数据同步与备份", new String[] {});
	private ButtonPanel queryPanel = new ButtonPanel(null, new String[] {});
	private ButtonPanel versionPanel = new ButtonPanel("版本信息", new String[] {});
	private JPanel panel = new JPanel();// 登录面板
	private JPanel panel_ = new JPanel();// 欢迎面板
	private JPanel panel0 = new JPanel();// 管理面板
	private JPanel panel2 = new JPanel();// 查询面板
	private JPanel panel3 = new JPanel();// 显示面板
	private JPanel panel4 = new JPanel();// 版本面板
	private String txtSource = "error";
	private String IP = "127.0.0.1";
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField();
	private JTextField username = new JTextField("账号", 10);
	private JTextField newpassword = new JTextField("", 10);
	private JPasswordField password = new JPasswordField(10);
	private JTextField name = new JTextField("宠物名字", 10);
	private JTextField age = new JTextField("宠物年龄", 10);
	private JTextField color = new JTextField("宠物颜色", 10);
	private JTextField rename = new JTextField("宠物名字", 10);
	private JTextField reage = new JTextField("宠物年龄", 10);
	private JTextField recolor = new JTextField("宠物颜色", 10);
	private JTextField queryname = new JTextField("宠物名字", 10);
	private JTextField updataname = new JTextField("宠物名字", 10);
	private JTextField deletename = new JTextField("宠物名字", 10);
	private JTextField IPaddress = new JTextField("服务器ip", 10);
	private JLabel lname1 = new JLabel("宠物名字:");
	private JLabel lname2 = new JLabel("宠物名字:");
	private JLabel lname3 = new JLabel("宠物名字:");
	private JLabel username1 = new JLabel("用户昵称:");
	private JLabel password1 = new JLabel("登录密码:");
	private JComboBox comboBox = new JComboBox();
	private JComboBox comboBoxType = new JComboBox();
	private JButton insert = new JButton("添加宠物");
	private JButton query = new JButton("查询宠物");
	private JButton delet = new JButton("删除宠物");
	private JButton updata = new JButton("修改宠物");
	private JButton petNum = new JButton("查询宠物总数");
	private JButton Drop = new JButton("清空宠物商店");
	private JButton login = new JButton("登录宠物商店");
	private JButton regist = new JButton("注册宠物商店");
	private JButton relogin = new JButton("切换账号登录");
	private JButton getDate = new JButton("查询当前时间");
	private JButton download = new JButton("与服务器同步数据");
	private JButton upload = new JButton("备份数据到服务器");
	private JButton repassword = new JButton("用户密码修改");
	private JButton deleteuser = new JButton("用户信息注销");
	private JTable table = new JTable();
	private JTextField filenameTF = new JTextField(), dirTF = new JTextField();

	public void init() {
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!getIP().equals("null")) {
					Client login = new Client();
					if (username.getText().equals("账号")) {
						JOptionPane.showMessageDialog(null, "请输入账号！", "错误",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (password.getText().equals("密码")) {
							JOptionPane.showMessageDialog(null, "请输入密码！", "错误",
									JOptionPane.ERROR_MESSAGE);
						} else {

							String loginresult = login.setClient(
									username.getText(), "login", IP);
							if (loginresult.equals("serverStop")) {
								JOptionPane.showMessageDialog(null, "服务器未开启！",
										"错误", JOptionPane.ERROR_MESSAGE);
							} else {
								if (loginresult.equals(password.getText())) {
									txtSource = "users_pet/"
											+ username.getText() + "_pet.txt";
									JOptionPane.showMessageDialog(null,
											"登录成功！", "恭喜",
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
									// 欢迎界面
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
											.add(new JLabel("登录成功！")));
									welcome.add(panel_02.add(new JLabel("欢迎您，"
											+ username.getText() + "   !")));
									welcome.add(panel_03.add(new JLabel(
											"登录时间为：")));
									welcome.add(panel_04.add(new JLabel(
											"（北京时间）")));
									welcome.add(panel_05.add(new JLabel(str)));
									welcome.add(panel_06);
									welcome.add(panel_07);
									welcomePanel.add(welcome);
									panel_.add(welcomePanel);

									tabs.removeAll();
									tabs.add("欢迎界面", panel_);
									tabs.add("管理界面", panel0);
									tabs.add("查询界面", panel2);
									tabs.add("显示界面", panel3);
									tabs.add("版本信息", panel4);
								} else {
									JOptionPane.showMessageDialog(null,
											"用户名或密码错误！", "错误",
											JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				} else {
					IPaddress.setText(JOptionPane.showInputDialog(IPaddress,
							"首次使用请设置服务端IP", JOptionPane.DEFAULT_OPTION));
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
				tabs.add("登录界面", panel);
				tabs.add("管理界面", panel0);
				tabs.add("查询界面", panel2);
				tabs.add("显示界面", panel3);
				tabs.add("版本信息", panel4);
			}
		});
		repassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newpassword.setText(null);
				newpassword.setText(JOptionPane.showInputDialog(newpassword,
						"请输入新密码:", JOptionPane.DEFAULT_OPTION));
				Client repassword = new Client();
				if (repassword.setClient(
						username.getText() + "/" + newpassword.getText(),
						"repassword", IP).equals("true")) {
					JOptionPane.showMessageDialog(null, "密码修改成功！", "恭喜",
							JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "密码修改失败！", "错误",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteuser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null,
						"注销后不可恢复,您确定要注销账号?", "账号注销", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					Client deleteuser = new Client();
					if (deleteuser.setClient(username.getText(), "delete", IP)
							.equals("true")) {
						JOptionPane.showMessageDialog(null, "注销成功！", "恭喜",
								JOptionPane.DEFAULT_OPTION);
						File f = new File("users_pet/" + username.getText()
								+ "_pet.txt");
						f.delete();
						txtSource = "error";
						tabs.removeAll();
						panel_.removeAll();
						welcomePanel.removeAll();
						tabs.add("登录界面", panel);
						tabs.add("管理界面", panel0);
						tabs.add("查询界面", panel2);
						tabs.add("显示界面", panel3);
						tabs.add("版本信息", panel4);
					} else {
						JOptionPane.showMessageDialog(null, "注销失败！", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				username.setText("账号");
				password.setText("");
			}
		});
		regist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!getIP().equals("null")) {
					Client regist = new Client();
					if (username.getText().equals("账号")) {
						JOptionPane.showMessageDialog(null, "请输入账号！", "错误",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (password.getText().equals("密码")) {
							JOptionPane.showMessageDialog(null, "请输入密码！", "错误",
									JOptionPane.ERROR_MESSAGE);
						} else {
							String registresult = regist.setClient(
									username.getText() + "/"
											+ password.getText(), "regist", IP);
							if (registresult.equals("serverStop")) {
								JOptionPane.showMessageDialog(null, "服务器未开启！",
										"错误", JOptionPane.ERROR_MESSAGE);
							} else {
								if (registresult.equals(username.getText())) {
									JOptionPane.showMessageDialog(null,
											"注册成功！", "恭喜",
											JOptionPane.DEFAULT_OPTION);
								} else {
									JOptionPane.showMessageDialog(null,
											"用户名已存在！", "错误",
											JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
					username.setText("账号");
					password.setText("");
				} else {
					IPaddress.setText(JOptionPane.showInputDialog(IPaddress,
							"首次使用请设置服务端IP", JOptionPane.DEFAULT_OPTION));
					setIP(IPaddress.getText());
				}
			}
		});
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PetShop petshop = new PetShop();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (name.getText().equals("宠物名字")) {
						JOptionPane.showMessageDialog(null, "请输入宠物姓名！", "错误",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (age.getText().equals("宠物年龄")) {
							JOptionPane.showMessageDialog(null, "请输入宠物年龄！",
									"错误", JOptionPane.ERROR_MESSAGE);
						} else {
							if (color.getText().equals("宠物颜色")) {
								JOptionPane.showMessageDialog(null, "请输入宠物颜色！",
										"错误", JOptionPane.ERROR_MESSAGE);
							} else {
								if (name.getText().length() < 2
										|| name.getText().length() > 6) {
									JOptionPane.showMessageDialog(null,
											"宠物名字长度应为2-6个字符！", "错误",
											JOptionPane.ERROR_MESSAGE);
								} else {
									if (!isInteger(age.getText())
											|| Integer.valueOf(age.getText()) <= 1
											|| Integer.valueOf(age.getText()) >= 99) {
										JOptionPane.showMessageDialog(null,
												"宠物年龄应为1-99的整数！", "错误",
												JOptionPane.ERROR_MESSAGE);
									} else {
										if (color.getText().length() < 1
												|| color.getText().length() > 5) {
											JOptionPane.showMessageDialog(null,
													"宠物颜色长度应为1-5个字符！", "错误",
													JOptionPane.ERROR_MESSAGE);
										} else {
											if (comboBox.getSelectedIndex() == 1) {
												Pet pet = new Pet();
												pet.setPet(name.getText(),
														"宠物狗",
														Integer.valueOf(age
																.getText()),
														color.getText());
												petshop.add(pet, txtSource);
												txt.setText("宠物狗："
														+ pet.getName()
														+ "保存成功！");
											} else {
												Pet pet = new Pet();
												pet.setPet(name.getText(),
														"宠物猫",
														Integer.valueOf(age
																.getText()),
														color.getText());
												petshop.add(pet, txtSource);
												txt.setText("宠物猫："
														+ pet.getName()
														+ "保存成功！");
											}
										}
									}
								}
							}
						}
					}
				}
				name.setText("宠物名字");
				age.setText("宠物年龄");
				color.setText("宠物颜色");
			}
		});
		query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PetShop petshop = new PetShop();
				String qname = queryname.getText();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (qname.equals("/")) {
						JOptionPane.showMessageDialog(null, "宠物姓名输入错误！", "错误",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (qname.equals("宠物名字")) {
							JOptionPane.showMessageDialog(null, "请输入宠物姓名！",
									"错误", JOptionPane.ERROR_MESSAGE);
						} else {
							if (petshop.search(qname, txtSource) != null) {
								txt.setText(petshop.search(qname, txtSource));
							} else {
								txt.setText("未查询到此名字宠物");
							}
						}
					}
				}
				queryname.setText("宠物名字");
			}
		});
		delet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PetShop petshop = new PetShop();
				String qname = deletename.getText();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (qname.equals("/")) {
						JOptionPane.showMessageDialog(null, "宠物姓名输入错误！", "错误",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (deletename.getText().equals("宠物名字")) {
							JOptionPane.showMessageDialog(null, "请输入宠物姓名！",
									"错误", JOptionPane.ERROR_MESSAGE);
						} else {
							if (petshop.delete(deletename.getText(), txtSource)) {
								txt.setText("宠物删除成功！");
							} else {
								txt.setText("未查询到此名字宠物！");
							}
						}
					}
				}
				deletename.setText("宠物名字");
			}
		});
		updata.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PetShop petshop = new PetShop();
				String qname = updataname.getText();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (qname.equals("/")) {
						JOptionPane.showMessageDialog(null, "宠物姓名输入错误！", "错误",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (updataname.getText().equals("宠物名字")) {
							JOptionPane.showMessageDialog(null, "请输入宠物姓名！",
									"错误", JOptionPane.ERROR_MESSAGE);
						} else {
							if (rename.getText().equals("宠物名字")) {
								JOptionPane.showMessageDialog(null,
										"请输入修改后的宠物姓名！", "错误",
										JOptionPane.ERROR_MESSAGE);
							} else {
								if (reage.getText().equals("宠物年龄")) {
									JOptionPane.showMessageDialog(null,
											"请输入修改后的宠物年龄！", "错误",
											JOptionPane.ERROR_MESSAGE);
								} else {
									if (recolor.getText().equals("宠物颜色")) {
										JOptionPane.showMessageDialog(null,
												"请输入修改后的宠物颜色！", "错误",
												JOptionPane.ERROR_MESSAGE);
									} else {
										if (rename.getText().length() < 2
												|| rename.getText().length() > 6) {
											JOptionPane.showMessageDialog(null,
													"宠物名字长度应为2-6个字符！", "错误",
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
																"宠物年龄应为1-99的整数！",
																"错误",
																JOptionPane.ERROR_MESSAGE);
											} else {
												if (recolor.getText().length() < 1
														|| recolor.getText()
																.length() > 5) {
													JOptionPane
															.showMessageDialog(
																	null,
																	"宠物颜色长度应为1-5个字符！",
																	"错误",
																	JOptionPane.ERROR_MESSAGE);
												} else {
													if (comboBoxType
															.getSelectedIndex() == 0) {
														if (petshop.updata(
																updataname
																		.getText(),
																rename.getText(),
																"宠物猫",
																reage.getText(),
																recolor.getText(),
																txtSource)) {
															txt.setText("宠物修改成功！");
														} else {
															txt.setText("未查询到此名字宠物！");
														}
													} else {
														if (petshop.updata(
																updataname
																		.getText(),
																rename.getText(),
																"宠物狗",
																reage.getText(),
																recolor.getText(),
																txtSource)) {
															txt.setText("宠物修改成功！");
														} else {
															txt.setText("未查询到此名字宠物！");
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
				updataname.setText("宠物名字");
				rename.setText("宠物名字");
				reage.setText("宠物年龄");
				recolor.setText("宠物颜色");
			}
		});
		petNum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PetShop petshop = new PetShop();
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String str = "当前宠物店中有宠物：" + petshop.getPetNUM(txtSource)
							+ "只！";
					JOptionPane.showMessageDialog(null, str, "宠物数量",
							JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		Drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int response = JOptionPane.showConfirmDialog(null,
							"清空后不可恢复,您确定要清空宠物商店?", "清空宠物商店",
							JOptionPane.YES_NO_OPTION);
					if (response == 0) {
						PetShop petshop = new PetShop();
						petshop.clean(txtSource);
						txt.setText("宠物商店已经清空！");
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
				JOptionPane.showMessageDialog(null, "当前北京时间：" + str, "当前时间",
						JOptionPane.DEFAULT_OPTION);
			}
		});
		download.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
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
						JOptionPane.showMessageDialog(null, "数据同步成功！", "恭喜",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "数据同步失败！", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		upload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtSource.equals("error")) {
					JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
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
						JOptionPane.showMessageDialog(null, "数据备份成功！", "恭喜",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "数据备份失败！", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		name.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (name.getText().equals("")) {
					name.setText("宠物名字");
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (name.getText().equals("宠物名字")) {
					name.setText("");
				}
			}
		});
		deletename.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (deletename.getText().equals("")) {
					deletename.setText("宠物名字");
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (deletename.getText().equals("宠物名字")) {
					deletename.setText("");
				}
			}
		});
		updataname.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (updataname.getText().equals("")) {
					updataname.setText("宠物名字");
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (updataname.getText().equals("宠物名字")) {
					updataname.setText("");
				}
			}
		});
		age.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (age.getText().equals("")) {
					age.setText("宠物年龄");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (age.getText().equals("宠物年龄")) {
					age.setText("");
				}
			}
		});
		color.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (color.getText().equals("")) {
					color.setText("宠物颜色");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (color.getText().equals("宠物颜色")) {
					color.setText("");
				}
			}
		});
		queryname.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (queryname.getText().equals("")) {
					queryname.setText("宠物名字");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (queryname.getText().equals("宠物名字")) {
					queryname.setText("");
				}
			}
		});
		rename.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (rename.getText().equals("")) {
					rename.setText("宠物名字");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (rename.getText().equals("宠物名字")) {
					rename.setText("");
				}
			}
		});
		reage.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (reage.getText().equals("")) {
					reage.setText("宠物年龄");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (reage.getText().equals("宠物年龄")) {
					reage.setText("");
				}
			}
		});
		recolor.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (recolor.getText().equals("")) {
					recolor.setText("宠物颜色");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (recolor.getText().equals("宠物颜色")) {
					recolor.setText("");
				}
			}
		});
		username.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (username.getText().equals("")) {
					username.setText("账号");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (username.getText().equals("账号")) {
					username.setText("");
				}
			}
		});
		password.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (password.equals("")) {
					password.setText("密码");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (password.equals("密码")) {
					password.setText("");
				}
			}
		});
		// 登录界面
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
		tabs.addTab("登录界面", panel);
		// 管理界面
		JPanel panel0_1 = new JPanel();
		JPanel panel0_2 = new JPanel();
		JPanel panel0_3 = new JPanel();
		panel0_1.add(name);
		panel0_1.add(age);
		panel0_1.add(color);
		comboBox.addItem("宠物猫");
		comboBox.addItem("宠物狗");
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
		tabs.addTab("管理界面", panel0);

		// 查询界面
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
		comboBoxType.addItem("宠物猫");
		comboBoxType.addItem("宠物狗");
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
		tabs.addTab("查询界面", panel2);

		// 显示界面
		String[] headers = { "宠物名字", "宠物类别", "宠物年龄", "宠物颜色" };
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
		tabs.addTab("显示界面", panel3);

		// 版本信息
		JPanel panel4_1 = new JPanel();
		JPanel panel4_2 = new JPanel();
		JPanel panel4_3 = new JPanel();
		JPanel panel4_4 = new JPanel();
		JPanel panel4_5 = new JPanel();
		JPanel panel4_7 = new JPanel();
		JPanel panel4_8 = new JPanel();
		JPanel panel4_9 = new JPanel();
		Box topInformation = Box.createVerticalBox();
		topInformation.add(panel4_1.add(new JLabel("宠物商店网络版V1.0")));
		topInformation.add(panel4_2.add(new JLabel("主要功能：")));
		topInformation.add(panel4_3.add(new JLabel("宠物信息的增、删、改、查！")));
		topInformation.add(panel4_5.add(new JLabel("宠物信息动态存储，服务器同步！")));
		topInformation.add(panel4_7.add(new JLabel("2010届—网络15班—53101512")));
		topInformation.add(panel4_4.add(new JLabel("姓名：仲强—学号：53101512")));
		topInformation.add(panel4_8.add(new JLabel("2012年4月25日")));
		topInformation.add(panel4_9.add(new JLabel("QQ：6475005")));
		versionPanel.add(topInformation);
		panel4.add(versionPanel);
		tabs.add("版本信息", panel4);
		// tabs 页签选择监听器
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabs.getSelectedIndex() == 0) {
					txt.setText("登录界面");
				} else {
					if (tabs.getSelectedIndex() == 1) {
						txt.setText("管理界面");
						if (txtSource.equals("error")) {
							JOptionPane.showMessageDialog(null, "用户未登录！", "错误",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						if (tabs.getSelectedIndex() == 2) {
							txt.setText("查询界面");
							if (txtSource.equals("error")) {
								JOptionPane.showMessageDialog(null, "用户未登录！",
										"错误", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							if (tabs.getSelectedIndex() == 3) {
								txt.setText("显示界面");
								if (txtSource.equals("error")) {
									JOptionPane.showMessageDialog(null,
											"用户未登录！", "错误",
											JOptionPane.ERROR_MESSAGE);
								} else {
									PetShop petshop = new PetShop();
									fillTable(petshop.showAllInTable(txtSource));
								}
							} else {
								txt.setText("信息界面");
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

		// 定义一个方法，用于返回用户选择的选项
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
		// pet_1.jpg这个图片的位置要跟当前这个类是同一个包下
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
		tableModel.setRowCount(0);// 清除原有行

		// 填充数据
		for (Pet pet : PetList) {
			String[] arr = new String[4];
			arr[0] = pet.getName();
			arr[1] = pet.getType();
			arr[2] = pet.getAge().toString();
			arr[3] = pet.getColor();

			// 添加数据到表格
			tableModel.addRow(arr);
		}

		// 更新表格
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
