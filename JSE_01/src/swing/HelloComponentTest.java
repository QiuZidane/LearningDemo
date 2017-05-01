package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class HelloComponentTest {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame jf = new HelloFrame();
				jf.setTitle("HelloWorld");
				jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				jf.setVisible(true);

			}
		});

	}

}

class HelloFrame extends JFrame {

	public HelloFrame() {

		// setSize(500, 400);

		HelloWComponent hWComponent = new HelloWComponent();
		// hWComponent.setBackground(Color.BLUE);

		add(hWComponent);

		pack();

	}

}

class HelloWComponent extends JPanel {

	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public HelloWComponent() {
		setBackground(Color.BLUE); // 看不到效果，因为被String遮住了
	}

	@Override
	public void paintComponent(Graphics g) {

		g.drawString("Hello", MESSAGE_X, MESSAGE_Y);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

}