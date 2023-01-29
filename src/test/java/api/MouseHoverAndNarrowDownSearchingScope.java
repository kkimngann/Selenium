package api;

import Driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import java.util.List;

public class MouseHoverAndNarrowDownSearchingScope implements Urls {
    private final static By figureSel = By.className("figure");
    private final static By profileNameSel = By.cssSelector(".figcaption h5 ");
    private final static By profileLinkSel = By.cssSelector(".figcaption a");
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromeDriver();
        try {
            driver.get(baseUrl + hover);
            List<WebElement> figureElems = driver.findElements(figureSel);
            if(figureElems.isEmpty()){
                throw new RuntimeException("No profile found");
            }

            Actions actions = new Actions(driver);

            for (WebElement figureElem : figureElems) {
                WebElement profileNameElem = figureElem.findElement(profileNameSel);
                WebElement profileLinkElem = figureElem.findElement(profileLinkSel);

                //Move hover
                actions.moveToElement(figureElem).perform();
                System.out.println("Is profile name displayed: " + profileNameElem.isDisplayed());
                System.out.println("Is profile link displayed: "+ profileLinkElem.isDisplayed());
                Thread.sleep(1000);
            }

            driver.quit();
        }

        catch(Exception e){
            e.printStackTrace();
            driver.quit();
        }

    }
}
