package models.pages;

import models.components.Component;
import models.components.global.TopMenuComponent;
import models.components.global.footer.FooterComponent;
import models.components.global.header.HeaderMenuComponent;
import models.components.product.ProductGridComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class BasePage extends Component {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }

    public ProductGridComponent productGridComponent(){
        return findComponent(ProductGridComponent.class, driver);
    }
    public FooterComponent footerComponent(){
        return findComponent(FooterComponent.class, driver);
    }

    public TopMenuComponent topMenuComponent(){
        return findComponent(TopMenuComponent.class, driver);
    }

    public HeaderMenuComponent headerMenuComponent(){
        return findComponent(HeaderMenuComponent.class,driver);
    }

    public void gotoMenuWithRandomSubmenu(String mainMenu){
        List<TopMenuComponent.MainCategoryItemComponent> mainCategoryItemComponents = topMenuComponent().mainCategoryItemElem();
        for (TopMenuComponent.MainCategoryItemComponent mainCategoryItemComponent : mainCategoryItemComponents) {
            if(mainCategoryItemComponent.getMainCategoryText().trim().equals(mainMenu)){
                List<TopMenuComponent.CategoryItemComponent> categoryItemComponents = mainCategoryItemComponent.categoryItemComponents();
                if(categoryItemComponents.size()> 0){
                    Random rand = new Random();
                    TopMenuComponent.CategoryItemComponent randomSubmenu = categoryItemComponents.get(rand.nextInt(categoryItemComponents.size()));
                    randomSubmenu.clickSubmenu();
                    break;
                }else {
                    mainCategoryItemComponent.clickMenu();
                    break;
                }

            }

        }
    }




}
