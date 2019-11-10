package readingproperties;

import java.io.IOException;

public class PropertiesCaller {

	public static void main(String[] args) throws IOException {
		ReadProperties1 props = new ReadProperties1();
		String playerName = props.getPropertyFromPropertyFile("player_name");
		System.out.println(playerName);
		
		
		
	}

}
