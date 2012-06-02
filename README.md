lightxls
========

Help loading xls content in POJO. 

Example :  Assume that we have a POJO Person.java.

THe following Junit test show how to load Person.java from an xls file, using lighxls library.

Let us get a SNAPSHOT of !

public class TestEasyXlsLoader {

	@Test
	public void testLoadXlsFile() {
		EasyXlsFileLoader easyXlsLoader = new EasyXlsLoader();
		Workbook workbook = easyXlsLoader.loadXlsFile("src/test/resources/test.xls");
		EasyClazzLoader<Person> easyXlsClazzLoader = new EasyXlsClazzLoader<Person>(workbook, Person.class);
		List<Person> personData = easyXlsClazzLoader.loadClazzData(Person.class);
		//Consider Using a Log, as your whishes.
		System.out.println("Results \t"+personData);
	}

}

Is it not easy ?

OK ! What about scalability ?

	After getting a look in the tools, you will notice I used a lot of Interface.
Every functions are embedded into Interface. What does this mean ?
THis mean that, you can change the behaviour of any function by providing your own implementation.

GREAT ! How will the tools know about my Implementation ?
    Just Getters/Setters methods provided, to change the default values !

Want to invest ? Thanks !

RESUME !

1- WHERE ARE NOW ?
   Actually, We simply load xls column in POJO. Data are loaded As String. There is no type checking.
2- WHERE ARE WE GOING ?
   The main goal of easyxls is to load data from source (xls, database, json, aso) in POJO, By using 
   Reflection, as presented in the Test up there.
     