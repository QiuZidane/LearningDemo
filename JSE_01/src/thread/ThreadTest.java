package thread;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {

		RunnableDemo runnableDemo1 = new RunnableDemo("Thread-1");
		// RunnableDemo runnableDemo2 = new RunnableDemo("Thread-2");
		runnableDemo1.start();
		// runnableDemo2.start();

		// ThreadDemo threadDemo1 = new ThreadDemo("Thread----");
		// ThreadDemo threadDemo2 = new ThreadDemo("Thread++++");
		// threadDemo1.setPriority(1);
		// threadDemo2.setPriority(10);
		// threadDemo1.setName("td1");

		// threadDemo1.start();
		// threadDemo2.start();

		// threadDemo1.join(1000);
		// threadDemo1.interrupt();
		// System.out.println(threadDemo1.isAlive());
		// System.out.println(threadDemo1.currentThread());
		// threadDemo1.dumpStack();
		// threadDemo1.run();
		// threadDemo1.start();

	}

}
