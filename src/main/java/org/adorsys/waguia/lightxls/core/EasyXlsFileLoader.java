package org.adorsys.waguia.lightxls.core;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;

public interface EasyXlsFileLoader{
	public Workbook loadXlsFile(String filePath);
	public Workbook loadXlsFile(InputStream inputStream);
}
