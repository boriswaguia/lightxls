/**
 * Copyright (C) 2012 Waguia W. Boris boris.waguia@adorsys.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.adorsys.waguia.lightxls.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.adorsys.waguia.lightxls.core.EasyXlsFileLoader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * 
 * @author w2b
 *
 */
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
