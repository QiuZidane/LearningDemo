package swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class SwingTest {

	public void createFrame(String title) {
		// 总载体容器
		JFrame jFrame = new JFrame(title);
		Container container = jFrame.getContentPane();
		JLabel jLabel = new JLabel("哈哈哈，这是一个label");
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jFrame.add(jLabel);
		container.setBackground(Color.white);

		jFrame.setSize(200, 200);

		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jFrame.setVisible(true);

	}
	
	public void about_model(){
		JButton jButton = new JButton("按钮1");
		ButtonModel model = jButton.getModel();
		System.out.println(model.isEnabled());
	}

	/**
	 * 
	 * main方法退出只是终止主线程，事件分派线程保持程序处于激活状态 <br>
	 * 直到关闭框架或调用System.exit方法终止程序 <br>
	 * 
	 */
	public static void main(String[] args) {

		// 关闭时，即使多个窗口都同时关闭了。
		// 初步分析是因为关闭按钮的监听都是同一个事件。
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				new SwingTest().createFrame("Frame1");
				new SwingTest().about_model();

			}
		});

		// EventQueue.invokeLater(new Runnable() {
		//
		// @Override
		// public void run() {
		//
		// SimpleFrame frame = new SimpleFrame("CJ EXAMPLE");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		// // frame.setUndecorated(false); // 关闭该frame的所有装饰
		//
		// frame.setResizable(false);
		//
		// frame.about_size();
		//
		// frame.about_icon();
		//
		// //frame.setExtendedState(Frame.ICONIFIED); // 最小化
		//
		// frame.setVisible(true);
		//
		// try {
		// TimeUnit.SECONDS.sleep(2);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// frame.repaint();
		//
		//
		// }
		// });

		// System.out.println("1234");
		// System.exit(123);
		
		
	}

}

class SimpleFrame extends JFrame {

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;

	public SimpleFrame(String title) {

		setTitle(title);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationByPlatform(true);

	}

	public void about_size() {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();

		int height = (int) dimension.getHeight();
		int width = (int) dimension.getWidth();

		System.out.println("getSize()=" + dimension.getSize());
		System.out.println("getHeight()=" + height);
		System.out.println("getWidth()=" + width);

		setSize(width / 2, height / 2);
		setLocation(width / 4, height / 4); // 移动到某个位置

	}

	public void about_icon() {
		Image image = new ImageIcon("star.png").getImage();
		setIconImage(image);
	}
	

}

class HelloComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
	}

}
