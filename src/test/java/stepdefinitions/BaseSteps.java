package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import models.pages.BasePage;
import runner.CucumberRunnerTest;
import test_flows.registration.RegistrationFlow;
import url.Urls;

public class BaseSteps extends CucumberRunnerTest implements Urls {

    @Given("Access the test website")
    public void gotoTestPage(){
        Allure.step("Access the test website");
        driver.get(Urls.demoBaseUrl);
    }
}
