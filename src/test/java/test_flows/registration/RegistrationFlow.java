package test_flows.registration;

import io.qameta.allure.Allure;
import models.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.TestNG;

public class RegistrationFlow {
    private final WebDriver driver;


    public RegistrationFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoRegistrationScreen(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.headerMenuComponent().clickRegister();
    }

    public void registerWithData(String gender, String firstName, String lastName, String email, String password, String confirmPassword){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registrationComponent().inputGender(gender);
        registerPage.registrationComponent().inputFirstName(firstName);
        registerPage.registrationComponent().inputLastName(lastName);
        registerPage.registrationComponent().inputEmail(email);
        registerPage.registrationComponent().inputPassword(password);
        registerPage.registrationComponent().inputConfirmPassword(confirmPassword);
        registerPage.registrationComponent().selectRegister();
    }

    public void selectRegister(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registrationComponent().selectRegister();
    }
    public void verifyErrorMessage(String messageType, String message){
        RegisterPage registerPage = new RegisterPage(driver);
        String actualErrorMessage= registerPage.registrationComponent().getErrorMessage(messageType);
        Assert.assertEquals(actualErrorMessage, message, "Error message do not match");
    }
}
