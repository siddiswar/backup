package readingproperties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties1 {

		public String getPropertyFromPropertyFile(String key) throws IOException{
			
			Properties propsObject = new Properties();
			
			//load method loads properties from properties file into properties object
			
			FileReader propertiesFileReader = new FileReader("config.properties");
			
			propsObject.load(propertiesFileReader);
			
			String valueOfTheKey = propsObject.getProperty(key);
			
			return valueOfTheKey;
		} 

}
