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
import url.Urls;

public class FooterTest {

    @Test
    public void testFooterCategoryPage() {
    }

    @Test (priority = 3)
    public void testFooterRegisterPage() {
       String actualResult = "Ngan";
       String expectedResult = "Kim";
       //Verifier.verifyEqual(actualResult, expectedResult);
        // hard assertion
       //Assert.assertEquals(actualResult, expectedResult, "[FAILED] Name is incorrect");
       Assert.fail("FAILED");
    }

    @Test
    public void testFooterLoginPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2, "FAILED");
        softAssert.assertEquals(1,1);
        softAssert.assertEquals(1,2,"FAILED");
        softAssert.assertAll();
    }

    // dependsOnMethods: testFooterRegisterPage need to pass the testFooterHomePage can run --> khong nen dung
    //priority default 0 --> 1--> 2 --> khong nen dung
    @Test(priority = 1, dependsOnMethods = {"testFooterRegisterPage"})
    public void testFooterHomePage(){
        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoBaseUrl);
        try {
            HomePage homePage = new HomePage(driver);
            InformationColumnComponent informationColumnComponent = homePage.footerComponent().informationColumnComponent();
            testFooterColumn(informationColumnComponent);

            CustomerServiceColumnComponent customerServiceColumnComponent = homePage.footerComponent().customerServiceColumnComponent();
            testFooterColumn(customerServiceColumnComponent);
            driver.quit();
        }
        catch (Exception e){
            e.printStackTrace();
            driver.quit();
        }
    }

    public static void testFooterColumn(FooterColumnComponent footerColumnComponent){
        System.out.println(footerColumnComponent.footerElem().getText());
        footerColumnComponent.linksElem().forEach(link ->{
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });

    }
}
