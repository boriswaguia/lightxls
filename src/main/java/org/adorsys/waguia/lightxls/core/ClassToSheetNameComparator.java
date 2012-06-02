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
package org.adorsys.waguia.lightxls.core;

import java.util.Locale;
/**
 * 
 * @author w2b
 *
 */
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
