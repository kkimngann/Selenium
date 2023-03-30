package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitForElementEnabled implements ExpectedCondition<Boolean> {
    private By selector;
    private By parentSelector;

    public WaitForElementEnabled(By selector, By parentSelector){
        this.selector = selector;
        this.parentSelector = parentSelector;
    }


    @Override
    public Boolean apply(WebDriver driver){
        WebElement parentElem = driver.findElement(parentSelector);
        return parentElem.findElement(selector).isEnabled();
    }

    @Override
    public String toString(){
        return "element located by " + this.selector.toString();
    }
}
