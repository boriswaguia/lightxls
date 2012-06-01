package org.adorsys.waguia.lightxls.core;

public interface Finder<T> {
	/**
	 * find the first parameter in the next parameter array, and return it position.
	 * @param whatToFind
	 * @param whereToFind
	 * @return
	 */
	public int find(T whatToFind,T ...whereToFind);
}
