package io.renren.multi_thread;

import java.io.File;

public class TestThreadCopy {

	static ThreadPool pool= new ThreadPool();
	public static void search(File file, String search) {

		if (file.isFile()) {
			if(file.getName().toLowerCase().endsWith(".java")){
				SearchFileTask task = new SearchFileTask(file, search);
				pool.add(task);
			}
		}
		if (file.isDirectory()) {
			File[] fs = file.listFiles();
			for (File f : fs) {
				search(f, search);
			}
		}
	}

	public static void main(String[] args) {
		File folder =new File("D:\\project");
		search(folder,"Magic");
	}



}