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
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.util.Locale;
import java.util.StringTokenizer;
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
 * 	������	2022.02.04	��Ʈ�� ȭ�� ���� �� South��, Center��, '�߰�'��� ����
 * 	������	2022.02.07	'����', '����', '�˻�' ��ư Ŭ�� �� "�����˻� ī��"�� �ٲ�
 * 	������	2022.02.08	'�˻�', '���� ����', '���� ����', '�ݱ�' ��� ����
 */

// ������(GUI) ���α׷� ���� �� ���� JFrame Ŭ�����κ��� ����� ����!!
// JFrame�� �ֻ��� �����̳��̴�.
public class ManageSystem extends JFrame {  // �ܺ� Ŭ����
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
			
			// '����' �޴� �̺�Ʈ ����
			fopen.addActionListener(this);  // '����'
			fsave.addActionListener(this);  // '����'
			fexit.addActionListener(this);  // '�ݱ�'
			
			// '����' �޴� �̺�Ʈ ����
			sno.addItemListener(this);  // '��ȣ' ����
			sname.addItemListener(this);  // '�̸�' ����
			schul.addItemListener(this);  // '�������' ����
			sjob.addItemListener(this);  // '����' ����
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("����"))	open();
			else if(e.getActionCommand().equals("����"))	save();
			else if(e.getActionCommand().equals("�ݱ�"))	exit();
		}
		// '����' ���� ��� �޼ҵ� ����
		public void open() {
			StringTokenizer st;
			Vector v;
			
			readOpen = new FileDialog(ManageSystem.this, "��������", FileDialog.LOAD);
			readOpen.setVisible(true);
			
			fileDir = readOpen.getDirectory();
			fileName = readOpen.getFile();
			readfileName = fileDir + "//" + fileName;
			
			try {
				BufferedReader read = new BufferedReader(new FileReader(readfileName));
				String line = null;
				
				while((line = read.readLine()) != null) {
					// �޸��忡 �̷��� ����Ǿ� ���� => 1, ȫ�浿, 010-2415-1234, ..., ���α׷���
					st = new StringTokenizer(line, ", ");  // ", " => ���й���(delimiter)
					v = new Vector();
					
					while(st.hasMoreTokens()) {
						v.add(st.nextToken());
					}
					showTable.data.addElement(v);
				}
				showTable.datamodel.fireTableDataChanged();
				read.close();  // �ڿ� ����
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		// '����' ���� ��� �޼ҵ� ����
		public void save() {
			saveOpen = new FileDialog(ManageSystem.this, "��������", FileDialog.SAVE);
			saveOpen.setVisible(true);
			
			fileDir = saveOpen.getDirectory();
			fileName = saveOpen.getFile();
			savefileName = fileDir + "//" + fileName;
			
			String str = "";
			String temp = "";
			
			try {
				BufferedWriter save = new BufferedWriter(new FileWriter(savefileName));
				
				for (int i = 0; i < showTable.table.getRowCount(); i++) {
					temp = showTable.data.elementAt(i).toString();
					// vector ��ü�� ���� ���¸� ���� [1, ȫ�浿, 010-2415-1234, ..., ���α׷���]
					str += temp.substring(1, temp.length() - 1) + "\n";  // �հ� �ڿ� �ִ� []���ȣ�� ���� �ٹٲ�
				}
				save.write(str);
				save.close();  // ������ �������� '�ڿ� ����'
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		
		// '�ݱ�' ��� �޼ҵ� ����
		public void exit() {
			System.exit(0);  // ���α׷� ���� ���� ���Ѷ�!!
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
				
				// �̺�Ʈ ����
				searchButton.addActionListener(this);
				exitButton.addActionListener(this);
				
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
				// [�߿�] searchType ���� search() �޼ҵ忡�� �����Ͽ� �ϳ��� �޼ҵ�� �� ���� �˻� ����� �ذ�����!!
				if(e.getActionCommand().equals("�̸�"))			searchType = 1;
				else if(e.getActionCommand().equals("����"))		searchType = 9;
				else if(e.getActionCommand().equals("�������"))	searchType = 7;
				else if(e.getActionCommand().equals("����"))		searchType = 8;
				else if(e.getActionCommand().equals("ã��"))		search();  // ����� ���� �޼ҵ� ȣ��
				else if(e.getActionCommand().equals("������"))	goOut();  // ����� ���� �޼ҵ� ȣ��
			}
			// 'ã��' ��ư Ŭ�� �� ó���� �޼ҵ� ����
			public void search() {
				Vector v = new Vector();
				
				// ���� �˻�(Sequential search �˰��� ����
				for (int i = 0; i < showTable.data.size(); i++) {
					// [���� �߿�] ��ũ��
					if(nameText.getText().equals(showTable.data.elementAt(i).get(searchType))) {
						v.addElement(showTable.data.elementAt(i));
					}
				}
				showTable.datamodel.setDataVector(v, showTable.column_name);
				showTable.TableSize();  // �� ũ�� ���� �޼ҵ� ȣ��
				nameText.setText(null);
			}
			// '������' ��ư Ŭ�� �� ó���� �޼ҵ� ���� => �ٽ� JTable�� ��ü �����͸� �����ֱ�
			public void goOut() {
				west.output.card.show(west.output, "�Ż����� ī��");  // ī�� �ٲٱ�
				
				buttons.addBtn.setEnabled(true);  // '�߰�' ��ư Ȱ��ȭ
//				buttons.nextBtn.setEnabled(true);  // '����' ��ư Ȱ��ȭ
				
				west.input.tf[0].setText(null);  // west �� �����
				west.input.tf[1].setText(null);
				west.input.tf[2].setText(null);
				west.input.tf[3].setText(null);
				west.input.tf[4].setText(null);
				west.input.box.setSelectedIndex(0);  // '����'���� �ʱ�ȭ ���Ѷ�
				west.input.tf[0].requestFocus();
				
				west.output.label[0].setText("   ���� :");
				west.output.label[1].setText("   ���� :");
				west.output.label[2].setText("   ������� :");
				west.output.label[3].setText("   ���� :");
				
				showTable.datamodel.setDataVector(showTable.data, showTable.column_name);  // ��ü ������ �ѷ��ֱ�
				showTable.TableSize();  // �� ũ�� ���� �޼ҵ� ȣ��
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
			if(e.getActionCommand().equals("�߰�")) addData();  // addData(); => �����(������)�� ������ �޼ҵ� ȣ��
			if(e.getActionCommand().equals("����")) updateData();
			if(e.getActionCommand().equals("����")) deleteData();
			if(e.getActionCommand().equals("�˻�")) searchData();
		}
		// [�߿�] '�˻�' ��ư���� ActionEvent�� �߻����� �� ó���ϴ� �޼ҵ� ����
		public void searchData() {
			// west.output.card.next(west.output);  // [��� 1]
			
			west.output.card.show(west.output, "�����˻� ī��");  // [��� 2]
		}
		
		// '����' ��ư���� ActionEvent�� �߻����� �� ó���ϴ� �޼ҵ� ����
		public void deleteData() {
			int yes_no_select = JOptionPane.showConfirmDialog(null, 
					"���� �����ϰڽ��ϱ�?", 
					"���� Ȯ�� â", 
					JOptionPane.YES_NO_OPTION);
			if (yes_no_select == JOptionPane.YES_OPTION) {
				addBtn.setEnabled(true);
				
				JTable tb = showTable.table;
				int deleteRow = tb.getSelectedRow();
				
				if(deleteRow == -1) {  // ����ڰ� ��� ���� �������� �ʰ� "����" ��ư�� ���� ���
					return;  // ���� ���� �״�� �����ض�
				} else {
					DefaultTableModel model = (DefaultTableModel)tb.getModel();
					model.removeRow(deleteRow);
					
					west.input.tf[0].setText(null);
					west.input.tf[1].setText(null);
					west.input.tf[2].setText(null);
					west.input.tf[3].setText(null);
					west.input.tf[4].setText(null);
					west.input.box.setSelectedIndex(0);  // '����'���� �ʱ�ȭ ���Ѷ�
					
					west.output.label[0].setText("   ���� :" + "");
					west.output.label[1].setText("   ���� :" + "");
					west.output.label[2].setText("   ������� :" + "");
					west.output.label[3].setText("   ���� :" + "");
					west.input.tf[0].requestFocus();
				}
			} else {
				return;
			}
		}
		
		// '����' ��ư���� ActionEvent�� �߻����� �� ó���ϴ� �޼ҵ� ����
		public void updateData() {
			
			int updateRow = showTable.table.getSelectedRow();
			
			// '�ڵ�����ȣ' ���� ��
			showTable.table.setValueAt(west.input.tf[2].getText(), updateRow, 2);
			
			// '�̸���' ���� ��
			showTable.table.setValueAt(west.input.tf[3].getText(), updateRow, 3);
			
			// '����' ���� ��
			showTable.table.setValueAt(west.input.box.getSelectedItem().toString(), updateRow, 9);
			
			west.input.tf[0].setText(null);
			west.input.tf[1].setText(null);
			west.input.tf[2].setText(null);
			west.input.tf[3].setText(null);
			west.input.tf[4].setText(null);
			west.input.box.setSelectedIndex(0);  // '����'���� �ʱ�ȭ ���Ѷ�
			
			// ������ "����"�� ���� �����͸� �Է� �ޱ� ���ؼ� �ֹι�ȣ �Է¶� �ٽ� Ȱ��ȭ ��Ű��!!
			west.input.tf[4].setEditable(true);
			west.input.tf[0].requestFocus();  // ��ȣ �Է¶��� ��Ŀ���� �־��!!
			
			west.output.label[0].setText("   ���� :" + "");
			west.output.label[1].setText("   ���� :" + "");
			west.output.label[2].setText("   ������� :" + "");
			west.output.label[3].setText("   ���� :" + "");
			return;
		}
		
		public void addData() {
			nextBtn.setEnabled(true);  //'����' ��ư Ȱ��ȭ
			
			vector = new Vector<String>();  // �����迭
			vector.add(west.input.tf[0].getText());  // ��ȣ  / west.input.tf[0] => �Ҿƹ���.�ƹ���.�ڽ�(����)
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
			
			// '�ֹι�ȣ' ���� ǥ����(regex) ���� => ��ȿ�� üũ
			String regex = "[0-9]{6}-[1234][0-9]{6}";  // [0 ~ 9��]{�ݺ�Ƚ��}
			boolean check = juminNum.matches(regex);
			
			if (check == false) {
				JOptionPane.showMessageDialog(null, 
						"�ֹι�ȣ�� ���Ŀ� ���� ����" + "\n" + "�ֹι�ȣ�� �ٽ� �Է��ϼ���.", 
						"�����޽���",
						JOptionPane.ERROR_MESSAGE); // �������� �޼ҵ忡�� ���ڰ� 4��¥��(�������� ���� X�� ����)
				
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
					showTable.TableSize();  // �� ũ�� ���� �޼ҵ� ȣ��
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
			// getSelectedItem() �޼ҵ�� ���� Ÿ���� Object�̱� ������ String Ÿ������ ��ȯ�Ͽ� �����ؾ���!!
			// getSelectedItem() => ����ڰ� ������ Item�� ������ �Ͷ� / .toString() => ��ü�� String���� ��ȯ
			vector.add(west.input.box.getSelectedItem().toString());
			
			// ����ڿ� ���� ���
			west.input.tf[0].setText(null);
			west.input.tf[1].setText(null);
			west.input.tf[2].setText(null);
			west.input.tf[3].setText(null);
			west.input.tf[4].setText(null);
			west.input.box.setSelectedIndex(0);  // '����'���� �ʱ�ȭ ���Ѷ�
			west.input.tf[0].requestFocus();  // ��ȣ �Է¶��� ��Ŀ���� �־��!!
			
			showTable.data.addElement(vector);
			showTable.datamodel.fireTableDataChanged();  // ��ȭ�� �뺸���ش�.
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
		
		Vector<Vector<String>> data;  // [���� �߿�]
		Vector<String> column_name;
		
		int row = -1;  // ���� ���� ����
		
		// ������
		public ShowTable() {
			data = new Vector<Vector<String>>();
			column_name = new Vector<String>();
			
			for (int i = 0; i < colName.length; i++) {
				column_name.add(colName[i]);
			}
			// [1�ܰ�] DefaultTableModel Ŭ���� ��ü ����
			// public DefaultTableModel(Vector data, Vector columnNames) ������ ����
			datamodel = new DefaultTableModel(data, column_name) {
				public boolean isCellEditable(int row, int column) {
					return false;  // Cell���� ������ �����Ϸ��� false�� �ش�.
				};
			};
			// [2�ܰ�] JTable ��ü ����
			table = new JTable(datamodel);
			
			// [3�ܰ�] JScrollPane ��ü ����
			scrollPane = new JScrollPane(table);
			
			// �̺�Ʈ ���� => addxxxListener() �޼ҵ�
			table.addMouseListener(this);
		}
		
		@Override  // �������� �������̵� ��Ų��.
		public void mouseClicked(MouseEvent e) {
			row = table.getSelectedRow();
			Info();  // ����� ���� �޼ҵ� ȣ��
		}
		public void Info() {
			int row = this.row;
			
			west.input.tf[0].setText((String)showTable.table.getValueAt(row, 0));  // ��ȣ
			west.input.tf[1].setText((String)showTable.table.getValueAt(row, 1));  // �̸�
			west.input.tf[2].setText((String)showTable.table.getValueAt(row, 2));  // �ڵ�����ȣ
			west.input.tf[3].setText((String)showTable.table.getValueAt(row, 3));  // �̸���
			west.input.tf[4].setText((String)showTable.table.getValueAt(row, 4));  // �ֹε�Ϲ�ȣ
			
			// [�߿�] �ֹι�ȣ�� ���� ���ϰ� ��Ȱ��ȭ ��Ű��!
			west.input.tf[4].setEditable(false);
			
			// ������ west.input ��ü�� �����ֱ�
			west.input.box.setSelectedItem(showTable.table.getValueAt(row, 9));  // ����
			
			west.output.label[0].setText("   ���� :" + "   " + showTable.table.getValueAt(row, 5));  // ����
			west.output.label[1].setText("   ���� :" + "   " + showTable.table.getValueAt(row, 6));  // ����
			west.output.label[2].setText("   ������� :" + "   " + showTable.table.getValueAt(row, 7));  // �������
			west.output.label[3].setText("   ���� :" + "   " + showTable.table.getValueAt(row, 8));  // ����
			
			showTable.table.changeSelection(row, 0, false, false);
		}  // end Info()
		
		// JTable�� ���� ũ�� �����ϱ�
		public void TableSize() {
			table.getColumnModel().getColumn(0).setPreferredWidth(50);	// ��ȣ �� ����
			table.getColumnModel().getColumn(1).setPreferredWidth(50);	// �̸� �� ����
			table.getColumnModel().getColumn(2).setPreferredWidth(120);	// �ڵ�����ȣ �� ����
			table.getColumnModel().getColumn(3).setPreferredWidth(150);	// �̸��� �� ����
			table.getColumnModel().getColumn(4).setPreferredWidth(130);	// �ֹι�ȣ �� ����
			table.getColumnModel().getColumn(5).setPreferredWidth(50);	// ���� �� ����
			table.getColumnModel().getColumn(6).setPreferredWidth(50);	// ���� �� ����
			table.getColumnModel().getColumn(7).setPreferredWidth(80);	// ������� �� ����
			table.getColumnModel().getColumn(8).setPreferredWidth(70);	// ���� �� ����
			table.getColumnModel().getColumn(9).setPreferredWidth(70);	// ���� �� ����
		}
	}
//	end ShowTable Ŭ����
	
	public static void main(String[] args) {
		// ��ü ����(main������ ��ü ������)
		ManageSystem manageSystem = new ManageSystem();
	}
}
