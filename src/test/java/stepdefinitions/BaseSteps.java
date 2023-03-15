package stepdefinitions;

import io.cucumber.java.en.Given;
import models.pages.BasePage;
import runner.CucumberRunnerTest;
import test_flows.registration.RegistrationFlow;
import url.Urls;

public class BaseSteps extends CucumberRunnerTest implements Urls {
    @Given("Access the test website")
    public void gotoTestPage(){
        driver.get(Urls.demoBaseUrl);
    }
}
