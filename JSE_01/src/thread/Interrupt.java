package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Interrupt {

	private Thread PT;
	private Thread CT;

	public void test() {
		
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		
		ProductQueue pq = new ProductQueue(queue);
		ConsumeQueue cq = new ConsumeQueue(queue);

		PT = new Thread(pq);
		CT = new Thread(cq);

		PT.start();
		CT.start();

		try {
			
			

			TimeUnit.SECONDS.sleep(2);
			
			pq.PFlag=false;
			PT.interrupt();
			cq.CFlag=false;
			CT.interrupt();
//			TimeUnit.SECONDS.sleep(2);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		new Interrupt().test();    

	}

}

class ProductQueue implements Runnable {

	public boolean PFlag = true;
	private BlockingQueue<Integer> queue;

	public ProductQueue(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		
		int count=0;
		
		try {
			
			System.out.println("ProductQueue start");

			while (PFlag) {
				
				
				queue.put(count++);
				TimeUnit.SECONDS.sleep(1);

			}

		} catch (InterruptedException ie) {
			System.out.println("中断ProductQueue");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class ConsumeQueue implements Runnable {
	public boolean CFlag = true;
	private BlockingQueue<Integer> queue;

	public ConsumeQueue(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		
		int count;
		
		try {

			System.out.println("ConsumeQueue start");
			
			while (CFlag) {

				TimeUnit.SECONDS.sleep(1);
				
				count=queue.take();
//				count=queue.poll(1, TimeUnit.SECONDS);
				System.out.println("take:"+count);
				
				
//				System.out.println("CCCC");

			}

		} catch (InterruptedException ie) {
			System.out.println("中断ConsumeQueue");
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
