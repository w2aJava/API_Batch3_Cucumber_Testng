package stepDefinations;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customeListeners.CustomerListener;
import customeListeners.ExtentReportListener;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

public class CreateCustomerAPISteps extends ExtentReportListener {

	RequestSpecification reqSpecs;
	Response response;
	ExtentTest logInfo = null;

	@Before("@CreateCustomerAPI")
	public void beforeCreateCustomerAPIScenario(cucumber.api.Scenario scenario) {
		System.out.println("Scenario.getId()-->" + scenario.getId());
		String[] splitedName = scenario.getId().split("/");
		// scenario.getId():-

		String featureName = splitedName[splitedName.length - 1];

		featureName = featureName.substring(0, featureName.indexOf("."));

		String scenarioName = scenario.getName();
		if (createCustomerTest == null) {
			System.out.println("Extent report-->"+extent);
			createCustomerTest = extent.createTest(Feature.class, featureName);
		}

		// test = extent.createTest(Feature.class, featureName);

		test = createCustomerTest.createNode(Scenario.class, scenarioName);
		System.out.println("Feature Name-->" + featureName);
	}

	/*
	 * @Before:- Gets executed before every scenario
	 * 
	 * @After:- Gets executed after every scenario
	 * 
	 * 
	 */

	@Given("I set the authentication using the secret key")
	public void i_set_the_authentication_using_the_secret_key() {

		reqSpecs = given().auth().basic(configProperty.getValidSecretKet(), "");

		try {

			logInfo = test.createNode(new GherkinKeyword("Given"), " i_set_the_authentication_using_the_secret_key");

			logInfo.info("I set the authentication successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}
	@Given("I set the authentication using the invalid secret key")
	public void i_set_the_authentication_using_the_invalid_secret_key() {
		
		reqSpecs = given().auth().basic(configProperty.getInValidSecretKet(), "");
		try {

			logInfo = test.createNode(new GherkinKeyword("Given"), " i_set_the_authentication_using_the_invalid_secret_key");

			logInfo.info("I set the authentication successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}


	@Given("I set {string} as email of the customer")
	public void i_set_as_email_of_the_customer(String customerEmail) {

		reqSpecs = reqSpecs.formParam("email", customerEmail);
		try {

			logInfo = test.createNode(new GherkinKeyword("Given"), " i_set_as_email_of_the_customer");

			logInfo.info("I set " + customerEmail + " as the email in the request");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Given("I set {string} as the description of the customer")
	public void i_set_as_the_description_of_the_customer(String customerDescription) {

		reqSpecs = reqSpecs.formParam("description", customerDescription).log().all();
		try {

			logInfo = test.createNode(new GherkinKeyword("Given"), "i_set_as_the_description_of_the_customer");

			logInfo.info("I set " + customerDescription + " as the description in the request");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@When("I send a Post request to the url")
	public void i_send_a_Post_request_to_the_url() {

		response = reqSpecs.post(configProperty.getCustomerAPIEndPoint());
		response.prettyPrint();
		try {

			logInfo = test.createNode(new GherkinKeyword("When"), "i_send_a_Post_request_to_the_url");

			logInfo.info("I send a post request");
			logInfo.info(response.asString());

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("I should get {int} as the response status code")
	public void i_should_get_as_the_response_status_code(int expectedStatusCode) {

		// Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
		
		try {

			logInfo = test.createNode(new GherkinKeyword("Then"), "i_should_get_"+expectedStatusCode+"as_the_response_status_code");

			logInfo.info("I got "+response.getStatusCode()+" as the expected status code");
			//logInfo.info(response.asString());

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("I should get id field in the response")
	public void i_should_get_id_field_in_the_response() {

		Assert.assertNotNull(response.jsonPath().get("id"));
		try {

			logInfo = test.createNode(new GherkinKeyword("Then"), "i_should_get_id_field_in_the_response");

			logInfo.info("Id field is available in the respons, whose value is "+response.jsonPath().get("id"));
			//logInfo.info(response.asString());

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}

	}

	@Then("the email in the response should be {string}")
	public void the_email_in_the_response_should_be(String expectedEmail) {
		Assert.assertEquals(response.jsonPath().get("email"), expectedEmail);

		try {

			logInfo = test.createNode(new GherkinKeyword("Then"), "the_email_in_the_response_should_be "+expectedEmail);

			logInfo.info("The email in the response is "+response.jsonPath().get("email"));
			//logInfo.info(response.asString());

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("the description in the response should be {string}")
	public void the_description_in_the_response_should_be(String expectedDescription) {
		Assert.assertEquals(response.jsonPath().get("description"), expectedDescription);

		try {

			logInfo = test.createNode(new GherkinKeyword("Then"), "he_description_in_the_response_should_be "+expectedDescription);

			logInfo.info("The description  in the response is "+response.jsonPath().get("description"));
			//logInfo.info(response.asString());

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

}
