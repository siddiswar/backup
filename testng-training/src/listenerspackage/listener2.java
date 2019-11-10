package listenerspackage;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.Reporter;

public class listener2 implements ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		System.out.println("SuiteListener : onStart");
		Reporter.log("SuiteListener : onStart");

		System.out.println("Suite being executed :"+ suite.getName());
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("SuiteListener : onFinish");
		System.out.println("Suite Results :" +suite.getResults().toString());
		
	}

}
