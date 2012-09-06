package server;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Server_GUI {
	private boolean state = true;
	ServerMain servermain = new ServerMain();
	StartServer startserver = new StartServer();
	JFrame jf = new JFrame("宠物店服务器V1.0");
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField();
	private JButton StartServer = new JButton("启动服务器");
	private JButton StopServer = new JButton("关闭服务器");
	private ButtonPanel managerPanel = new ButtonPanel("服务的开启与关闭",
			new String[] {});
	private ButtonPanel informationPanel = new ButtonPanel("服务的版本信息",
			new String[] {});
	private JPanel manager = new JPanel();// 管理面板
	private JPanel information = new JPanel();// 版本面板

	public void init() {
		StartServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				if(state){
					startserver.start();
					state = false;
					JOptionPane.showMessageDialog(null, "服务已开启！", "服务启动",
							JOptionPane.DEFAULT_OPTION);
				}else{
					startserver.resume();
					JOptionPane.showMessageDialog(null, "服务已开启！", "服务启动",
							JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		StopServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "服务已关闭！", "服务关闭",
						JOptionPane.DEFAULT_OPTION);
				startserver.suspend();
				state = false;
				/*StopServer stopserver = new StopServer();	
				stopserver.start();*/
			}
		});
		// 管理界面
		JPanel panel_1 = new JPanel();
		panel_1.add(StartServer);
		JPanel panel_2 = new JPanel();
		panel_2.add(StopServer);
		Box managertop = Box.createVerticalBox();
		managertop.add(panel_1);
		managertop.add(panel_2);
		managerPanel.add(managertop);
		manager.add(managerPanel);
		tabs.add("管理界面", manager);
		// 版本信息
		JPanel panel4_1 = new JPanel();
		JPanel panel4_2 = new JPanel();
		JPanel panel4_3 = new JPanel();
		JPanel panel4_4 = new JPanel();
		JPanel panel4_5 = new JPanel();
		JPanel panel4_7 = new JPanel();
		JPanel panel4_9 = new JPanel();
		Box topInformation = Box.createVerticalBox();
		topInformation.add(panel4_1.add(new JLabel("宠物网络服务器端V1.0")));
		topInformation.add(panel4_2.add(new JLabel("主要功能：")));
		topInformation.add(panel4_3.add(new JLabel("宠物信息的增、删、改、查！")));
		topInformation.add(panel4_5.add(new JLabel("宠物信息动态存储，服务器同步！")));
		topInformation.add(panel4_4.add(new JLabel("2010届—网络15班")));
		topInformation.add(panel4_7.add(new JLabel("姓名：仲强-学号：53101512")));
		topInformation.add(panel4_9.add(new JLabel("2012年4月25日 QQ：6475005")));
		informationPanel.add(topInformation);
		information.add(informationPanel);
		tabs.add("版本信息", information);

		// tabs 页签选择监听器
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabs.getSelectedIndex() == 0) {
					txt.setText("管理界面");
				} else {
					txt.setText("版本信息");
				}
			}
		});

		Container contentPane = jf.getContentPane();
		contentPane.add(BorderLayout.SOUTH, txt);
		contentPane.add(tabs);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
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

	public static void main(String[] args) {
		File f = new File("users_pet");
		if (!f.exists()) {
			f.mkdirs();
		}
		new Server_GUI().init();
	}

	public class StartServer extends Thread {

		@Override
		public void run() {
			servermain.StartServer();
		}
	}
	public class StopServer extends Thread {

		@Override
		public void run() {
			try {
				servermain.StopServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
