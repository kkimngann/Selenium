package models.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Component {
    protected WebDriver driver;
    protected WebElement component;
    protected WebDriverWait wait;

    public Component(WebDriver driver, WebElement component) {
        this.driver = driver;
        this.component = component;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    public WebElement findElement(By selector){
        return component.findElement(selector);
    }

    public List<WebElement> findElements(By selector){
        return component.findElements(selector);
    }

    public<T extends Component> T findComponent(Class<T> componentClass, WebDriver driver){
        return findComponents(componentClass, driver).get(0);
    }

    public<T extends Component> List<T> findComponents(Class<T> componentClass, WebDriver driver){
        By componentSelector;
        try{
            componentSelector = getComponentSelector(componentClass);
        }
        catch (Exception e){
            throw  new IllegalArgumentException("[ERROR] Component must have css selector");
        }
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(componentSelector));
        List<WebElement> results = component.findElements(componentSelector);
        Class<?>[] params = new Class[]{WebDriver.class, WebElement.class};
        Constructor<T> constructor;
        try{
            constructor = componentClass.getConstructor(params);
        }
        catch (Exception e){
            throw new IllegalArgumentException("[ERR] the component must have a constructor with param " + Arrays.toString(params));
        }

        //Convert all elements to component
        List<T> components = results.stream().map(webElement -> {
            try{

                return constructor.newInstance(driver, webElement);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return components;
    }

    private By getComponentSelector(Class<? extends Component> componentClass){
        if(componentClass.isAnnotationPresent(ComponentCSSSelector.class)){
            return By.cssSelector(componentClass.getAnnotation(ComponentCSSSelector.class).value());
        } else if(componentClass.isAnnotationPresent(ComponentXpathSelector.class)){
            return By.xpath(componentClass.getAnnotation(ComponentXpathSelector.class).value());
        } else {
            throw new IllegalArgumentException("Component class " + componentClass + " must have annotation" +
                    ComponentCSSSelector.class.getSimpleName() + " or " + ComponentXpathSelector.class.getSimpleName());
        }
    }

    public WebElement getComponent() {
        return component;
    }
}
