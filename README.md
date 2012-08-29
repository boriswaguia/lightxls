lightxls
========

Help loading xls content in POJO. 

Example :  Assume that we have a POJO Person.java.

THe following Junit test show how to load Person.java from an xls file, using lighxls library.

Let us get a SNAPSHOT !

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

Is it not easy ?
Change behaviour  by providing your own implementation.

GREAT ! How will the tools know about my Implementation ?
    Just use Getters and Setters function provided, to change default values !

RESUME !

1- Actually 
	* We load xls column in POJO. Data are loaded As String. There is no type checking.
	* Support for Native Java type checking.
2- Comming soon
    * Type Detecting on the fly,
    * Support references (reference, collections and map)
    * aso