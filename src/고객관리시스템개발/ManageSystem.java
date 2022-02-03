package �������ý��۰���;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * <<���� �̷�(Modification Information)>>
 * 
 * 	������	�ۼ���		�۾�����
 * 	----	----		-------
 * 	������	2022.02.03	��Ʈ�� ȭ�� ���� �� �޴�, West���� �Է�, �Ż����� ����
 * 	...		...			.....
 */

// ������(GUI) ���α׷� ���� �� ���� JFrame Ŭ�����κ��� ����� ����!!
// JFrame�� �ֻ��� �����̳��̴�.
public class ManageSystem extends JFrame {
	public static final String String = null;
	
	// ���� Ŭ���� ��ü ����
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons buttons = new Buttons();
	ShowTable showTable = new ShowTable();
	
	// ������
	public ManageSystem() {
		OUTTER: while (true) { // ���� Loop, OUTTER(�빮�ڷ�)�� ���̺��̶�� �Ѵ�.
			ImageIcon icon = new ImageIcon("images/����������_�̹���.jpg");
			JOptionPane.showMessageDialog(null, null, "���������� �ý���", JOptionPane.NO_OPTION, icon);

			// �н����� ����â ����� ����
			String password = JOptionPane.showInputDialog("������ �ý���" + "\n" + "�н����� �Է�");
			String passwd = "test1234"; // ���� �н�����

			if (password == null) {
				break OUTTER;
			} else if (password.equals(passwd)) { // ���� Ȯ��!! => ���� â�� �����
				setTitle("���������� �ý���");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// ���� Ŭ���� ��ü ���̱�
				add(menuMain.bar, BorderLayout.NORTH);
				add(west, BorderLayout.WEST);
				
				setSize(1200, 700);
				setLocation(500, 50);
				setVisible(true);
				
				break OUTTER;
			} else {
				JOptionPane.showMessageDialog(null, "�н����尡 ���� �ʽ��ϴ�." + "\n" + "'Ȯ��' ��ư�� ��������.", "�н����� ���� ����",
						JOptionPane.ERROR_MESSAGE);
				continue OUTTER;
			}
		}
	}
	
//	MenuMain Ŭ����
	// �޴� ����� '���� Ŭ����' ����
	// JPanel�� ���� �����̳��̴�.
	class MenuMain extends JPanel implements ActionListener, ItemListener {
		// ��������(������������)���� ������ ������ ����
		JMenuBar bar;
		JMenu file, sort, help;
		JMenuItem fopen, fsave, fexit, proinfo;
		JCheckBoxMenuItem sno, sname, schul, sjob;
		
		FileDialog saveOpen, readOpen;
		String fileDir, fileName, savefileName, readfileName;
		ButtonGroup gr = new ButtonGroup();
		
		// ������
		public MenuMain() {
			bar = new JMenuBar();
			
			file = new JMenu("����");
			sort = new JMenu("����");
			help = new JMenu("����");
			
			fopen = new JMenuItem("����");
			fsave = new JMenuItem("����");
			fexit = new JMenuItem("�ݱ�");
			
			sno = new JCheckBoxMenuItem("��ȣ");
			sname = new JCheckBoxMenuItem("�̸�");
			schul = new JCheckBoxMenuItem("�������");
			sjob = new JCheckBoxMenuItem("����");
			
			proinfo = new JMenuItem("���α׷� ����");
			
			// ��ü ���̱�
			file.add(fopen); file.add(fsave); file.addSeparator(); file.add(fexit);
			gr.add(sno); gr.add(sname); gr.add(schul); gr.add(sjob);
			sort.add(sno); sort.add(sname); sort.add(schul); sort.add(sjob);
			help.add(proinfo);
			
			bar.add(file); bar.add(sort); bar.add(help);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
		}
	}
//	end MenuMain Ŭ����
	
//	West Ŭ����
	// �����͸� �Է¹޴� West�� ���� Ŭ���� ����
	class West extends JPanel {
		// ���� Ŭ���� ��ü ����
		Input input = new Input();
		Output output = new Output();
		
		// ������
		public West() {
			setLayout(new BorderLayout());
			add(input, BorderLayout.CENTER);
			add(output, BorderLayout.SOUTH);
		}
		// ������ �Է��� �޴� Input ���� Ŭ���� ����
		class Input extends JPanel {
			
