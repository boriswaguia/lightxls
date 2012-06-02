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

import org.adorsys.waguia.lightxls.core.ClassToSheetNameComparator;
import org.adorsys.waguia.lightxls.core.EasyComparator;
import org.adorsys.waguia.lightxls.core.Finder;
/**
 * 
 * @author w2b
 *
 */
public class ClassInSheetFinder implements Finder<String> {
	private EasyComparator classToSheetNameComparator ;
	public ClassInSheetFinder() {
	}
	public ClassInSheetFinder(EasyComparator<String> classToSheetNameComparator) {
		this.classToSheetNameComparator = classToSheetNameComparator;
	}
	
	public int find(String whatToFind, String... whereToFind) {
		if(whatToFind == null || whereToFind == null) throw new NullPointerException() ;
		if(this.classToSheetNameComparator == null )this.classToSheetNameComparator = new ClassToSheetNameComparator();
		for (int i = 0; i < whereToFind.length; i++) {
			if(classToSheetNameComparator.compare(whatToFind, whereToFind[i]) == 0) return i;
		}
		return -1;
	}
	
}
