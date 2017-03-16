package swing.action;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputListener;

/**
 *
 * 示例内容：
 * 将动作和键盘按钮结合 比如Ctrl+Y实现改变Panel颜色
 * 鼠标事件
 *
 */
public class ActionFrame extends JFrame {

	private JPanel buttonPanel;

	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 400;

	public ActionFrame() {

		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		buttonPanel = new JPanel();

		// 定义动作
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("star.png"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon("star.png"), Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon("star.png"), Color.RED);

		
		// 鼠标事件
		JButton blueButton = new JButton(blueAction);
		blueButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("mousePressed");
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouseReleased");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouseClicked");
			}
		});
		
		// 加入到panel中
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(blueButton);
		buttonPanel.add(new JButton(redAction));

		Action actionYYY = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// xxx 动作处理
			}
		};

		buttonPanel.add(new JButton(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// xxx 动作处理
			}
		}));

		add(buttonPanel);

		// 关联按钮和名字
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // 通过父组建获得输入映射
		imap.put(KeyStroke.getKeyStroke("typed a"), "YYY"); // 将输入a这个键盘输入事件添加到映射中，并与动作映射键值"YYY"关联
		imap.put(KeyStroke.getKeyStroke("ctrl A"), "YYY"); //
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue"); //
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red"); //

		// 获取动作和动作映射键值
		ActionMap aMap = buttonPanel.getActionMap();
		aMap.put("YYY", yellowAction);
		aMap.put("panel.blue", blueAction);
		aMap.put("panel.red", redAction);
		aMap.put("YYY", actionYYY);

		// 适配器
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("关闭啦！");
			}
		});

		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouseReleased");

			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("mousePressed");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("mouseExited");

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("mouseEntered");

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouseClicked");

			}
		});

	}

	public class ColorAction extends AbstractAction {

		public ColorAction(String name, Icon icon, Color c) {

			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION,
					"Set panel color to " + name.toLowerCase(Locale.CHINESE));
			putValue("color", c);
			putValue("text", name);

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Color c = (Color) getValue("color");
			String text = (String) getValue("text");
			buttonPanel.setBackground(c);
			System.out.println(text);

		}

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				ActionFrame aFrame = new ActionFrame();
				aFrame.setVisible(true);
				aFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			}
		});

	}

}
