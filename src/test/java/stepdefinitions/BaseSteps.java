package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import runner.Order_CucumberRunnerTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class BaseSteps extends Order_CucumberRunnerTest implements Urls {
    FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
    @Given("Access the test website")
    public void gotoTestPage(){
        Allure.step("Access the test website");
        getDriver().get(Urls.demoBaseUrl);
    }

    @When("User go to menu {string}")
    public void userGoToMenu(String menuName) {
        Allure.step(String.format("User go to menu %s", menuName));
        footerTestFlow.gotoMenu(menuName);
    }

    @Then("Verify that footer shown correctly")
    public void verifyThatFooterShownCorrectly() {
        Allure.step("Verify that footer shown correctly");
        footerTestFlow.verifyFooterComponent();
    }
}
