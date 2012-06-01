package org.adorsys.waguia.lightxls.core;

import java.util.List;


public interface EasyClazzLoader<T> {
	public List<T> loadClazzData(Class clazz);
}
