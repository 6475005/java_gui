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
	JFrame jf = new JFrame("����������V1.0");
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField();
	private JButton StartServer = new JButton("����������");
	private JButton StopServer = new JButton("�رշ�����");
	private ButtonPanel managerPanel = new ButtonPanel("����Ŀ�����ر�",
			new String[] {});
	private ButtonPanel informationPanel = new ButtonPanel("����İ汾��Ϣ",
			new String[] {});
	private JPanel manager = new JPanel();// �������
	private JPanel information = new JPanel();// �汾���

	public void init() {
		StartServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				if(state){
					startserver.start();
					state = false;
					JOptionPane.showMessageDialog(null, "�����ѿ�����", "��������",
							JOptionPane.DEFAULT_OPTION);
				}else{
					startserver.resume();
					JOptionPane.showMessageDialog(null, "�����ѿ�����", "��������",
							JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		StopServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "�����ѹرգ�", "����ر�",
						JOptionPane.DEFAULT_OPTION);
				startserver.suspend();
				state = false;
				/*StopServer stopserver = new StopServer();	
				stopserver.start();*/
			}
		});
		// �������
		JPanel panel_1 = new JPanel();
		panel_1.add(StartServer);
		JPanel panel_2 = new JPanel();
		panel_2.add(StopServer);
		Box managertop = Box.createVerticalBox();
		managertop.add(panel_1);
		managertop.add(panel_2);
		managerPanel.add(managertop);
		manager.add(managerPanel);
		tabs.add("�������", manager);
		// �汾��Ϣ
		JPanel panel4_1 = new JPanel();
		JPanel panel4_2 = new JPanel();
		JPanel panel4_3 = new JPanel();
		JPanel panel4_4 = new JPanel();
		JPanel panel4_5 = new JPanel();
		JPanel panel4_7 = new JPanel();
		JPanel panel4_9 = new JPanel();
		Box topInformation = Box.createVerticalBox();
		topInformation.add(panel4_1.add(new JLabel("���������������V1.0")));
		topInformation.add(panel4_2.add(new JLabel("��Ҫ���ܣ�")));
		topInformation.add(panel4_3.add(new JLabel("������Ϣ������ɾ���ġ��飡")));
		topInformation.add(panel4_5.add(new JLabel("������Ϣ��̬�洢��������ͬ����")));
		topInformation.add(panel4_4.add(new JLabel("2010�졪����15��")));
		topInformation.add(panel4_7.add(new JLabel("��������ǿ-ѧ�ţ�53101512")));
		topInformation.add(panel4_9.add(new JLabel("2012��4��25�� QQ��6475005")));
		informationPanel.add(topInformation);
		information.add(informationPanel);
		tabs.add("�汾��Ϣ", information);

		// tabs ҳǩѡ�������
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabs.getSelectedIndex() == 0) {
					txt.setText("�������");
				} else {
					txt.setText("�汾��Ϣ");
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

		// ����һ�����������ڷ����û�ѡ���ѡ��
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
