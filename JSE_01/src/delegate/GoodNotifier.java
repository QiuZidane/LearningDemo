package delegate;

public class GoodNotifier extends Notifier {

	@Override
	public void addListener(Object object, String methodName, Object... args) {
		System.out.println("有新的委托申请");
		this.getEventHandler().addEvent(object, methodName, args);
		
	}

	@Override
	public void notifyX() {
		System.out.println("通知所有同学");
		try {
			this.getEventHandler().notifyX();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
