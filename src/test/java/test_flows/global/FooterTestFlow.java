package test_flows.global;

import models.components.global.TopMenuComponent;
import models.components.global.footer.FooterColumnComponent;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }


    public void verifyFooterComponent(){
        BasePage basePage = new BasePage(driver);
        verifyInformationColumn(basePage.footerComponent().informationColumnComponent());
        verifyCustomerServiceColumn(basePage.footerComponent().customerServiceColumnComponent());
        verifyMyAccountColumn(basePage.footerComponent().myAccountColumnComponent());
        verifyFollowUsColumn(basePage.footerComponent().followUsColumnComponent());
    }

    private void verifyFollowUsColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList("Facebook", "Twitter", "RSS", "YouTube", "Google+");
        String fbUrl= "http://www.facebook.com/nopCommerce";
        String TwitterUrl= "https://twitter.com/nopCommerce";
        String rssUrl= baseUrl + "news/rss/1";
        String youtubeUrl= "http://www.youtube.com/user/nopCommerce";
        String googleUrl= "https://plus.google.com/+nopcommerce";
        List<String> expectedHrefs = Arrays.asList(fbUrl, TwitterUrl, rssUrl, youtubeUrl, googleUrl);
        testFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyMyAccountColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList(
                "My account", "Orders", "Addresses", "Shopping cart",
                "Wishlist");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "customer/info", baseUrl + "customer/orders", baseUrl + "customer/addresses", baseUrl + "cart",
                baseUrl + "wishlist");

        testFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyCustomerServiceColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList(
                "Search", "News", "Blog", "Recently viewed products",
                "Compare products list", "New products");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "search", baseUrl + "news", baseUrl + "blog", baseUrl + "recentlyviewedproducts",
                baseUrl + "compareproducts", baseUrl + "newproducts");
        testFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComponent) {
        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList(
                "Sitemap", "Shipping & Returns", "Privacy Notice", "Conditions of Use",
                "About us", "Contact us");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "sitemap", baseUrl + "shipping-returns", baseUrl + "privacy-policy", baseUrl + "conditions-of-use",
                baseUrl + "about-us", baseUrl + "contactus");
        testFooterColumn(footerColumnComponent, expectedLinkTexts, expectedHrefs);
    }
    public void testFooterColumn(FooterColumnComponent footerColumnComponent, List<String> expectedLinkText, List<String> expectedHref){
        List<String> actualLinkText = new ArrayList<>();
        List<String> actualHref = new ArrayList<>();

        footerColumnComponent.linksElem().forEach(link ->{
            actualLinkText.add(link.getText().trim());
            actualHref.add(link.getAttribute("href"));

        });
        if(actualLinkText.isEmpty() || actualHref.isEmpty()){
            Assert.fail("[ERROR] Texts or hyperlinks is empty");
        }

        //Link text verification
        Assert.assertEquals(actualLinkText, expectedLinkText, "[ERROR] Footer text do not match. Please check");

        //Verify Href
        Assert.assertEquals(actualHref, expectedHref, "[ERROR] Footer link do not match");;
    }

    public void gotoMenu(String mainMenu) {
        BasePage basePage = new BasePage(driver);
        basePage.gotoMenuWithRandomSubmenu(mainMenu);

    }
}
