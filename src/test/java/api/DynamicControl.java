package api;

import Driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnabled;
import url.Urls;

import java.time.Duration;

public class DynamicControl implements Urls {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            driver.get(baseUrl + dynamicControls);

            By checkboxFormSel = By.id("checkbox-example");
            By inputFormSel = By.id("input-example");

            //Check form interaction
            WebElement checkboxFormElem = driver.findElement(checkboxFormSel);
            WebElement checkBoxElem = checkboxFormElem.findElement(By.tagName("input"));
            if(!checkBoxElem.isSelected()){
                checkBoxElem.click();
            }

            //Input form interaction
            WebElement inputFormElem = driver.findElement(inputFormSel);
            WebElement inputElem = inputFormElem.findElement(By.tagName("input"));
            if(!inputElem.isEnabled()){
                inputFormElem.findElement(By.tagName("button")).click();
            }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
            wait.until(new WaitForElementEnabled(By.tagName("input"), inputFormSel));
            inputElem.sendKeys("Ngan ne");

            driver.quit();
        }
        catch(Exception e){
            e.printStackTrace();
            driver.quit();
        }
    }
}
