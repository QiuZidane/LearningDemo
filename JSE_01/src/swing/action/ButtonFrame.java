package swing.action;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * ************* 传统做法 *************
 * 使用内部类或者外部类实现ActionListener <br>
 * 然后给按钮添加监听器 <br>
 * 
 */
public class ButtonFrame extends JFrame {

	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public ButtonFrame() {

	setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	
	// ************* 传统做法 *************
	
	JButton yellowButton = new JButton("Yellow");
	JButton redButton = new JButton("<html><a>Red</a></html>");
	
	// add button to panel
	buttonPanel = new JPanel();
	buttonPanel.add(yellowButton);
	buttonPanel.add(redButton);
	
	// add panel to frame
	add(buttonPanel);
	
	ColorAction yellowAction = new ColorAction(Color.YELLOW);
	ColorAction redAction = new ColorAction(Color.RED);
	
	yellowButton.addActionListener(yellowAction);
	redButton.addActionListener(redAction);
	
	
	}

	private class ColorAction implements ActionListener {

		private Color backgroundColor;

		public ColorAction(Color c) {
			backgroundColor = c;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			buttonPanel.setBackground(backgroundColor);
			
			System.out.println(e.toString());

		}

	}

	public static void main(String[] args) {
		new ButtonFrame().setVisible(true);

	}

}

