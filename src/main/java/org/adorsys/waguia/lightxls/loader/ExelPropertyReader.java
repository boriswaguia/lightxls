package org.adorsys.waguia.lightxls.loader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import junit.framework.Assert;

import org.adorsys.waguia.lightxls.core.EasyPropertyReader;
import org.apache.poi.ss.usermodel.Cell;

public class ExelPropertyReader implements EasyPropertyReader {
	private Field field ;
	private Class<?> type;
	private Object newInstance;
	private Cell cell;
	private Method method;
	public ExelPropertyReader() {
	}
	
	public ExelPropertyReader(Field field, Class<?> type, Object newInstance,
			Cell cell, Method method) {
		super();
		Assert.assertNotNull("The Field must not be null", field);
		Assert.assertNotNull("The New Instance Must not be null", newInstance);
		Assert.assertNotNull("The Cell must not be null", cell);
		this.field = field;
		if(type == null){
			this.type = field.getType();
		}else{
			this.type = type;
		}
		this.newInstance = newInstance;
		this.cell = cell;
		this.method = method ;
	}

	public void readProperty() {
		try {
			doRead();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void doRead() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(type.equals(String.class) ) {
			method.invoke(newInstance, cell.getStringCellValue());
		}else if (type.equals(Integer.TYPE)){
			method.invoke(newInstance, (int)cell.getNumericCellValue());
		}else if (type.equals(Integer.class)){
			method.invoke(newInstance, new Integer((int)cell.getNumericCellValue()));
		}else if (type.equals(Long.TYPE)){
			method.invoke(newInstance, (long)cell.getNumericCellValue());
		}else if (type.equals(Long.class)){
			method.invoke(newInstance, new Long((long)cell.getNumericCellValue()));
		}else if (type.equals(Double.TYPE)){
			method.invoke(newInstance, cell.getNumericCellValue());
		}else if (type.equals(Double.class)){
			method.invoke(newInstance, new Double(cell.getNumericCellValue()));
		}else if (type.equals(Boolean.TYPE)){
			method.invoke(newInstance, cell.getBooleanCellValue());
		}else if(type.equals(Boolean.class)){
			method.invoke(newInstance, new Boolean(cell.getBooleanCellValue()));
		}else if (type.equals(BigDecimal.class)){
			method.invoke(newInstance, new BigDecimal(cell.getNumericCellValue()));
		}else if (type.equals(BigInteger.class)){
			//cast the double into a String.
			method.invoke(newInstance, new BigInteger(""+cell.getNumericCellValue()));
		}else if (type.equals(Date.class)){
			method.invoke(newInstance, cell.getDateCellValue());
		}else{
			System.out.println("It is a reverenced field");
		}
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Object getNewInstance() {
		return newInstance;
	}

	public void setNewInstance(Object newInstance) {
		this.newInstance = newInstance;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
}
