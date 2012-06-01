package org.adorsys.waguia.lightxls.core;

import java.util.Locale;

public class ClassToSheetNameComparator implements EasyComparator<String> {

	public int compare(String clazzName, String sheetName) {
		if(clazzName == null || sheetName == null) throw new NullPointerException();
		if(clazzName.equals(sheetName)) return 0;
		clazzName = clazzName.toLowerCase(Locale.ENGLISH);
		sheetName = sheetName.toLowerCase(Locale.ENGLISH);
		if(sheetName.contains(clazzName)) return 0;
		return 2;
	}

}
