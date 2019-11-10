package testngpackage1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testngannotations1 {

	
	@Test
	public void myTest1() {
		String expectedValue = "something";
		String actualValue = "somethingelse";
		Assert.assertEquals(expectedValue, actualValue);
	}

}
