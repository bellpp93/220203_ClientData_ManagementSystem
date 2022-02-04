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
import java.util.Locale;
import java.util.Calendar;
import java.util.Vector;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
				add(buttons, BorderLayout.SOUTH);
				add(showTable.scrollPane, BorderLayout.CENTER);
				
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
		
		// ��ü �������� ����
		Vector<String> vector;
		JButton addBtn, preBtn, nextBtn, updateBtn, delBtn, searchBtn;
		
		int age;
		String juminNum, sung, chul;
		
		// ������
		public Buttons() {
			setLayout(new GridLayout(1, 6));
			
			addBtn = new JButton("�߰�");
			delBtn = new JButton("����");
			preBtn = new JButton("����");
			nextBtn = new JButton("����");
			updateBtn = new JButton("����");
			searchBtn = new JButton("�˻�");
			
			addBtn.setBackground(Color.YELLOW);
			delBtn.setBackground(Color.LIGHT_GRAY);
			updateBtn.setBackground(Color.PINK);
			searchBtn.setBackground(Color.GREEN);
			
			// ��ư ��ü�� JPanel�� ���̱�
			add(addBtn);
			add(delBtn);
			add(preBtn);
			add(nextBtn);
			add(updateBtn);
			add(searchBtn);
			
			// ��ư���� �̺�Ʈ ����
			addBtn.addActionListener(this);  // ���⼭ add�� ������ ����
			delBtn.addActionListener(this);
			preBtn.addActionListener(this);
			nextBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			searchBtn.addActionListener(this);
		}

		@Override  // �ڽ� Ŭ������ �°� �������ض�!!
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("�߰�")) addData();  // addData(); => �����(������) ���� �޼ҵ� ȣ��
		}
		public void addData() {
			nextBtn.setEnabled(true);  //'����' ��ư Ȱ��ȭ
			vector = new Vector<String>();  // �����迭
			vector.add(west.input.tf[0].getText());  // ��ȣ  / �Ҿƹ���.�ƹ���.�ڽ�(����)
			vector.add(west.input.tf[1].getText());  // �̸�
			vector.add(west.input.tf[2].getText());  // �ڵ�����ȣ
			vector.add(west.input.tf[3].getText());  // �̸���
			
			/*
			 * [�߿�] ����ڰ� �Է��� �ֹι�ȣ�� �ٷ� Vector ��ü�� �������� �ʰ�
			 * 		���� �ֹι�ȣ�� �������� �ƴ��� üũ�� �Ŀ� ������ ���� Vector ��ü�� �߰�����!! 
			 */
			juminNum = west.input.tf[4].getText();
			
			// juminNum�� ����Ű�� �ֹι�ȣ�� üũ ���� �����Ͽ� üũ����!!
			int[] weight = {2,3,4,5,6,7,0,8,9,2,3,4,5};  // ����ġ �迭 �غ�
			int sum = 0;
			int temp, result;
			
			// '�ֹι�ȣ' ���� ǥ����(regex)
			String regex = "[0-9]{6}-[1234][0-9]{6}";
			boolean check = juminNum.matches(regex);
			
			if(check == false) {
				JOptionPane.showMessageDialog(null, 
						"�ֹι�ȣ�� ���Ŀ� ���� ����"+"\n"+"�ֹι�ȣ�� �ٽ� �Է��ϼ���.", 
						"�����޽���", JOptionPane.ERROR_MESSAGE);  // �������� �޼ҵ忡�� ���ڰ� 4��¥��(�������� ���� X�� ����)
				
				// ����ڸ� ���� ���
				west.input.tf[4].setText(null);
				west.input.tf[4].requestFocus();
				return;  // �� ���� �״�� �־��!!
			} else {  // ���Ŀ� ������
				// �ֹι�ȣ ���� ����!!
				// [1�ܰ� ����]
				for (int i = 0; i < juminNum.length()-1; i++) {
					if(juminNum.charAt(i) == '-') continue;
						sum += (juminNum.charAt(i)-48) * weight[i];
				}
				// [2�ܰ�, 3�ܰ� ���� ����]
				temp = 11 - (sum % 11);
				result = temp % 10;
				
				if (result == juminNum.charAt(13)-48) {
					JOptionPane.showMessageDialog(null, 
							"�ֹι�ȣ üũ ��� �����Դϴ�." + "\n" + "'Ȯ��' ��ư�� ������ ������ ��µ�!!");  // �������� �޼ҵ忡�� ���ڰ� 2��¥��
					vector.add(juminNum);  // �ֹι�ȣ�� �����̱� ������ Vector ��ü�� �߰���Ų��.
					
					// ������ �ֹι�ȣ�κ��� ���� �����ϱ�
					// "����" ����
					Calendar cal = Calendar.getInstance(Locale.KOREA);
					int year = cal.get(Calendar.YEAR);  // 2022
					
					// �ֹι�ȣ �� �� �ڸ� ������
					int yy = Integer.parseInt(juminNum.substring(0, 2));
					if (juminNum.charAt(7)-48 < 3) {
						yy = yy + 1900;
					} else {
						yy = yy + 2000;
					}
					age = year - yy + 1;  // ������
					
					// "����" �����ϱ�
					if ((juminNum.charAt(7)-48) % 2 == 0) {
						sung = "����";
					} else {
						sung = "����";
					}
					// "�������" �����ϱ�
					// 2���� �迭�� �ʱ�ȭ
					String[][] localeCode = {{"����","00","08"},{"�λ�","09","12"},
							                 {"��õ","13","15"},{"���","16","25"},
							                 {"����","26","34"},{"���","35","39"},
							                 {"����","40","40"},{"�泲","41","43"},
							                 {"�泲","45","47"},{"����","44","44"},
							                 {"����","96","96"},{"����","48","54"},
							                 {"����","55","64"},{"����","65","66"},
							                 {"�뱸","67","70"},{"���","71","80"},
							                 {"�泲","81","84"},{"�泲","86","90"},
							                 {"���","85","85"},{"����","91","95"}};
					String localeStr = juminNum.substring(8, 10);
					int locale = Integer.parseInt(localeStr);
					String birthplace = null;
					
					for (int j = 0; j <= 19; j++) {  // localeCode.length = 19
						if (locale >= Integer.parseInt(localeCode[j][1]) &&  // ���׿����� ���� && => ������ ������ �Ѵ� ���϶�
								locale <= Integer.parseInt(localeCode[j][2])) {
							birthplace = localeCode[j][0];
						}
					}
					vector.add(String.valueOf(age));  // �������� ���ڿ��� ��ȯ�Ͽ� ����
					vector.add(sung);
					vector.add(birthplace);
					vector.add(juminNum.substring(2, 4) + "��" + juminNum.substring(4, 6) + "��");
				} else {
					JOptionPane.showMessageDialog(null, 
							"�ֹι�ȣ�� Ʋ��.", 
							"�����޽���", JOptionPane.ERROR_MESSAGE);  // �������� �޼ҵ忡�� ���ڰ� 4��¥��(�������� ���� X�� ����)
					
					// ����ڸ� ���� ���
					west.input.tf[4].setText(null);
					west.input.tf[4].requestFocus();
					return;  // �� ���� �״�� �־��!!
				}
			}  // end if��
			
			// ����ڰ� ������ ���� ����
			vector.add(west.input.box.getSelectedItem().toString());  // getSelectedItem() => ����ڰ� ������ Item�� ������ �Ͷ� / .toString() => ��ü�� String���� ��ȯ
			
			west.input.tf[0].setText(null);
			west.input.tf[1].setText(null);
			west.input.tf[2].setText(null);
			west.input.tf[3].setText(null);
			west.input.tf[4].setText(null);
			west.input.box.setSelectedIndex(0);  // '����'���� �ʱ�ȭ ���Ѷ�
			west.input.tf[0].requestFocus();  // ��ȣ �Է¶��� ��Ŀ���� �־��!!
			
			showTable.data.addElement(vector);
			showTable.datamodel.fireTableDataChanged();
		}
	}
//	end Buttons Ŭ����
	
//	ShowTable Ŭ����
	// JTable�� �Է��� �����͸� �����ִ� ���� Ŭ����
	class ShowTable extends MouseAdapter {
		
		DefaultTableModel datamodel;
		JTable table;
		JScrollPane scrollPane;
		
		String[] colName = {"��ȣ","�̸�","�ڵ�����ȣ","E-Mail","�ֹι�ȣ","����","����","�������","����","����"};
		
		Vector<Vector<String>> data;
		Vector<String> column_name;
		
		int row = -1;
		
		// ������
		public ShowTable() {
			data = new Vector<Vector<String>>();
			column_name = new Vector<String>();
			
			for (int i = 0; i < colName.length; i++) {
				column_name.add(colName[i]);
			}
			datamodel = new DefaultTableModel(data, column_name) {
				public boolean isCellEditable(int row, int column) {
					return false;
				};
			};
			table = new JTable(datamodel);
			scrollPane = new JScrollPane(table);
			
			// �̺�Ʈ ���� => addxxxListener() �޼ҵ�
			table.addMouseListener(this);
		}
		
		@Override  // �������� �������̵� ��Ų��.
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
