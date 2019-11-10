import org.testng.annotations.Test;

public class testngexample1 {

	//Install TestNG plugin from eclipse marketplace
	//Reference TestNG library in your project
	//import whatever class you need
	
	@Test //test method  and not test
	public void testMethod1(){
		System.out.println("I am test method1..");
	}
	
	@Test
	public void testMethod2(){
		System.out.println("I am test method2..");
	}
}
