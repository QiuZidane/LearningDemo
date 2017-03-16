package swing.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.ButtonPeer;

import javax.accessibility.AccessibleAction;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class LayoutTest {

	private static JButton createButton(String name, String desc, Icon icon, JPanel parentPanel,
			Color color) {

		JButton button = new JButton(new MyAction01(name, desc, icon, parentPanel, color));

		parentPanel.add(button);

		return button;

	}

	public static void main(String[] args) {

		JPanel southPanel = new JPanel();
		JPanel northPanel = new JPanel();
		JPanel eastPanel = new JPanel();
		JPanel westPanel = new JPanel();

		JButton redButton = createButton("red", "this is a red button",
				new ImageIcon(LayoutTest.class.getResource("./star1.png")), southPanel, Color.RED);
		JButton blueButton = createButton("blue", "this is a blue button",
				new ImageIcon(LayoutTest.class.getResource("./star1.png")), northPanel, Color.blue);
		JButton pinkButton = createButton("pink", "this is a pink button",
				new ImageIcon(LayoutTest.class.getResource("./star1.png")), eastPanel, Color.pink);
		JButton yellowButton = createButton("blue", "this is a yellow button",
				new ImageIcon(LayoutTest.class.getResource("./star1.png")), westPanel, Color.YELLOW);
		
		

		MyFrame01 myFrame01 = new MyFrame01();
		myFrame01.addPanel(westPanel, BorderLayout.CENTER);
		myFrame01.addPanel(eastPanel, BorderLayout.EAST);
		myFrame01.addPanel(southPanel, BorderLayout.SOUTH);
		myFrame01.addPanel(northPanel, BorderLayout.NORTH);
		
		
		
		myFrame01.setVisible(true);
		
		

	}

}

class MyFrame01 extends JFrame {

	private static int DEFAULT_WIDTH = 600;
	private static int DEFAULT_HEIGHT = 400;

	public MyFrame01() {

		setTitle("This is MyFrame01");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	/**
	 * 往Frame上加入panel
	 * 
	 * @param panel
	 * @param position
	 *            方位
	 */
	public void addPanel(JPanel panel, String position) {

		add(panel, position);
		System.out.println("在方位:"+position+" 加入控件:"+panel);

	}

}

class MyAction01 extends AbstractAction {

	private JPanel parentPanel;

	public MyAction01(String name, String desc, Icon icon, JPanel parentPanel, Color color) {

		putValue(Action.NAME, name);
		putValue(Action.LONG_DESCRIPTION, desc);
		putValue(Action.SMALL_ICON, icon);
		putValue("color", color);

		this.parentPanel = parentPanel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Color color = (Color) getValue("color");
		parentPanel.setBackground(color);

	}

}
