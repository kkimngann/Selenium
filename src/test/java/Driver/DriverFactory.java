package Driver;

import io.cucumber.java.hu.De;
import org.apache.commons.exec.OS;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {
    private WebDriver driver;
    public static WebDriver getChromeDriver(){
        String currentProjectLocation = System.getProperty("user.dir");

        String chromeDriverLocation = "";
        String chromeDefaultProfileLocation = System.getProperty("user.home");
        if(OS.isFamilyMac()) {
            chromeDriverLocation = currentProjectLocation + "/src/test/resources/drivers/chromedriver";
            chromeDefaultProfileLocation = chromeDefaultProfileLocation + "/Library/Application Support/Google/Chrome";
        }
        if(OS.isFamilyWindows()){
            chromeDriverLocation = currentProjectLocation + "\\src\\test\\resources\\drivers\\chromedriver";
        }
        if(chromeDriverLocation.isEmpty()){
            throw  new IllegalArgumentException("Can not detect OS type");
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(chromeOptions);


        //Using safari on macmini
        //WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public WebDriver getDriver(String browserName) {
        if(driver == null){
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.ANY);
            BrowserType browserType;
            try{
                browserType = BrowserType.valueOf(browserName);
            }catch (Exception e){
                e.printStackTrace();
                throw new IllegalArgumentException(browserName + " do not support");
            }

            switch (browserType){
                case chrome:
                    desiredCapabilities.setBrowserName(BrowserType.chrome.getName());
                    break;
                case firefox:
                    desiredCapabilities.setBrowserName(BrowserType.firefox.getName());
                    break;
                case safari:
                    desiredCapabilities.setBrowserName(BrowserType.safari.getName());
                    break;
            }

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--headless");

            String gridHub = System.getProperty("gridHub");
            String hub = gridHub +"/wd/hub";
            try{
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = new RemoteWebDriver(new URL(hub), desiredCapabilities);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return driver;
    }
}
