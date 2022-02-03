package 고객관리시스템개발;

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
 * <<개정 이력(Modification Information)>>
 * 
 * 	개발자	작성일		작업내용
 * 	----	----		-------
 * 	윤종인	2022.02.03	인트로 화면 구현
 * 	...		...			.....
 */

// 윈도우(GUI) 프로그램 개발 시 먼저 JFrame 클래스로부터 상속을 받자!!
// JFrame은 최상위 컨테이너이다.
public class ManageSystem extends JFrame {
	public static final String String = null;
	
	// 내부 클래스 객체 생성
	MenuMain menuMain = new MenuMain();
	West west = new West();
	Buttons buttons = new Buttons();
	ShowTable showTable = new ShowTable();
	
	// 생성자
	public ManageSystem() {
		
	}
	
//	MenuMain 클래스
	// 메뉴 만들기 '내부 클래스' 구현
	// JPanel은 작은 컨테이너이다.
	class MenuMain extends JPanel implements ActionListener, ItemListener {
		
		// 생성자
		public MenuMain() {
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
		}
	}
//	end MenuMain 클래스
	
//	West 클래스
	// 데이터를 입력받는 West쪽 내부 클래스 구현
	class West extends JPanel {
		// 내부 클래스 객체 생성
		Input input = new Input();
		Output output = new Output();
		
		// 생성자
		public West() {
			
		}
		// 데이터 입력을 받는 Input 내부 클래스 구현
		class Input extends JPanel {
			// 생성자
			public Input() {
				
			}
		}
		// 입력받은 주민번호로부터 정보 추출(나이, 성별, 출생지역, 생일)을 보여줄 내부 클래스 구현
		class Output extends JPanel implements ActionListener {
			
			// 생성자
			public Output() {
				
			}

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		}
	}
//	end West 클래스
	
//	Buttons 클래스
	// 버튼 컴퍼넌트들 만들기 내부 클래스
	class Buttons extends JPanel implements ActionListener {

		// 생성자
		public Buttons() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}
//	end Buttons 클래스
	
//	ShowTable 클래스
	// JTable에 입력한 데이터를 보여주는 내부 클래스
	class ShowTable extends MouseAdapter {
		
		// 생성자
		public ShowTable() {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
		}
	}
//	end ShowTable 클래스
	
	public static void main(String[] args) {
		// 객체 생성(main에서는 객체 생성만)
		ManageSystem manageSystem = new ManageSystem();
		
	}
}













