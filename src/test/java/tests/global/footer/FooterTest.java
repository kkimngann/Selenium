package tests.global.footer;

import Driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import support.verfication.Verifier;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest {

    @Test
    public void testFooterCategoryPage() {
        WebDriver driver = DriverFactory.getChromeDriver();
        try{
            driver.get(Urls.demoBaseUrl);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyProductCategoryFooterComponent();
        }
        catch (Exception e){
            e.printStackTrace();
            driver.quit();
        }
        driver.quit();
    }

    @Test
    public void testFooterRegisterPage() {

    }

    @Test
    public void testFooterLoginPage() {

    }

    // dependsOnMethods: testFooterRegisterPage need to pass the testFooterHomePage can run --> khong nen dung
    //priority default 0 --> 1--> 2 --> khong nen dung
    @Test
    public void testFooterHomePage(){
    }

    public static void testFooterColumn(FooterColumnComponent footerColumnComponent){
        System.out.println(footerColumnComponent.footerElem().getText());
        footerColumnComponent.linksElem().forEach(link ->{
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }


}
