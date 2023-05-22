package Driver;


import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.hu.De;

import java.io.FileWriter;
import java.util.Dictionary;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import org.apache.commons.exec.OS;
import org.json.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;


import java.net.URL;
import java.time.Duration;
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
            throw  new IllegalArgumentException("Cannot detect OS type");
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

            java.util.Map<String, Boolean> moonoption = new java.util.HashMap<>();

            moonoption.put("enableVideo", true);
            desiredCapabilities.setCapability("moon:options", moonoption);
            
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
            String username = System.getProperty("BROWSERSTACK_USERNAME");
            String accessKey = System.getProperty("BROWSERSTACK_ACCESSKEY");

            // Create a ChromeOptions object and set the desired capabilities.

            // Create a RemoteWebDriver object and pass the BrowserStack URL, username, access key, and desired capabilities.
            String hub = "https://" + username + ":" + accessKey + "@hub.browserstack.com:80/wd/hub";

            try{

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

    public void closeBrowserSession(){
        if(driver != null){
            // declare the JavascriptExecutor class
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
            JSONObject json = new JSONObject(response.toString());
            writeToFile((String) json.get("build_hashed_id"));
            System.out.println("build_hashed_id: " + json.get("build_hashed_id"));
            driver.quit();
        }
    }
    public static void writeToFile(String inputText){
        try {
            FileWriter myWriter = new FileWriter("build_hashed_id.txt");
            myWriter.write(inputText);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
