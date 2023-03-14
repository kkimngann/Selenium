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
        registerPage.registerForm().inputGender(gender);
        registerPage.registerForm().inputFirstName(firstName);
        registerPage.registerForm().inputLastName(lastName);
        registerPage.registerForm().inputEmail(email);
        registerPage.registerForm().inputPassword(password);
        registerPage.registerForm().inputConfirmPassword(confirmPassword);
        registerPage.registerForm().selectRegister();
    }

    public void selectRegister(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerForm().selectRegister();
    }
    public void verifyErrorMessage(String messageType, String message){
        RegisterPage registerPage = new RegisterPage(driver);
        String actualErrorMessage= registerPage.registerForm().getErrorMessage(messageType);
        Assert.assertEquals(actualErrorMessage, message, "Error message do not match");
    }
}
