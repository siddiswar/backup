package testngparameterization;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamTests {

	@Parameters({ "username", "password" })
	@Test
	public void firstParamTest(String uname, String pwd) {
		System.out.println(uname + " : " + pwd);
	}

	@Parameters({"browser","url","username","password"})
	@Test
	public void loginTest(String browserName, String url, String uname, String pwd) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("Opening chrome browser");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.out.println("Opening firefox browser");
		} else if (browserName.equalsIgnoreCase("IE")) {
			System.out.println("Opening IE browser");
		}
		
		System.out.println("opening url in browser :" + url);
		
		System.out.println("Inputing username : " + uname);
		System.out.println("Inputing password : " + pwd);
		
		System.out.println("verifying whether login is suceessful");


	}

}
