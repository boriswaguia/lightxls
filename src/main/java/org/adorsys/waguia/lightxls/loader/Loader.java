package org.adorsys.waguia.lightxls.loader;

import java.util.List;

public interface Loader<T> {
	public List<T> loadEntity(Class<T> clazz,String filePath);
}
