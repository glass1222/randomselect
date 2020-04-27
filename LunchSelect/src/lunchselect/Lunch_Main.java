package lunchselect;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Lunch_Main extends JFrame implements ActionListener {

	private JPanel contentPane;

	private JTextField tf_result;

	JButton add_btn = new JButton("추가");

	JButton delete_btn = new JButton("삭제");

	JButton start_btn = new JButton("뽑기");

	List list = new List();

	String[] str;

	JButton search_btn = new JButton("검색");
	
	private String one, two;
	private JLabel first = new JLabel();
	private JLabel second = new JLabel();
	
	public Lunch_Main() {

		config();

		actionstart();

		setResizable(false);
	
		set();
		setlabel();
		timeget();
		
	}
	public void timeget() {
		String sday=null;
		while(true) {
			
			Calendar t=Calendar.getInstance();
			// 날짜, 시간을 받아옴
			int year = t.get(Calendar.YEAR);
			int month = t.get(Calendar.MONTH);
			int date = t.get(Calendar.DATE);
			int amPm = t.get(Calendar.AM_PM);
			int hour = t.get(Calendar.HOUR);
			int min = t.get(Calendar.MINUTE);
			int sec = t.get(Calendar.SECOND);
			String ampm=amPm==Calendar.AM? "AM":"PM"; // 비교 후 am 이나 pm을 ampm에 저장
			int day= t.get(Calendar.DAY_OF_WEEK); // 요일을 정수형으로 일~토->1~7
			switch(day) { //요일 설정
			case 1:
				sday="Sun";
				break;
			case 2:
				sday="Mon";
				break
			case 3:
				sday="Tus";
				break;
			case 4:
				sday="Wed";
				break;
			case 5:
				sday="Thu";
				break;
			case 6:
				sday="Fri";
				break;
			case 7:
				sday="Sat";
				break;
			}
			one= (year+"."+month+"."+date+"."+sday+"day");//문자열 one에 저장
			two=(ampm+" "+hour+":"+min+":"+sec);//문자열two에 저장
			first.setText(one);
			second.setText(two);
			contentPane.add(first); //기존 패널에 추가
			contentPane.add(second); // 마찬가지
			try { 
				Thread.sleep(100); // 0.1초 간격으로
				repaint();
		    } catch(Exception e) {} //예외처리 
		}
	}
	public void setlabel() { //날짜, 시간 라벨 설정들
	
		first.setOpaque(false);
		first.setBackground(new Color(0,0,0,0));
		first.setBounds(20,0,300,50);
		first.setForeground(Color.black);
		first.setFont(new Font("맑은 고딕",Font.BOLD,14));
		second.setOpaque(false);
		second.setBackground(new Color(0,0,0,0));
		second.setBounds(37,20,300,50);
		second.setForeground(Color.black);
		second.setFont(new Font("맑은 고딕",Font.BOLD,14));
	}
	public void set() {
		setTitle("점심메뉴 뽑기 프로그램"); // 타이틀 그냥 여기로 옮겼음
		setLocationRelativeTo(null); // 화면 가운데서 창이 나오게 함
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private void actionstart() {

		add_btn.addActionListener(this);

		delete_btn.addActionListener(this);

		start_btn.addActionListener(this);

		search_btn.addActionListener(this);

	}

	private void config() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 700, 550);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		add_btn.setBounds(515, 468, 62, 23);

		contentPane.add(add_btn);

		add_btn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		delete_btn.setBounds(589, 468, 62, 23);

		contentPane.add(delete_btn);
		
		delete_btn.setFont(new Font("맑은 고딕", Font.BOLD,12));

		start_btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));

		start_btn.setBounds(180, 382, 156, 74);

		contentPane.add(start_btn);

		tf_result = new JTextField();

		tf_result.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		tf_result.setBackground(Color.PINK);

		tf_result.setEditable(false);

		tf_result.setHorizontalAlignment(SwingConstants.CENTER);

		tf_result.setBounds(167, 178, 184, 74);

		contentPane.add(tf_result);

		tf_result.setColumns(10);

		JLabel label = new JLabel("오늘의 점심 메뉴는?");

		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		label.setHorizontalAlignment(SwingConstants.CENTER);

		label.setBounds(168, 105, 183, 63);

		JLabel label2 = new JLabel("메뉴 목록");
		label2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label2);
		label2.setBounds(480,15,200,30);
		
		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(484, 50, 190, 406);

		contentPane.add(scrollPane);

		list.setFont(new Font("맑은 고딕", Font.BOLD, 18));

		scrollPane.setViewportView(list);

		add();

		setVisible(true);

		search_btn.setBounds(214, 289, 97, 23);

		contentPane.add(search_btn);

		search_btn.setVisible(false);

	}

	private void add() {

		list.add("청춘라면");

		list.add("맘스터치");

		list.add("국밥");

		list.add("중국집");

		list.add("찜닭");

		list.add("학식 스폐셜메뉴");

		list.add("감자탕");
		
		list.add("도시락");


	}

	@Override

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == add_btn) {

			System.out.println("add버튼 클릭");

			String str = JOptionPane.showInputDialog("추가할 메뉴를 적어 주세요");

			if (str.equals("") || str.equals(" ") || str.equals("  ")) {

				JOptionPane.showMessageDialog(null, "값을 입력해 주세요!");

			} else {

				list.add(str);
			}

		} else if (e.getSource() == delete_btn) {

			System.out.println("삭제버튼 클릭");

			int i = list.getSelectedIndex();

			list.remove(i);

			JOptionPane.showMessageDialog(null, "해당 메뉴를 삭제했습니다.");

		} else if (e.getSource() == start_btn) {

			search_btn.setVisible(false);

			System.out.println("시작버튼 클릭");

			start_btn.setEnabled(false);

			select();

		} else if (e.getSource() == search_btn) {

			System.out.println("검색버튼 클릭");

			try {

				Desktop.getDesktop().browse(new URI("https://www.google.co.kr/search?"

						+ "q=대구대+" + tf_result.getText() + "&oq=대구대+" + tf_result.getText()

						+ "&aqs=chrome"

						+ "..69i57.4885j0j4&sourceid=chrome&ie=UTF-8"));

			} catch (IOException e2) {

				e2.printStackTrace();

			} catch (URISyntaxException e3) {

				e3.printStackTrace();
			}

		}

	}

	private void select() {

		str = new String[list.getItemCount()];

		str = list.getItems();

		int[] numArr = new int[str.length];

		for (int i = 0; i < numArr.length; i++) {

			numArr[i] = i;

		}

		for (int i = 0; i < 871125; i++) {

			int n = (int) (Math.random() * str.length);

			int temp = numArr[0];

			numArr[0] = numArr[n];

			numArr[n] = temp;

		}

		Timer m_timer = new Timer();

		TimerTask m_task1 = new TimerTask() {

			int i = 5;

			@Override

			public void run() {

				if (i > 0) {

					tf_result.setText(String.valueOf(i));

					i--;

				} else {

					m_timer.cancel();

					tf_result.setText(str[numArr[0]]);

					search_btn.setVisible(true);

					start_btn.setEnabled(true);

				}

			}

		};

		m_timer.schedule(m_task1, 100, 1000);

	}

	public static void main(String[] args) {

		new Lunch_Main();

	}

}
