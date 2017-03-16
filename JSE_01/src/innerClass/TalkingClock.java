package innerClass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.naming.InitialContext;
import javax.swing.Timer;

import org.omg.CORBA.PUBLIC_MEMBER;

public class TalkingClock {

	private int interval;
	private boolean beep;

	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}

	public void start() {
		ActionListener listener = new TimePrinter();
		Timer timer = new Timer(interval, listener);
		timer.start();

	}

	// 可以设置为private
	// 如果设置为private，则不能通过TalkingClock.TimePrinter引用这个内部类
	private class TimePrinter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Date now = new Date();
			System.out.println("At the tone, the time is " + now);
			if (beep) { // 或者 TalkingClock.this.beep
				Toolkit.getDefaultToolkit().beep();
			}

		}
	}
	
	/**
	 * 
	 * @author EvaZis
	 * 静态内部类，如果不考虑引用外围类的变量，就定义为静态内部类
	 *
	 */
	static class TimePrinter_static implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Date now = new Date();
			System.out.println("At the tone, the time is " + now);
			Toolkit.getDefaultToolkit().beep();
		}
	}

	/**
	 * 局部内部类 <br>
	 * 方法变量声明为final的原因是使局部变量与局部类内部建立的拷贝一致--安全考虑？ 感觉不final也一致的<br>
	 * 局部内部类只能声明为class / abstract / final
	 */
	public void start_inner(final int interval1, final boolean beep1) {

		class TimePrinter implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				System.out.println("inner start:" + now + " interval = " + interval1);
				if (beep1) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		}

		ActionListener l = new TimePrinter();
		Timer t = new Timer(interval1, l);
		t.start();

	}

	/**
	 * 匿名内部类
	 * <p>
	 * 实现了某个接口的类 只创建了这个类的一个对象，就不必专门写个类了(当然写了会清晰点)<br>
	 * 可以是实现一个接口或者扩展一个类<br>
	 * 
	 */
	public void start_anonymous_inner(final int interval1, final boolean beep1) {

		ActionListener l = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				System.out.println("inner start:" + now + " interval = " + interval1);
				if (beep1) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		};

		Timer timer = new Timer(interval1, l);
		timer.start();

	}

}
