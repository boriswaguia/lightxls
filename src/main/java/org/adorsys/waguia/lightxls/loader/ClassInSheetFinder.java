package org.adorsys.waguia.lightxls.loader;

import org.adorsys.waguia.lightxls.core.ClassToSheetNameComparator;
import org.adorsys.waguia.lightxls.core.EasyComparator;
import org.adorsys.waguia.lightxls.core.Finder;

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
