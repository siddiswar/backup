package conditionalstatements;

public class SwitchCase {

	public static void main(String[] args) {

		char grade = 'a';

		switch (grade) {
		case 'a':
			System.out.println("Excellent");
			break;
		case 'b':
			System.out.println("Very good");
			break;
		case 'c':
			System.out.println("good");
			break;
		case 'd':
			System.out.println("Better luck next time");
		default:
			System.out.println("Invalid grade");
			break;
		}
		
		System.out.println("Next to switch case code block");

	}

}
