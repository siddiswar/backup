package classandobjects;

public class MethodExamplesClass {

	
	
	
	int length = 3;
	int bredth= 6;
	
	
	
	
	
	void printArea(){
		System.out.println((length*bredth)/2);
	}
	
	
	int getArea(){
		int area = (length*bredth)/2;
		return area;
	}
	
	
	int calculateArea(int len,int bre){
		int area = (len*bre)/2;
		return area;
	}
	
	
}
