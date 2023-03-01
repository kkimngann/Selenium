package tests.global.footer;

import models.components.global.footer.FooterColumnComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;
import tests.BaseTest;
import url.Urls;

public class FooterTest extends BaseTest {

    public FooterTest(WebDriver driver) {
        super(driver);
    }

    @Test
    public void btestFooterCategoryPage() {
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCategoryFooterComponent();
    }

    @Test
    public void atestFooterRegisterPage() {
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCategoryFooterComponent();
    }


    public static void testFooterColumn(FooterColumnComponent footerColumnComponent){
        System.out.println(footerColumnComponent.footerElem().getText());
        footerColumnComponent.linksElem().forEach(link ->{
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }


}
