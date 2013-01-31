package com.anyreader.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInfo {
	
	private String path;
	private String fileName;
	private String pathName;	// path + filename
	private String text = "";
	private long createTime;
	private int fileSize;
	private boolean isDirectory;
	
	public FileInfo(String pathName) {
		getFileInfo(pathName);
		setTextFromFile(pathName);
	}
	
	public void getFileInfo(String pathName){
		File f = new File(pathName);
		if(f.isDirectory()) {
			setFileName(f.getName());
			setPath(f.getParent());
			pathName = f.getAbsolutePath();
			setCreateTime(f.lastModified());
			setFileSize((int)f.length());
		}
		else {
			setFileName(f.getName());
			setPath(f.getParent());
			pathName = f.getAbsolutePath();
			setDirectory(true);
		}
	}
	
	public void setTextFromFile(String pathName) {
		try {
			File f = new File(pathName);
			Scanner sc = new Scanner(f, "UTF-8");
			while(sc.hasNext() == true) {
				text += sc.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getText() {
		return text;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
