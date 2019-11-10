package classandobjects;

public class LearnAboutVariablesCallingProgram {

	public static void main(String[] args) {
		System.out.println("Starting execution");

		// Declaration
		// Instantiation
		// Initialization

		// Triangle triangleObject;

		// triangleObject = new Triangle();

		TriangleExampleToLearnAboutVariables triangleObject = new TriangleExampleToLearnAboutVariables();

		System.out.println("Intial values...");
		System.out.println("Length :" + triangleObject.length);
		System.out.println("Bredth :" + triangleObject.bredth);

		System.out.println("Updated values using direct access");

		triangleObject.bredth = 30;
		triangleObject.length = 40;
		System.out.println("Length :" + triangleObject.length);
		System.out.println("Bredth :" + triangleObject.bredth);
		triangleObject.calculateArea();

		System.out.println("Updated values using getters and setters");

		triangleObject.setLength(300);
		triangleObject.setBredth(400);
		System.out.println("Length :" + triangleObject.getLength());
		System.out.println("Bredth :" + triangleObject.getBredth());
		triangleObject.calculateArea();

		System.out.println("Execution completed");

	}

}
