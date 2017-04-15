package delegate;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 若干Event类的载体，同时提供一个执行所有Event的方法
 * 
 * @author QZidane
 *
 */
public class EventHandler {

	private List<Event> objects;

	public EventHandler() {
		objects = new ArrayList<Event>();
	}

	// 添加某个对象要执行的事件, 以及需要的参数
	public void addEvent(Object object, String methodName, Object... args) {
		objects.add(new Event(object, methodName, args));
	}

	// 通知所有对象执行指定的事件
	public void notifyX() throws Exception {
		for (Event e : objects) {
			e.invoke();
		}
	}

}
