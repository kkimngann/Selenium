package models.components.global.footer;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FooterColumnComponent extends Component {

    private final static By footerSel = By.tagName("h3");
    private final static By linkSel = By.cssSelector("li a");

    public FooterColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public WebElement footerElem(){
        return component.findElement(footerSel);
    }

    public List<WebElement> linksElem(){
        return component.findElements(linkSel);
    }
}