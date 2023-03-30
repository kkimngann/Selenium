package models.components.order;

import models.components.ComponentCSSSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".product-essential")
public class CheapComputerComponent extends ComputerEssentialComponent{

    public CheapComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        return selectCompOption(type);
    }

    @Override
    public String selectRAMType(String type) {
        return selectCompOption(type);
    }

    @Override
    public String selectSoftware(String type) {
        return selectCompOption(type);
    }
}
