package io.renren.multi_thread;

import java.io.File;
import java.io.FileReader;

public class SearchFileTask implements Runnable{

	private File file;
	private String search;
	public SearchFileTask(File file,String search){
		this.file = file;
		this.search = search;
	}

	@Override
	public void run() {
		String content = searchContent(file);
		if (content.contains(search)) {
			System.out.println("线程："+Thread.currentThread().getName()+"找到目标字符串"+search+"在文件:"+file);
		}


	}

	public String searchContent(File file) {
		try {
			FileReader fr = new FileReader(file);
			char[] all = new char[(int)file.length()];
			fr.read(all);
			return new String(all);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}