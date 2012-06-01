package org.adorsys.waguia.lightxls.core;

/**
 * I have just rename Java its comparator. Due to some trouble shouting at the compilation time.
 * 
 * @author w2b
 *
 * @param <T>
 */
public interface EasyComparator<T> {
	public int compare(T argOne,T argTwo);
}
