package 고객관리시스템개발;

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
 * <<개정 이력(Modification Information)>>
 * 
 * 	개발자	작성일		작업내용
 * 	----	----		-------
 * 	윤종인	2022.02.03	인트로 화면 구현 중 메뉴, West쪽의 입력, 신상정보 보더
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
		OUTTER: while (true) { // 무한 Loop, OUTTER(대문자로)는 레이블이라고 한다.
			ImageIcon icon = new ImageIcon("images/고객정보관리_이미지.jpg");
			JOptionPane.showMessageDialog(null, null, "고객정보관리 시스템", JOptionPane.NO_OPTION, icon);

			// 패스워드 인증창 만들어 띄우기
			String password = JOptionPane.showInputDialog("고객관리 시스템" + "\n" + "패스워드 입력");
			String passwd = "test1234"; // 실제 패스워드

			if (password == null) {
				break OUTTER;
			} else if (password.equals(passwd)) { // 인증 확인!! => 실행 창을 띄워라
				setTitle("고객정보관리 시스템");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// 내부 클래스 객체 붙이기
				add(menuMain.bar, BorderLayout.NORTH);
				add(west, BorderLayout.WEST);
				
				setSize(1200, 700);
				setLocation(500, 50);
				setVisible(true);
				
				break OUTTER;
			} else {
				JOptionPane.showMessageDialog(null, "패스워드가 맞지 않습니다." + "\n" + "'확인' 버튼을 누르세요.", "패스워드 인증 실패",
						JOptionPane.ERROR_MESSAGE);
				continue OUTTER;
			}
		}
	}
	
//	MenuMain 클래스
	// 메뉴 만들기 '내부 클래스' 구현
	// JPanel은 작은 컨테이너이다.
	class MenuMain extends JPanel implements ActionListener, ItemListener {
		// 전역개념(전역참조변수)으로 생성자 밖으로 빼냄
		JMenuBar bar;
		JMenu file, sort, help;
		JMenuItem fopen, fsave, fexit, proinfo;
		JCheckBoxMenuItem sno, sname, schul, sjob;
		
		FileDialog saveOpen, readOpen;
		String fileDir, fileName, savefileName, readfileName;
		ButtonGroup gr = new ButtonGroup();
		
		// 생성자
		public MenuMain() {
			bar = new JMenuBar();
			
			file = new JMenu("파일");
			sort = new JMenu("정렬");
			help = new JMenu("도움말");
			
			fopen = new JMenuItem("열기");
			fsave = new JMenuItem("저장");
			fexit = new JMenuItem("닫기");
			
			sno = new JCheckBoxMenuItem("번호");
			sname = new JCheckBoxMenuItem("이름");
			schul = new JCheckBoxMenuItem("출신지역");
			sjob = new JCheckBoxMenuItem("직업");
			
			proinfo = new JMenuItem("프로그램 정보");
			
			// 객체 붙이기
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
//	end MenuMain 클래스
	
//	West 클래스
	// 데이터를 입력받는 West쪽 내부 클래스 구현
	class West extends JPanel {
		// 내부 클래스 객체 생성
		Input input = new Input();
		Output output = new Output();
		
		// 생성자
		public West() {
			setLayout(new BorderLayout());
			add(input, BorderLayout.CENTER);
			add(output, BorderLayout.SOUTH);
		}
		// 데이터 입력을 받는 Input 내부 클래스 구현
		class Input extends JPanel {
			
			JTextField[] tf = new JTextField[5];
			String[] text = {"번호","이름","핸드폰번호","이메일","주민등록번호"};
			String[] jobText = {"선택","회사원","공무원","프로그래머","의사","변호사","학생","기타"};
			JLabel la, job;
			JComboBox box;
			
			// 생성자
			public Input() {
				setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "입력")); // 여기서 2는 선의 두께
				setLayout(new GridLayout(6, 2, 5, 30));

				for (int i = 0; i < text.length; i++) {
					la = new JLabel(text[i]);
					tf[i] = new JTextField(10);
					la.setHorizontalAlignment(JLabel.CENTER);
					add(la);
					add(tf[i]);
				}
				box = new JComboBox(jobText);
				job = new JLabel("직업");
				job.setHorizontalAlignment(JLabel.CENTER);
				add(job);
				add(box);
				setPreferredSize(new Dimension(340, 300));
			}
		}
		// 입력받은 주민번호로부터 정보 추출(나이, 성별, 출생지역, 생일)을 보여줄 내부 클래스 구현
		class Output extends JPanel implements ActionListener {
			
			JPanel info = new JPanel();		// '신상정보' 보더 만들기 JPanel
			JPanel search = new JPanel();	// '정보검색' 보더 만들기 JPanel
			
			CardLayout card = new CardLayout();
			String[] text = {"  나이:","  성별:","  출생지역:","  생일:"};
			JLabel[] label = new JLabel[4];
			ButtonGroup group = new ButtonGroup();
			JRadioButton[] searchRadio = new JRadioButton[4];
			JButton searchButton;
			JButton exitButton;
			JTextField nameText;
			String[] search_name = {"이름","직업","출생지역","생일"};
			int searchType;
			
			// 생성자
			public Output() {
				// '신상정보' 보더 만들기
				info.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "신상정보"));
				info.setLayout(new GridLayout(4, 1));
				
				for (int i = 0; i < text.length; i++) {
					label[i] = new JLabel(text[i], JLabel.LEFT);
					info.add(label[i]);
				}
				
				// '정보검색' 보더 만들기
				search.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "정보검색"));
				nameText = new JTextField(10);
				searchButton = new JButton("찾기");
				exitButton = new JButton("나가기");
				
				searchButton.setBackground(Color.CYAN);
				exitButton.setBackground(Color.LIGHT_GRAY);
				
				setPreferredSize(new Dimension(340, 300));
				
				int x = -70;
				search.setLayout(null);  // 사용자 정의 배치 방법(일종의 편법)
				
				for (int i = 0; i < searchRadio.length; i++) {
					searchRadio[i] = new JRadioButton(search_name[i]);
					searchRadio[i].setBounds(x += 80, 60, 80, 30);
					
					group.add(searchRadio[i]);
					search.add(searchRadio[i]);
					// 이벤트 연결
					searchRadio[i].addActionListener(this);
				}
				nameText.setBounds(40, 110, 200, 30);  // 입력
				searchButton.setBounds(35, 170, 80, 30);  // 찾기
				exitButton.setBounds(140, 170, 110, 30);  // 나가기
				search.add(nameText);
				search.add(searchButton);
				search.add(exitButton);
				
				card = new CardLayout();
				setLayout(card);
				
				add(info,"신상정보 카드");
				add(search,"정보검색 카드");
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
