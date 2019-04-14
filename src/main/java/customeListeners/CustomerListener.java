package customeListeners;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.restassured.RestAssured;
import util.ConfigProperty;

public class CustomerListener extends ExtentReportListener implements ITestListener {

	

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

		configProperty = ConfigFactory.create(ConfigProperty.class);

		RestAssured.baseURI = configProperty.getBaseURI();
		RestAssured.basePath = configProperty.getBasePath();
		
		extent=ExtentReportListener.setUp();

	}

	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
