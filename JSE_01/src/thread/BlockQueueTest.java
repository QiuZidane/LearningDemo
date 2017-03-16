package thread;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {

	public static void main(String[] args) throws InterruptedException {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory (e.g. /usr/local/)");
		String directory = in.nextLine();
		System.out.println("Enter keyword (e.g. volatile):\n");
		String keyword = in.nextLine();

		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 100;

		BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
		FileEnumerationTask enumerationTask = new FileEnumerationTask(queue, new File(directory));
		new Thread(enumerationTask).start();
		// 开启100个线程执行查找
		for (int i = 1; i <= SEARCH_THREADS; i++) {
			new Thread(new SearchTask(queue, keyword)).start();
		}

	}

}

/**
 * this task enumerates all files in a directory and its subdirectories
 * 
 * @author EvaZis
 *
 */
class FileEnumerationTask implements Runnable {

	public static File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startingDirectory;

	/**
	 * 构造函数
	 */
	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}

	@Override
	public void run() {
		try {
			enumerate(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 遍历目录，将所有文件放入BlockingQueue
	 * 
	 * @param directory
	 * @throws InterruptedException
	 */
	public void enumerate(File directory) throws InterruptedException {
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				enumerate(file);
			} else {
				// System.out.println("put " + file.toString() + " into queue");
				queue.put(file); // throws InterruptedException
			}
		}
	}
}

/**
 * this task searches files for a given keyword.
 */
class SearchTask implements Runnable {
	private BlockingQueue<File> queue;
	private String keyword;

	/**
	 * Constructs a SearchTask
	 */
	public SearchTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}

	@Override
	public void run() {
		try {
			boolean done = false;
			while (!done) {
				File file = queue.take(); // 取出队列中的头元素
				if (file == FileEnumerationTask.DUMMY) {
					queue.put(file); // 这里要放回去，不然其他线程会取不到DUMMY
					done = true;
					// System.exit(0); // 这里不能退出，因为有可能其他线程还在查找
				} else {
					search(file);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * searches a file for a given keyword and prints all matching lines.
	 * 
	 * @param file
	 */
	public void search(File file) {
		try (Scanner in = new Scanner(file)) {
			int lineNumber = 0;
			while (in.hasNextLine()) {
				lineNumber++;
				String line = in.nextLine();
				if (line.contains(keyword)) {
					System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
