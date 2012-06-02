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
package org.adorsys.waguia.lightxls.test;

import java.util.List;

import org.adorsys.waguia.lightxls.core.EasyClazzLoader;
import org.adorsys.waguia.lightxls.core.EasyXlsFileLoader;
import org.adorsys.waguia.lightxls.generic.EasyXlsClazzLoader;
import org.adorsys.waguia.lightxls.loader.EasyXlsLoader;
import org.adorsys.waguia.lightxls.loader.Person;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
/**
 * 
 * @author w2b
 *
 */
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
