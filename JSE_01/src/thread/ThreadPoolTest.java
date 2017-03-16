package thread;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.*;

public class ThreadPoolTest {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory (e.g. /usr/local/)");
		String directory = in.nextLine();
		System.out.println("Enter keyword:\n");
		String keyword = in.nextLine();
		
		long startTime =  System.currentTimeMillis();
		ExecutorService pool = Executors.newCachedThreadPool();
		MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
		Future<Integer> result = pool.submit(counter);

		try {
			System.out.println(result.get() + "matching files");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		pool.shutdown();//用完要关闭pool
		int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
		System.out.println("largest pool size=" + largestPoolSize);
		
		long endTime =  System.currentTimeMillis();
		long interval = endTime-startTime;
		System.out.println("Running time = "+interval);
		

	}

}

/**
 * This task counts the files in a directory and its subdirectories that contain
 * a given keyword
 * 
 * @author QZidane
 *
 */
class MatchCounter implements Callable<Integer> {

	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;

	/**
	 * constructs a MatchCounter
	 * 
	 * @param dir
	 * @param key
	 * @param pool
	 */
	public MatchCounter(File dir, String key, ExecutorService pool) {

		this.directory = dir;
		this.keyword = key;
		this.pool = pool;
	}

	@Override
	public Integer call() {
		count = 0;
		try {

			File[] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList<>();

			// 遍历所有文件/文件夹
			for (File file : files) {
				if (file.isDirectory()) {
					MatchCounter counter = new MatchCounter(file, keyword, pool);
					// 将MatchCounter实例submit到pool，返回future结果
					Future<Integer> result = pool.submit(counter);
					// 将结果放入list
					results.add(result);
				} else {
					System.out.println("searching file:"+file.toString());
					if (search(file)) {
						count++;
					}
				}
			}
			for (Future<Integer> result : results) {
				try {
					count += result.get();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return count;
	}

	/**
	 * Searches a file for a given keyword
	 * 
	 * @param file
	 * @return 思路： 1.使用Scanner获取输入的字符流file 2.逐行判断是否含有关键字，含有则返回true，
	 *         3.所有行都没有含有，返回false
	 * 
	 */
	public boolean search(File file) {

		try {
			try (Scanner in = new Scanner(file)) {
				boolean foundKeyword = false;
				while (!foundKeyword && in.hasNextLine()) {
					String line = in.nextLine();
					if (line.contains(this.keyword)) {
						foundKeyword = true;
					}
				}
				return foundKeyword;
			}
		} catch (Exception e) {
			return false;
		}

	}

}
