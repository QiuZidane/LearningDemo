package thread;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import io.*;

/**
 * 打印出现keyword的行
 * 
 * @author EvaZis
 *
 */
public class ThreadPoolTest2 {

	public static void main(String[] args) throws IOException {

		File dir = new File("/Users/EvaZis/Documents/NewBird^^/Missions");
		String keyword = "Delegate";
		
		StringBuilder totalContent = new StringBuilder();
		
		long starttime = System.currentTimeMillis();

		// 新建线程池
		ExecutorService pool = Executors.newCachedThreadPool();

		// 新建线程任务，开始查找
		CountFileByPool cf = new CountFileByPool(dir, keyword, pool);
		Future<ArrayList<String>> result = pool.submit(cf);
		

		try {
			ArrayList<String> content = result.get();
			for (String line : content) {
				// System.out.println(line);
				// 拼装字符串
				totalContent.append(line+"\r");
			}
			FileTest1.getFileHander().writeStrToFile_buffer(totalContent.toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		pool.shutdown();

		long endtime = System.currentTimeMillis();
		System.out.println("cast time = " + (endtime - starttime));

	}

}

class CountFileByPool implements Callable<ArrayList<String>> {

	private File _dir;
	private String _keyword;
	private ExecutorService _pool;
	private ArrayList<String> content; // 存放当次线程中含有关键字的行，每行占一个元素位置

	public CountFileByPool(File dir, String keyword, ExecutorService pool) {
		_dir = dir;
		_keyword = keyword;
		_pool = pool;
	}

	public ArrayList<String> call() {

		List<Future<ArrayList<String>>> results = new ArrayList<>(); // 存放当次线程运行的Future结果
		content = new ArrayList<>();

		// 列出所有目录/文件，逐一处理
		File[] files = _dir.listFiles();
		try {
			for (File file : files) {
				if (file.isDirectory()) {

					// 如果是目录，则将其放入线程池内进行统计
					CountFileByPool countFileByPool = new CountFileByPool(file, _keyword, _pool);
					Future<ArrayList<String>> result = _pool.submit(countFileByPool);
					results.add(result); // 将结果加入到list中

				} else {

					// 如果是文件，则查找并将结果加到content
					ArrayList<String> aList = searchKeyword(file);
					content.addAll(aList);

				}
			}
		} catch (Exception e) {
			System.err.println("...");
			e.printStackTrace();
		}

		// 统计所有的task
		for (Future<ArrayList<String>> result : results) {

			try {
				content.addAll(result.get()); // get时如果计算结果没出来,会阻塞,直到计算完成
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		}

		return content;
	}

	private ArrayList<String> searchKeyword(File file) {

		ArrayList<String> content = new ArrayList<>();
		int lineNumber = 0;

		try {
			try (Scanner in = new Scanner(file)) {

				while (in.hasNextLine()) {
					String line = in.nextLine();
					lineNumber++;
					if (line.contains(_keyword)) {
						String element = file.getName() + " line:" + lineNumber + " >>>" + line;
						content.add(element);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return content;

	}

}
