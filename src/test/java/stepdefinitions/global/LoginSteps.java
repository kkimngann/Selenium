package stepdefinitions.global;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import runner.CucumberRunnerTest;
import stepdefinitions.BaseSteps;
import test_flows.global.LoginTestFlow;
import url.Urls;

@Feature("src/test/resources/features/Login.feature")
public class LoginSteps extends BaseSteps implements Urls {
    WebDriver driver = getDriver();
    LoginTestFlow loginTestFlow = new LoginTestFlow(driver);
    @When("User go to login screen")
    public void goToScreenRegistration(){
        Allure.step("User go to login screen");
        getDriver().get(demoBaseUrl);
        loginTestFlow.userGotoLoginScreen();
    }


    @And("User input email {string} and password {string} and submit")
    public void userInputEmailAndPasswordAndSubmit(String email, String password) {
        Allure.step(String.format("User input email %s and password %s and submit", email, password));
        loginTestFlow.userInputCredentialAndSubmit(email, password);
    }

    @Then("Error message show correctly")
    public void errorMessageShowCorrectly(String expectedErrorMessage) {
        Allure.step(String.format("Error message %s show correctly", expectedErrorMessage));
        loginTestFlow.checkErrorMessage(expectedErrorMessage);
    }


    @Then("Show correct user email {string} after login")
    public void showCorrectUserEmail(String expectedEmail) {
        Allure.step(String.format("Show correct user email %s", expectedEmail));
        loginTestFlow.checkCorrectUserEmail(expectedEmail);
    }

    @And("Show button log out correctly")
    public void showButtonLogOutCorrectly() {
        Allure.step("Show button log out correctly");
        loginTestFlow.checkButtonLogout();
    }
}
