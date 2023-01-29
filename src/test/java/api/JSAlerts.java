package api;

import Driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.time.Duration;


public class JSAlerts implements Urls {
    private final static By jsAlertSel = By.cssSelector("[onClick=\"jsAlert()\"]");
    private final static By jsAlertConfirmSel = By.cssSelector("[onClick=\"jsConfirm()\"]");
    private final static By jsAlertPromptSel = By.cssSelector("[onClick=\"jsPrompt()\"]");
    private final static By resultSel = By.id("result");
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(baseUrl + jsalert);
            WebElement triggerJSAlertBtnElem;
            Alert alert;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            //JSAlert / ok
            handleAlert(driver, jsAlertSel, true);

            //JSConfirm / cancel
            handleAlert(driver, jsAlertConfirmSel, false);

            //JSPrompt
            handleAlert(driver, jsAlertPromptSel, "Ngan ne");


            driver.quit();
        }

        catch(Exception e){
            e.printStackTrace();
            driver.quit();
        }

    }

    public static void handleAlert(WebDriver driver, By alertSel, boolean isAccepting){
        WebElement triggerJSAlertBtnElem;
        Alert alert;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        triggerJSAlertBtnElem = driver.findElement(alertSel);
        triggerJSAlertBtnElem.click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert content: " + alert.getText());
        if(isAccepting){
            alert.accept();
        }else {
            alert.dismiss();
        }
        System.out.println("Result: " + driver.findElement(resultSel).getText());
    }

    public static void handleAlert(WebDriver driver, By alertSel, String inputText){
        WebElement triggerJSAlertBtnElem;
        Alert alert;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        triggerJSAlertBtnElem = driver.findElement(alertSel);
        triggerJSAlertBtnElem.click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert content: " + alert.getText());
        alert.sendKeys(inputText);
        alert.accept();
        System.out.println("Result: " + driver.findElement(resultSel).getText());
    }
}
