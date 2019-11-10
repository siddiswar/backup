package conditionalstatementsexamples;

public class GreaterAmongThree {

	public static void main(String[] args) {


			int a=10;
			int b=4;
			int c=15;
			
			
/*			if((a>b)&&(b>c)){
				System.out.println("a is greater");
			}
			
			if((b>a)&&(b>c)){
				System.out.println("b is greater");
			}
			
			if((c>a)&&(c>b)){
				System.out.println("c is greater");
			}*/
			
			
			if((a>b)&&(b>c)){
				System.out.println("a is greater");
			}else if((b>a)&&(b>c)){
				System.out.println("b is greater");
			}else if((c>a)&&(c>b)){
				System.out.println("c is greater");
			}
			
			
			
	}

}
