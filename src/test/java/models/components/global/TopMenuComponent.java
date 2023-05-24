package models.components.global;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.reporters.jq.Main;

import java.util.List;

@ComponentCSSSelector(value = ".top-menu")
public class TopMenuComponent extends Component {

    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<MainCategoryItemComponent> mainCategoryItemElem(){
        return findComponents(MainCategoryItemComponent.class, driver);
    }

    @ComponentCSSSelector(value = ".top-menu > li")
    public static class MainCategoryItemComponent extends Component{

        public MainCategoryItemComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        public WebElement mainCategoryLinkElement(){
            return component.findElement(By.tagName("a"));
        }

        public String getMainCategoryText(){
            return component.findElement(By.tagName("a")).getText();
        }

        public List<CategoryItemComponent> categoryItemComponents(){
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(CategoryItemComponent.class, driver);
        }

        public void clickMenu() {
            component.click();
        }
    }

    @ComponentCSSSelector(value = ".sublist li a")
    public static class CategoryItemComponent extends Component{
        public CategoryItemComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }
        public void clickSubmenu(){
            component.click();
        }
    }
}
