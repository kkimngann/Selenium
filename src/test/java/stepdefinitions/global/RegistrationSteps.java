package stepdefinitions.global;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import models.pages.BasePage;
import runner.CucumberRunner;
import stepdefinitions.BaseSteps;
import test_flows.registration.RegistrationFlow;

@Feature("src/test/resources/features/Register.feature")
public class RegistrationSteps extends CucumberRunner {
    RegistrationFlow registrationFlow = new RegistrationFlow(driver);
    @When("User go to register screen")
    public void goToScreenRegistration(){
        registrationFlow.gotoRegistrationScreen();
    }

    @When("User input data {string}, {string}, {string}, {string}, {string}, {string}")
    public void inputData(String gender, String firstname, String lastname, String email, String password, String confirmPassword){
        registrationFlow.registerWithData(gender, firstname, lastname, email, password, confirmPassword);
    }

    @When("User select register")
    public void selectRegister(){
        registrationFlow.selectRegister();
    }

    @Then("Error message {string} invalid show correctly {string}")
    public void verifyMessage(String messageType, String message){
        registrationFlow.verifyErrorMessage(messageType, message);
    }

}
