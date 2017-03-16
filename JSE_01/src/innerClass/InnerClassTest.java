package innerClass;

import javax.swing.JOptionPane;

public class InnerClassTest {

	public static void main(String[] args) {

		TalkingClock clock = new TalkingClock(1000, true);
		// 普通内部类
		// clock.start();
		// 局部内部类
		clock.start_inner(2000, true);
		clock.start_anonymous_inner(3000, true);
		
		JOptionPane.showMessageDialog(null, "Quit？");
		System.exit(0);
		
		Outer outer = new Outer();
		/**
		 * 必须现有外部类对象才能生成内部类对象。<br>
		 * 因为内部类需要访问外部类中的成员变量，成员变量必须先实例化
		 */
		Outer.inner inner = outer.new inner(); 
		inner.setInt();
		System.out.println(inner.innerInt);
		
		
		

	}

}
