package swing;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

public class FontTest {

	// 获得系统支持的字体
	public static void listFonts() {
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
		System.out.println(Arrays.toString(fontNames));
	}

	public static void main(String[] args) {

		// FontTest.listFonts();

		Font sansbold14 = new Font("abc", Font.BOLD, 14);

	}

}
