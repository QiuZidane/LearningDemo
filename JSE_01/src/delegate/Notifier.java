package delegate;

/**
 * 
 * 放哨学生的抽象类<br>
 * 他们需要有两个功能：1,增加需要通知的学生 2,通知所有注册的学生(老师来了)
 * 
 * @author QZidane
 *
 */
public abstract class Notifier {

	private EventHandler eventHandler = new EventHandler();

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	// 增加需要通知的学生
	public abstract void addListener(Object object, String methodName, Object... args);
	
	// 通知所有注册的学生
	public abstract void notifyX();

}
