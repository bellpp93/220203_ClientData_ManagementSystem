package �������ý��۰���;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <<���� �̷�(Modification Information)>>
 * 
 * 	������	�ۼ���		�۾�����
 * 	----	----		-------
 * 	������	2022.02.03	��Ʈ�� ȭ�� ����
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
		
	}
	
//	MenuMain Ŭ����
	// �޴� ����� '���� Ŭ����' ����
	// JPanel�� ���� �����̳��̴�.
	class MenuMain extends JPanel implements ActionListener, ItemListener {
		
		// ������
		public MenuMain() {
			
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
			
		}
		// ������ �Է��� �޴� Input ���� Ŭ���� ����
		class Input extends JPanel {
			// ������
			public Input() {
				
			}
		}
		// �Է¹��� �ֹι�ȣ�κ��� ���� ����(����, ����, �������, ����)�� ������ ���� Ŭ���� ����
		class Output extends JPanel implements ActionListener {
			
			// ������
			public Output() {
				
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













