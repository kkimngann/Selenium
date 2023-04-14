package models.components.order;

import Driver.DriverFactory;
import models.components.Component;
import models.components.ComponentCSSSelector;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
@ComponentCSSSelector(".product-essential")
public abstract class ComputerEssentialComponent extends Component {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType(String type);
    public abstract String selectSoftware(String type);

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
            return optionElement.getText().trim();
        }
        else{
            throw  new RuntimeException("The option " + type + " not found");
        }
    }

    public void selectAddToCart(){
        String selectorStr = "//input[contains(@id,\"add-to-cart-button\")]";
        driver.findElement(By.xpath(selectorStr)).click();
        try{
            Thread.sleep(5000);
        }
        catch (Exception ignore){
        }
    }

    public double getDefaultPrice(){
        String selectorStr = ".product-price";
        return Double.parseDouble(component.findElement(By.cssSelector(selectorStr)).getText().trim());
    }

}
