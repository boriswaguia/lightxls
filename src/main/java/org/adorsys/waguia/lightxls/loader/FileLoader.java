package org.adorsys.waguia.lightxls.loader;

import org.apache.poi.ss.usermodel.Workbook;

public interface FileLoader {
	public Workbook LoadFile(String file);
}
