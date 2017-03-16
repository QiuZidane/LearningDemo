package thread;


public class RunnableDemo implements Runnable {

	private Thread threadObj1;
	private String threadName;

	public RunnableDemo(String name) {
		threadName = name;
		System.out.println("Createing : " + threadName);
	}

	@Override
	public void run() {
		System.out.println("Running : " + threadName);
		try {
			
			for (int i = 4; i > 0; i--) {
				System.out.println("Thread: " + threadName + "," + i);
				Thread.sleep(1000);

				if (Thread.currentThread().isInterrupted()) {
					System.out
							.println("Thread.currentThread=" + Thread.currentThread().toString() + " is Interrupted ");
				} else {
					System.out.println(
							"Thread.currentThread=" + Thread.currentThread().toString() + " isn't Interrupted ");
				}

				// 获取线程状态
				 System.out.println("Thread.State=" + Thread.currentThread().getState().toString());
				
				
				// 请求停止线程
				Thread.currentThread().interrupt();

				if (Thread.currentThread().isInterrupted()) {
					System.out
							.println("Thread.currentThread=" + Thread.currentThread().toString() + " is Interrupted ");
				} else {
					System.out.println(
							"Thread.currentThread=" + Thread.currentThread().toString() + " isn't Interrupted ");
				}

			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (threadObj1 == null) {
			threadObj1 = new Thread(this, threadName);
			threadObj1.start();
		}
	}

}
