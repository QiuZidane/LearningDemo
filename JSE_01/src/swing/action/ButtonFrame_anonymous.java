package swing.action;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * ************* 匿名内部类做法 *************
 * 将要实现的动作放在匿名内部类 <br>
 * 
 */
public class ButtonFrame_anonymous extends JFrame implements ActionListener {

	private JPanel buttonPanel;
	
	private JButton blueButton = new JButton("blue");


	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;

	public ButtonFrame_anonymous() {
		
		buttonPanel = new JPanel();
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		add(buttonPanel);
		
		// blueButton是单独加上面板的，其他button是通过createButton加入面板的
		buttonPanel.add(blueButton);
		blueButton.addActionListener(this);
		
	}

	public void createButton(String title, final Color backgroundColor) {

		JButton jb = new JButton(title);
		buttonPanel.add(jb);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				buttonPanel.setBackground(backgroundColor);
				

			}
		});

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ButtonFrame_anonymous bf = new ButtonFrame_anonymous();
				bf.createButton("yellow", Color.yellow);
				bf.createButton("red", Color.red);
				bf.setVisible(true);
				
			}
		});
		

	}

	/**
	 * 这里判断了点击哪个按钮
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (e.getSource() == blueButton) {
			// e.getActionCommand() 返回动作事件相关命令字符串，如果是按钮，就返回按钮标签
			System.out.println(e.getActionCommand());
			buttonPanel.setBackground(Color.blue);
		}
		
		
	}
	
	

}
