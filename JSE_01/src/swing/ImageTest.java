package swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ImageTest {

	public static void main(String[] args) {

		JFrame jFrame = new ImageFrame();
		jFrame.setTitle("图片测试");
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

	}

}

class ImageFrame extends JFrame {
	public ImageFrame() {
		add(new ImageComponent());
		pack();
	}
}

class ImageComponent extends JComponent {

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	private Image image;

	public ImageComponent() {		
		image = new ImageIcon("star.png").getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (image == null) {
			return;
		}

		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);

		g.drawImage(image, 0, 0, null);

	}

	@Override
	public Dimension getPreferredSize() {
		// return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		return new Dimension(image.getWidth(this), image.getHeight(this));
	}

}