			JTextField[] tf = new JTextField[5];
			String[] text = {"��ȣ","�̸�","�ڵ�����ȣ","�̸���","�ֹε�Ϲ�ȣ"};
			String[] jobText = {"����","ȸ���","������","���α׷���","�ǻ�","��ȣ��","�л�","��Ÿ"};
			JLabel la, job;
			JComboBox box;
			
			// ������
			public Input() {
				setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "�Է�")); // ���⼭ 2�� ���� �β�
				setLayout(new GridLayout(6, 2, 5, 30));

				for (int i = 0; i < text.length; i++) {
					la = new JLabel(text[i]);
					tf[i] = new JTextField(10);
					la.setHorizontalAlignment(JLabel.CENTER);
					add(la);
					add(tf[i]);
				}
				box = new JComboBox(jobText);
				job = new JLabel("����");
				job.setHorizontalAlignment(JLabel.CENTER);
				add(job);
				add(box);
				setPreferredSize(new Dimension(340, 300));
			}
		}
		// �Է¹��� �ֹι�ȣ�κ��� ���� ����(����, ����, �������, ����)�� ������ ���� Ŭ���� ����
		class Output extends JPanel implements ActionListener {
			
			JPanel info = new JPanel();		// '�Ż�����' ���� ����� JPanel
			JPanel search = new JPanel();	// '�����˻�' ���� ����� JPanel
			
			CardLayout card = new CardLayout();
			String[] text = {"  ����:","  ����:","  �������:","  ����:"};
			JLabel[] label = new JLabel[4];
			ButtonGroup group = new ButtonGroup();
			JRadioButton[] searchRadio = new JRadioButton[4];
			JButton searchButton;
			JButton exitButton;
			JTextField nameText;
			String[] search_name = {"�̸�","����","�������","����"};
			int searchType;
			
			// ������
			public Output() {
				// '�Ż�����' ���� �����
				info.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "�Ż�����"));
				info.setLayout(new GridLayout(4, 1));
				
				for (int i = 0; i < text.length; i++) {
					label[i] = new JLabel(text[i], JLabel.LEFT);
					info.add(label[i]);
				}
				
				// '�����˻�' ���� �����
				search.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "�����˻�"));
				nameText = new JTextField(10);
				searchButton = new JButton("ã��");
				exitButton = new JButton("������");
				
				searchButton.setBackground(Color.CYAN);
				exitButton.setBackground(Color.LIGHT_GRAY);
				
				setPreferredSize(new Dimension(340, 300));
				
				int x = -70;
				search.setLayout(null);  // ����� ���� ��ġ ���(������ ���)
				
				for (int i = 0; i < searchRadio.length; i++) {
					searchRadio[i] = new JRadioButton(search_name[i]);
					searchRadio[i].setBounds(x += 80, 60, 80, 30);
					
					group.add(searchRadio[i]);
					search.add(searchRadio[i]);
					// �̺�Ʈ ����
					searchRadio[i].addActionListener(this);
				}
				nameText.setBounds(40, 110, 200, 30);  // �Է�
				searchButton.setBounds(35, 170, 80, 30);  // ã��
				exitButton.setBounds(140, 170, 110, 30);  // ������
				search.add(nameText);
				search.add(searchButton);
				search.add(exitButton);
				
				card = new CardLayout();
				setLayout(card);
				
				add(info,"�Ż����� ī��");
				add(search,"�����˻� ī��");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
	}
//	end West Ŭ����
	
//	Buttons Ŭ����
	// ��ư ���۳�Ʈ�� ����� ���� Ŭ����
	class Buttons extends JPanel implements ActionListener {

		// ������
		public Buttons() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
//	end Buttons Ŭ����
	
//	ShowTable Ŭ����
	// JTable�� �Է��� �����͸� �����ִ� ���� Ŭ����
	class ShowTable extends MouseAdapter {
		
		// ������
		public ShowTable() {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
		}
	}
//	end ShowTable Ŭ����
	
	public static void main(String[] args) {
		// ��ü ����(main������ ��ü ������)
		ManageSystem manageSystem = new ManageSystem();
	}
}
