package readingproperties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Properties propsObject = new Properties();
		
		//load method loads properties from properties file into properties object
		
		FileReader propertiesFileReader = new FileReader("config.properties");
		
		propsObject.load(propertiesFileReader);
		
		
		String name = propsObject.getProperty("player_name");
		System.out.println(name);
		
		String age = propsObject.getProperty("player_age");
		int convertedAge = Integer.parseInt(age);
		System.out.println("Total Age in days :" + (convertedAge*365));
		
		System.out.println(propsObject.getProperty("player_country"));
		
		
		System.out.println(propsObject.getProperty("player_centuries"));
		

	}

}
