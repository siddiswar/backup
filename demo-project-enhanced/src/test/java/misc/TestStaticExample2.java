package misc;

/**
 * @author Siddu
 */


public class TestStaticExample2{
	
	public static void main(String[] args) {
		TestStaticExample obj1 = new TestStaticExample();
		TestStaticExample obj2 = new TestStaticExample();
		
		obj1.staticMethod();
		obj2.staticMethod();
		
		obj1.nonStaticMethod();
		obj2.nonStaticMethod();
		
		System.out.println(obj1.staticVar);
		System.out.println(obj2.staticVar);

		System.out.println(obj1.nonstaticVar);
		System.out.println(obj2.nonstaticVar);

		

	}
	
}
class TestStaticExample {

	
	int nonstaticVar;
	static int staticVar;
	
	
	public void nonStaticMethod(){
		nonstaticVar++;
		staticVar++;
	}
	
	public static void staticMethod(){
		staticVar++;
	}
}


