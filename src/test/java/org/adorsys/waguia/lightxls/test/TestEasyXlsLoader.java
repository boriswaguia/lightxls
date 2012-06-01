package org.adorsys.waguia.lightxls.test;

import java.util.List;

import org.adorsys.waguia.lightxls.core.EasyClazzLoader;
import org.adorsys.waguia.lightxls.core.EasyXlsFileLoader;
import org.adorsys.waguia.lightxls.generic.EasyXlsClazzLoader;
import org.adorsys.waguia.lightxls.loader.EasyXlsLoader;
import org.adorsys.waguia.lightxls.loader.Person;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class TestEasyXlsLoader {

	@Test
	public void testLoadXlsFile() {
		EasyXlsFileLoader easyXlsLoader = new EasyXlsLoader();
		Workbook workbook = easyXlsLoader.loadXlsFile("src/test/resources/test.xls");
		EasyClazzLoader<Person> easyXlsClazzLoader = new EasyXlsClazzLoader<Person>(workbook, Person.class);
		List<Person> personData = easyXlsClazzLoader.loadClazzData(Person.class);
		System.out.println("Results \t"+personData);
	}

}
