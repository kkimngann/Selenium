package stepdefinitions.global;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import runner.CucumberRunnerTest;
import test_flows.registration.RegistrationFlow;

@Feature("src/test/resources/features/Register.feature")
public class RegistrationSteps extends CucumberRunnerTest {
    RegistrationFlow registrationFlow = new RegistrationFlow(driver);
    @When("User go to register screen")
    public void goToScreenRegistration(){
        Allure.step("User go to register screen");
        registrationFlow.gotoRegistrationScreen();
    }

    @When("User input data {string}, {string}, {string}, {string}, {string}, {string}")
    public void inputData(String gender, String firstname, String lastname, String email, String password, String confirmPassword){
        Allure.step("User input data:" + gender +", "+ firstname +", "+ lastname +", "+ email+", "+ password +", "+ confirmPassword);
        registrationFlow.registerWithData(gender, firstname, lastname, email, password, confirmPassword);
    }

    @When("User select register")
    public void selectRegister(){
        Allure.step("User select register");
        registrationFlow.selectRegister();
    }

    @Then("Error message {string} invalid show correctly {string}")
    public void verifyMessage(String messageType, String message){
        Allure.step("Check error message");
        registrationFlow.verifyErrorMessage(messageType, message);
    }

}
