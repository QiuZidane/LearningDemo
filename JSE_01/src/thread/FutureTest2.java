package thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 打印出现keyword的行
 * 
 * @author EvaZis
 *
 */
public class FutureTest2 {

	public static void main(String[] args) {

		File dir = new File("/Users/EvaZis/Documents/NewBird^^/Missions");
		String keyword = "Delegate";
		long starttime = System.currentTimeMillis();

		// 新建线程任务，开始查找
		CountFiles cf = new CountFiles(dir, keyword);
		FutureTask<ArrayList<String>> task = new FutureTask<>(cf);
		new Thread(task).start();

		try {
			ArrayList<String> content = task.get();
			for (String line : content) {
				System.out.println(line);
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		long endtime = System.currentTimeMillis();
		System.out.println("cast time = " + (endtime - starttime));

	}

}

class CountFiles implements Callable<ArrayList<String>> {

	private File _dir;
	private String _keyword;
	private ArrayList<String> content; // 存放当次线程中含有关键字的行，每行占一个元素位置

	public CountFiles(File dir, String keyword) {
		_dir = dir;
		_keyword = keyword;
	}

	public ArrayList<String> call() {

		List<Future<ArrayList<String>>> results = new ArrayList<>(); // 存放当次线程运行的Future结果
		content = new ArrayList<>();

		// 列出所有目录/文件，逐一处理
		File[] files = _dir.listFiles();
		try {
			for (File file : files) {
				if (file.isDirectory()) {

					// 如果是目录，则新开一个线程类来统计
					CountFiles cFiles = new CountFiles(file, _keyword);
					FutureTask<ArrayList<String>> task = new FutureTask<>(cFiles); // 将Callable转换成Future和Runnable
					results.add(task); // 将任务加入到list中
					new Thread(task).start(); // 启动任务

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

