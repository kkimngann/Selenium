package api;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchBrowser {
    public static void main(String[] args) throws InterruptedException {
        String currentProjectLocation = System.getProperty("user.dir");

        String chromeDriverLocation = "";
        String chromeDefaultProfileLocation = System.getProperty("user.home");
        if (OS.isFamilyMac()) {
            chromeDriverLocation = currentProjectLocation + "/src/test/resources/drivers/chromedriver";
            chromeDefaultProfileLocation = chromeDefaultProfileLocation + "/Library/Application Support/Google/Chrome";
        }
        if (OS.isFamilyWindows()) {
            chromeDriverLocation = currentProjectLocation + "\\src\\test\\resources\\drivers\\chromedriver";
        }
        if (chromeDriverLocation.isEmpty()) {
            throw new IllegalArgumentException("Cannot detect OS type");
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        // chromeOptions.addArguments("--start-maximized");
        // chromeOptions.addArguments("--headless");
        // chromeOptions.addArguments("--no-sandbox");
        // chromeOptions.addArguments("--disable-dev-shm-usage");
        // chromeOptions.addArguments("user-data-dir=" + chromeDefaultProfileLocation);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://learn.sdetpro.com");
        Thread.sleep(3000);
        driver.quit();
    }

}
