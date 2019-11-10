package listenerspackage;

import org.testng.IExecutionListener;

//All the Listeners present in TestNg are interfaces
//we need to implement
public class listener1 implements IExecutionListener {

	@Override
	public void onExecutionStart() {
		System.out.println("ExecutionListener : onExecutionStart");
		System.out.println("sending email to team with the message :Test Execution started");
	}

	@Override
	public void onExecutionFinish() {
		System.out.println("ExecutionListener : onExecutionFinish");
		//sending email to team with the message "Test Execution completed Please find attached execution results"
		System.out.println("sending email to team with the message :Test Execution completed Please find attached execution results");

	}

}
