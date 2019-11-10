package classandobjects;

public class TriangleExampleToLearnAboutVariables {

	//Direct initialization
	int length = 10;
	int bredth = 12;
	
	
/*	void setLength(int lengthFromCallingProgram){
		length = lengthFromCallingProgram;
	}

	int getLength(){
		return length;
	}*/
	
	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public int getBredth() {
		return bredth;
	}


	public void setBredth(int bredth) {
		this.bredth = bredth;
	}


	//Member method
	void calculateArea(){
		int area= ( length * bredth )/2;
		System.out.println(area);
	}

	
	
	
}
