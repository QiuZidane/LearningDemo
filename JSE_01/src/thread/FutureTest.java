package thread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory (e.g. c:\\java):");
		String directory = in.nextLine();
		System.out.println("Enter keyword (e.g. volatile):");
		String keyword = in.nextLine();
		
		long startTime =  System.currentTimeMillis();
		
		MathCounter counter = new MathCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<>(counter);
		new Thread(task).start();
		
		try {
			System.out.println(task.get()+" matching files");  // get时如果计算结果没出来，会阻塞
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime =  System.currentTimeMillis();
		long interval = endTime-startTime;
		System.out.println("Running time = "+interval);

	}

}

/**
 * This task counts the files in a directory and its subdirectory that contain a
 * given keyword
 * 
 * @author QZidane
 *
 */
class MathCounter implements Callable<Integer> {

	private File directory;
	private String keyword;
//	private int count;

	/**
	 * Constucts a MathCounter
	 */
	public MathCounter(File directory, String keyword) {
		this.directory = directory;
		this.keyword = keyword;
	}

	@Override
	public Integer call() {
		int count = 0;
		try {
			File[] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList<>();

			for (File file : files) {
				if (file.isDirectory()) {
					MathCounter counter = new MathCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<>(counter);  // 将Callable转换成Future和Runnable
					results.add(task);
					new Thread(task).start();

				} else {

					if (search(file)) {
						count++;
					}
				}

			}

			for (Future<Integer> result : results) {
				try {
					count += result.get(); // 调用时会阻塞，直到计算完成
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count; // get的咚咚
	}

	/**
	 * Search a file for a given keyword
	 * 
	 * @param file
	 * @return
	 */
	public boolean search(File file) {

		try {
			try (Scanner in = new Scanner(file)) {
				boolean found = false;
				while (!found && in.hasNextLine()) {
					String line = in.nextLine();
					if (line.contains(keyword)) {
						found = true;
					}
				}
				return found;

			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
}