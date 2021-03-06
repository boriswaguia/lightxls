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
package org.adorsys.waguia.lightxls.generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.adorsys.waguia.lightxls.core.EasyClazzLoader;
import org.adorsys.waguia.lightxls.core.EasyComparator;
import org.adorsys.waguia.lightxls.core.EasyMatching;
import org.adorsys.waguia.lightxls.core.Finder;
import org.adorsys.waguia.lightxls.loader.ClassInSheetFinder;
import org.adorsys.waguia.lightxls.loader.ExelPropertyReader;
import org.adorsys.waguia.lightxls.loader.FieldToColumnComparator;
import org.adorsys.waguia.lightxls.loader.SheetColumnToClazzFieldMatching;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * 
 * @author w2b
 *
 * @param <T>
 */
public class EasyXlsClazzLoader<T> implements EasyClazzLoader<T> {
	public static int HEADER_INDEX = 0;
	private Workbook workbook;
	private Class clazz;
	private Finder classInSheetFinder;
	private EasyMatching sheetColumnToClazzFieldMatching;
	private EasyComparator fieldToColumnComparator;
	private ExelPropertyReader exelPropertyReader ;
	public EasyXlsClazzLoader(Workbook workbook, Class<T> clazz) {
		this.workbook = workbook;
		this.clazz = clazz;
	}

	public List<T> loadClazzData(Class clazz) {
		List<T> result = new ArrayList<T>();
		try {
			result = loadClazz();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<T> loadClazz() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		List<T> result = new ArrayList<T>();

		Field[] declaredFields = clazz.getDeclaredFields();
		int numberOfSheets = workbook.getNumberOfSheets();
		Method[] declaredMethods = clazz.getDeclaredMethods();
		if (classInSheetFinder == null)
			classInSheetFinder = new ClassInSheetFinder();
		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < numberOfSheets; i++) {
			sheetNames.add(workbook.getSheetAt(i).getSheetName());
		}
		int position = classInSheetFinder.find(clazz.getSimpleName(),(String[]) sheetNames.toArray(new String[sheetNames.size()]));
		if (position == -1)
			throw new RuntimeErrorException(null,
					"Unable to find the class's sheet");
		Sheet clazzSheet = workbook.getSheetAt(position);
		// assuming that the first row will contains class's properties. so this is
		// how to get columnNames.
		Row row = clazzSheet.getRow(HEADER_INDEX);
		Iterator<Cell> cellIterator = row.cellIterator();
		List<String> columnNames = new ArrayList<String>();
		while (cellIterator.hasNext()) {
			Cell cell = (Cell) cellIterator.next();
			columnNames.add(cell.getStringCellValue());
		}
		if (this.sheetColumnToClazzFieldMatching == null)
			this.sheetColumnToClazzFieldMatching = new SheetColumnToClazzFieldMatching();

		if (sheetColumnToClazzFieldMatching.checkMatching(
				(String[]) columnNames.toArray(new String[columnNames.size()]),
				declaredFields, declaredMethods) == false)
			throw new RuntimeException(
					"Matching Error. Please recheck matching rules");
		Iterator<Row> rowIterator = clazzSheet.rowIterator();
		if (fieldToColumnComparator == null)
			this.fieldToColumnComparator = new FieldToColumnComparator();
		int numberOfIteration = 0;
		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			Object newInstance = clazz.newInstance();
			if (numberOfIteration == HEADER_INDEX) {
				numberOfIteration++;
				continue;
			}
			for (int i = 0; i < declaredFields.length; i++) {
				Field field = declaredFields[i];
				if (!columnNames.contains(field.getName()))
					continue;
				String correspondinMethodName = "set"
						+ StringUtils.capitalize(field.getName());
				for (int j = 0; j < declaredMethods.length; j++) {
					Method method = declaredMethods[j];
					if (!method.getName().equals(correspondinMethodName)) continue ;
					int index = 0;
					//Find the correct field's range in the list of columns.
					for (String string : columnNames) {
						if (fieldToColumnComparator.compare(field.getName(),string) == 0) {
							Class<?> type = field.getType();
							if(exelPropertyReader == null){
								exelPropertyReader = new ExelPropertyReader(field, type, newInstance, nextRow.getCell(index), method);
								exelPropertyReader.readProperty();
							}else {
								exelPropertyReader.setField(field);
								exelPropertyReader.setCell(nextRow.getCell(index));
								exelPropertyReader.setMethod(method);
								exelPropertyReader.setNewInstance(newInstance);
								exelPropertyReader.setType(type);
								exelPropertyReader.readProperty();
							}
							index++;
							continue ;
						}
						index++;
					}
				}
			}
			result.add((T) newInstance);
			numberOfIteration ++;
		}
		return result;
	}
	public void checkTypeAndLoadData(Class<?> type,Object newInstance,Cell cell){
	}
	public Finder getClassInSheetFinder() {
		return classInSheetFinder;
	}

	public void setClassInSheetFinder(Finder classInSheetFinder) {
		this.classInSheetFinder = classInSheetFinder;
	}

	public EasyMatching getSheetColumnToClazzFieldMatching() {
		return sheetColumnToClazzFieldMatching;
	}

	public void setSheetColumnToClazzFieldMatching(
			EasyMatching sheetColumnToClazzFieldMatching) {
		this.sheetColumnToClazzFieldMatching = sheetColumnToClazzFieldMatching;
	}

	public EasyComparator getFieldToColumnComparator() {
		return fieldToColumnComparator;
	}

	public void setFieldToColumnComparator(
			EasyComparator fieldToColumnComparator) {
		this.fieldToColumnComparator = fieldToColumnComparator;
	}

	public ExelPropertyReader getExelPropertyReader() {
		return exelPropertyReader;
	}

	public void setExelPropertyReader(ExelPropertyReader exelPropertyReader) {
		this.exelPropertyReader = exelPropertyReader;
	}
	
}
