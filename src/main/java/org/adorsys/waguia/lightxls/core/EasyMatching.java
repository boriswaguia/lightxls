package org.adorsys.waguia.lightxls.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface EasyMatching {
	public boolean checkMatching();
	public boolean checkMatching(String[] columnNames,Field[] fields,Method[] methods);
}
