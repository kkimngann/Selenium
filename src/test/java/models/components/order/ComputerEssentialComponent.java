package models.components.order;

import models.components.Component;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ComputerEssentialComponent extends Component {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType(String type);

    protected String selectCompOption(String type){
        String selectorStr = "//label[contains(text(), \"" + type + "\")]";
        By optionSelector = By.xpath(selectorStr);
        WebElement optionElement = null;
        try
        {
            optionElement = component.findElement(optionSelector);
        }
        catch (Exception ignored){}

        if(optionElement != null){
            optionElement.click();
            return optionElement.getText();
        }
        else{
            throw  new RuntimeException("The option " + type + " not found");
        }
    }

    public void selectAddToCart(){
        String selectorStr = "//input[contains(@id,\"add-to-cart-button\")]";
        driver.findElement(By.xpath(selectorStr)).click();
    }
}
