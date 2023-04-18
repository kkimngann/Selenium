package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import runner.CucumberRunnerTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

@Test
public class CommonSteps extends CucumberRunnerTest implements Urls {
    @Given("Access the test website")
    public void gotoTestPage(){
        WebDriver driver = getDriver();
        Allure.step("Access the test website");
        driver.get(demoBaseUrl);
    }

    @When("User go to menu {string}")
    public void userGoToMenu(String menuName) {
        WebDriver driver = getDriver();
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        Allure.step(String.format("User go to menu %s", menuName));
        footerTestFlow.gotoMenu(menuName);
    }

    @Then("Verify that footer shown correctly")
    public void verifyThatFooterShownCorrectly() {
        WebDriver driver = getDriver();
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        Allure.step("Verify that footer shown correctly");
        footerTestFlow.verifyFooterComponent();
    }
}
