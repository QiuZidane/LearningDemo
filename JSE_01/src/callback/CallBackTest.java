package callback;

import java.util.concurrent.TimeUnit;

public class CallBackTest implements ICallback {

	/**
	 * 具体回调方法的实现
	 */
	@Override
	public void exec() {

		System.out.println("调用了" + CallBackTest.class.getSimpleName() + "的exec方法");

	}

	public static void main(String[] args) {

		LongTimeTask lTask = new LongTimeTask(new CallBackTest());
		new Thread(lTask).start();
		System.out.println("123");

	}

}

/**
 * 
 * 定义回调方法接口
 *
 */
interface ICallback {

	public void exec();

}

class LongTimeTask implements Runnable {

	ICallback cbObj;

	public LongTimeTask(ICallback cbObj) {

		this.cbObj = cbObj;

	}

	@Override
	public void run() {
		try {
			long current = System.currentTimeMillis();
			System.out.println("开始时间=" + current);
			TimeUnit.SECONDS.sleep(2);
			System.out.println("结束时间=" + (System.currentTimeMillis() - current));
			
			// 长耗时任务完成后，调用回调方法
			cbObj.exec();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
