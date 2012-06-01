package org.adorsys.waguia.lightxls.loader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.adorsys.waguia.lightxls.core.EasyMatching;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class SheetColumnToClazzFieldMatching implements EasyMatching {
	private String[] sheetColumns ;
	private Field[] clazzFields;
	private Method[] clazzMethods;
	private int numberOfFieldUnmatching ;
	
	public SheetColumnToClazzFieldMatching() {
		super();
	}

	public SheetColumnToClazzFieldMatching(String[] sheetColumns,
			Field[] clazzFields,Method[] methods) {
		super();
		this.sheetColumns = sheetColumns;
		this.clazzFields = clazzFields;
	}
	
	/**
	 * I will check if columns name match to fields, and also validate getters/setters methods for properties.
	 */
	public boolean checkMatching() {
		boolean result = true ;
		if(this.sheetColumns == null || this.clazzFields == null || this.clazzMethods == null){
			throw new RuntimeException("Null value aren't required");
		}
		String [] tempArray = new String[clazzFields.length];//init the array.
		String[] fieldNames = fieldNames(clazzFields);
		//find matching ColumnName-FieldName and save them in a tempArray.
		for (int i = 0; i < this.sheetColumns.length; i++) {
			String sheetColumn = this.sheetColumns[i];
			for (int j = 0; j < fieldNames.length; j++) {
				String fieldName = fieldNames[j];
				if(sheetColumn.equals(fieldName) ){
					tempArray[j] = fieldName;//store matching fieldName/columnName in the array.
					break ;
				}
			}
		}
		System.out.println("Array contains "+tempArray[3]);
		fieldNames = tempArray ;
		if(clazzMethods.length < fieldNames.length) result = false;
		System.out.println("result : \t"+result);
		//TODO : Find spring's bean validator For getters/setters
		//Here I check if There are appropriate setter for founded fields.
		for (int i = 0; i < fieldNames.length; i++) {
			String fieldName = fieldNames[i];
			if(fieldName == null ) continue ;//check null value in the array.
			String setterMethodName = "set"+StringUtils.capitalize(fieldName);
			boolean methodFound = false;
			for (int j = 0; j < clazzMethods.length; j++) {
				Method method = clazzMethods[j];
				if(method.getName().equals(setterMethodName)) {
					methodFound = true;
					break ;
				}
			}
			System.out.println("Method Found \t "+methodFound);
			if(methodFound == false) result = false ;
		}
		return result;
	}
	
	private String[] fieldNames(Field[] fields){
		if(fields == null )throw new NullPointerException("Unable to transfrom fields properties to array of String.");
		List<String> arrayList = new ArrayList<String>();
		for (Field field : fields) {
			arrayList.add(field.getName());
		}
		return (String[]) arrayList.toArray(new String[arrayList.size()]);
	}

	public boolean checkMatching(String[] columnNames, Field[] fields,
			Method[] methods) {
		this.sheetColumns = columnNames;
		this.clazzMethods = methods;
		this.clazzFields = fields;
		return checkMatching();
	}
	
}

