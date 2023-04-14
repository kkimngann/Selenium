package stepdefinitions.global;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import runner.CucumberRunnerTest;
import stepdefinitions.BaseSteps;
import test_flows.registration.RegistrationFlow;
import url.Urls;

import java.util.Date;

@Feature("src/test/resources/features/Register.feature")
public class RegistrationSteps extends BaseSteps implements Urls {
    WebDriver driver = getDriver();
    RegistrationFlow registrationFlow = new RegistrationFlow(driver);
    private String emailTest = "";
    @When("User go to register screen")
    public void goToScreenRegistration(){
        Allure.step("User go to register screen");
        registrationFlow.gotoRegistrationScreen();
    }

    @When("User input data {string}, {string}, {string}, {string}, {string}, {string}")
    public void inputData(String gender, String firstname, String lastname, String email, String password, String confirmPassword){
        Allure.step("User input data:" + gender +", "+ firstname +", "+ lastname +", "+ email+", "+ password +", "+ confirmPassword);
        emailTest = email;
        if(emailTest.equals("auto")){
            emailTest = "auto_email_test" + new Date().getTime() + "@yopmail.com";
        }
        registrationFlow.registerWithData(gender, firstname, lastname, emailTest, password, confirmPassword);
    }

    @When("User select register")
    public void selectRegister(){
        Allure.step("User select register");
        registrationFlow.selectRegister();
    }

    @Then("Error message {string} invalid show correctly {string}")
    public void verifyMessage(String messageType, String message){
        Allure.step(String.format("Error message %s invalid show correctly %s", messageType, message));
        registrationFlow.verifyErrorMessage(messageType, message);
    }

    @Then("Show correct user email {string} after registration")
    public void showCorrectUserEmail(String expectedEmail) {
        Allure.step(String.format("Show correct user email %s", expectedEmail));
        if(expectedEmail.equals("auto")){
            expectedEmail = emailTest;
        }
        registrationFlow.checkCorrectUserEmail(expectedEmail);
    }

}
