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

	public Lunch_Main() {

		config();

		actionstart();
<<<<<<< HEAD

		setResizable(false);
=======
		
		setTitle("점심메뉴 뽑기 프로그램");
>>>>>>> refs/heads/fix
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

		delete_btn.setBounds(589, 468, 62, 23);

		contentPane.add(delete_btn);

		start_btn.setFont(new Font("굴림", Font.PLAIN, 18));

		start_btn.setBounds(180, 382, 156, 74);

		contentPane.add(start_btn);

		tf_result = new JTextField();

		tf_result.setFont(new Font("굴림", Font.BOLD, 20));

		tf_result.setBackground(Color.PINK);

		tf_result.setEditable(false);

		tf_result.setHorizontalAlignment(SwingConstants.CENTER);

		tf_result.setBounds(167, 178, 184, 74);

		contentPane.add(tf_result);

		tf_result.setColumns(10);

		JLabel label = new JLabel("오늘의 점심 메뉴는?");

		label.setFont(new Font("굴림", Font.BOLD, 20));

		label.setHorizontalAlignment(SwingConstants.CENTER);

		label.setBounds(168, 105, 183, 63);

		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(484, 50, 190, 406);

		contentPane.add(scrollPane);

		list.setFont(new Font("굴림", Font.BOLD, 18));

		scrollPane.setViewportView(list);

		add();

		setVisible(true);

		search_btn.setBounds(214, 289, 97, 23);

		contentPane.add(search_btn);

		search_btn.setVisible(false);

	}

	private void add() {

		list.add("청진식당");

		list.add("뚝배기집");

		list.add("굿모닝");

		list.add("맘스터치");

		list.add("순대국밥");

		list.add("쌀국수");

		list.add("한솥");

		list.add("육개장");

		list.add("순두부찌개");

		list.add("부대찌개");

		list.add("먹쉬돈나");

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

			JOptionPane.showMessageDialog(null, "삭제에 성공했습니다.");

		} else if (e.getSource() == start_btn) {

			search_btn.setVisible(false);

			System.out.println("시작버튼 클릭");

			start_btn.setEnabled(false);

			select();

		} else if (e.getSource() == search_btn) {

			System.out.println("검색버튼 클릭");

			try {

				Desktop.getDesktop().browse(new URI("https://www.google.co.kr/search?"

						+ "q=종각역+" + tf_result.getText() + "&oq=종각역+" + tf_result.getText()

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
