package thread;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest2 {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Enter base directory: (e.g. /usr/local/)");
		Scanner in = new Scanner(System.in);
		String dirPath = in.nextLine();
		System.out.println("Enter keyword");
		String keyword = in.nextLine();

		BlockingQueue<File> queue = new ArrayBlockingQueue<>(10);
		EnumFiles task1 = new EnumFiles(new File(dirPath), queue);
		new Thread(task1).start();

		for (int thread = 0; thread < 100; thread++) {
			SearchFile task2 = new SearchFile(keyword, queue);
			new Thread(task2).start();
		}

	}

}

/**
 * 
 * 生产者类，遍历目录下所有的文件
 * 
 * @params dir、ArrayBlockingQueue
 * @author EvaZis
 *
 */
class EnumFiles implements Runnable {

	private File _dir;
	private BlockingQueue<File> _queue; // ----这个类型跟原例子是否相同？
	public static final File ENDFLAG = new File("");

	// 构造方法
	public EnumFiles(File dir, BlockingQueue<File> queue) {
		_dir = dir;
		_queue = queue;

	}

	/**
	 * 获取该目录下所有文件 <br>
	 * 判断file是否目录 <br>
	 * 如果是则再new一个EnumFiles类来继续判断 <br>
	 * 如果不是则将file加入到阻塞队列 <br>
	 * 
	 */
	@Override
	public void run() {

		try {
			Enumerate(_dir);
			_queue.put(ENDFLAG);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// 遍历文件
	private void Enumerate(File dir) throws InterruptedException {

		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				Enumerate(file);
			} else {
				_queue.put(file);
				System.out.println("putting file:" + file);
			}
		}
	}

}

class SearchFile implements Runnable {

	private String _keyword;
	private BlockingQueue<File> _queue;
	private int count;

	public SearchFile(String keyword, BlockingQueue<File> queue) {
		_keyword = keyword;
		_queue = queue;
		count = 0;
	}

	/**
	 * 从队列取出文件<br>
	 * 进行关键字检查
	 */
	@Override
	public void run() {

		try {

			boolean done = false;
			while (!done) {
				File file = _queue.take();
				if (file == EnumFiles.ENDFLAG) {
					_queue.put(EnumFiles.ENDFLAG);
					// System.out.printf("一共找到%s个文件 ", count);
					done = true;
				} else {
					searchByKeyword(file);
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void searchByKeyword(File file) {


		try (Scanner in = new Scanner(file)) {
			while (in.hasNextLine()) {

				String line = in.nextLine();
				if (line.toUpperCase().contains(_keyword)) {
					System.out.println(line);					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
