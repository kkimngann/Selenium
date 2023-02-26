package models.components.order;

import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCSSSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent{
    private static final By productAttributeSel = By.cssSelector("select[id^=\"product_attribute\"]");
    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorDropdownElem =
                component.findElements(productAttributeSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processorDropdownElem, type);
    }

    @Override
    public String selectRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement RAMDropdownElem =
                component.findElements(productAttributeSel).get(RAM_DROPDOWN_INDEX);
        return selectOption(RAMDropdownElem, type);
    }

    private String selectOption(WebElement dropdownElement, String type){
        Select select = new Select(dropdownElement);
        List<WebElement> allOptions = select.getOptions();
        String fullStringOption = null;
        for (WebElement option : allOptions) {
            String currentOptionText = option.getText();
            String currentOptionTextRemovedSpace = currentOptionText.replace(" ","");
            if(currentOptionTextRemovedSpace.startsWith(type)){
                fullStringOption = currentOptionText;
                break;
            }
        }
        if(fullStringOption == null){
            throw new RuntimeException("[ERROR] The option "+ type + " not found");
        }
        select.selectByVisibleText(fullStringOption);
        return fullStringOption;
    }

}
