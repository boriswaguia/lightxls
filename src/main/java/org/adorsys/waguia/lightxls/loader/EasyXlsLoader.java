package org.adorsys.waguia.lightxls.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.adorsys.waguia.lightxls.core.EasyXlsFileLoader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class EasyXlsLoader implements EasyXlsFileLoader{
	private Workbook workbook;

	public Workbook loadXlsFile(String filePath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return loadXlsFile(inputStream);
	}
	public Workbook loadXlsFile(InputStream inputStream) {
		if(inputStream == null )throw new NullPointerException("");
		try {
			workbook = new HSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}
	public Workbook getWorkbook() {
		if(workbook == null )throw new NullPointerException("No workbook loaded. Try to load one before continue");
		return workbook;
	}
	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
}
