package org.adorsys.waguia.lightxls.loader;

import org.adorsys.waguia.lightxls.core.EasyComparator;
import org.apache.commons.lang3.StringUtils;

public class FieldToColumnComparator implements EasyComparator<String> {

	public int compare(String argOne, String argTwo) {
		if(StringUtils.isBlank(argOne) || StringUtils.isBlank(argTwo))return -1;
		StringUtils.trim(argOne);
		StringUtils.trim(argTwo);
		if(argOne.equalsIgnoreCase(argTwo)) return 0;
		if(argOne.startsWith(argTwo) || argTwo.startsWith(argOne)) return 0;
		return -1;
	}

	

}
