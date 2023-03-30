package test_flows.global;

import models.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import support.verfication.Verifier;

public class LoginTestFlow {
    private final WebDriver driver;

    public LoginTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void userInputCredentialAndSubmit(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginComponent().inputEmail(email);
        loginPage.loginComponent().inputPassword(password);
        loginPage.loginComponent().selectLogin();
    }

    public void userGotoLoginScreen(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.headerMenuComponent().clickLogin();
    }

    public void checkErrorMessage(String expectedError){
        LoginPage loginPage = new LoginPage(driver);
        String actualErrorMessage = loginPage.loginComponent().getErrorMessage();
        Verifier.verifyEqual(actualErrorMessage, expectedError);

    }

    public void checkCorrectUserEmail(String expectedEmail) {
        LoginPage loginPage = new LoginPage(driver);
        String actualErrorMessage = loginPage.headerMenuComponent().getCurrentUserEmail();
        Verifier.verifyEqual(actualErrorMessage, expectedEmail);
    }

    public void checkButtonLogout() {
        LoginPage loginPage = new LoginPage(driver);
        Boolean isExistingLogout = loginPage.headerMenuComponent().checkButtonLogout();
        Verifier.verifyTrue(isExistingLogout);
    }
}
