package delegate;

import java.lang.reflect.Method;

/**
 * 
 * 定义了一个事件类
 *
 */
public class Event {

	// 要执行方法的对象
	private Object object;

	// 要执行的方法名称
	private String methodName;

	// 要执行方法的参数
	private Object[] params;

	// 要执行方法的参数类型
	private Class<?>[] paramTypes;

	public Event() {}

	public Event(Object object, String methodName, Object... args) {
		this.object = object;
		this.methodName = methodName;
		this.params = args;
		contractParamTypes(this.params);

	}

	// 根据参数数组生成参数类型数组
	private void contractParamTypes(Object[] params) {

		this.paramTypes = new Class<?>[params.length];
		for (int i = 0; i < params.length; i++) {
			this.paramTypes[i] = params[i].getClass();
		}
	}
	
	
	public void invoke() throws Exception {
		
		try {
			Method method = object.getClass().getMethod(this.methodName, this.paramTypes);
			if (null==method) {
				return;
			}
			method.invoke(this.object, this.params);
		} catch (Exception e) {
			System.err.println("无此方法:"+this.methodName);
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
}
