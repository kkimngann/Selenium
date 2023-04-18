package runner;

import Driver.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@CucumberOptions(tags="${cucumber.filter.tags}",
        features = "src/test/resources/features", glue = {"stepdefinitions"},
        plugin = { "pretty", "json:target/cucumber-reports/cucumber.json",	"html:target/cucumber-reports/cucumberreport.html" }, monochrome = true)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
    private final static List<DriverFactory> webdriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private static String browser;
    protected WebDriver driver;

    protected WebDriver getDriver(){
        if(this.driver == null){
            this.driver = driverThread.get().getDriver(browser);
            return this.driver;
        }
        return this.driver;
    }

    @BeforeTest(description = "Init browser session")
    @Parameters({"browser"})
    public void initBrowserSession(String browser){
        this.browser = browser;
        driverThread = ThreadLocal.withInitial(() ->{
            DriverFactory webdriverThread = new DriverFactory();
            webdriverThreadPool.add(webdriverThread);
            return webdriverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession(){
        driverThread.get().closeBrowserSession();
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            // testMethodName-yyyy-m-dd-hr-mm-sec.png

            // 1. Get method name
            String methodName = result.getName();

            // 2. Get Taken time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String filename = methodName + "-" + y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec + ".png";

            // 3. Take Screenshot
            WebDriver driver = driverThread.get().getDriver(browser);
            File screenshotBase64Data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {

                // 4. Save
                String fileLocation = System.getProperty("user.dir") + "/screenshots/" + filename;
                FileUtils.copyFile(screenshotBase64Data, new File(fileLocation));

                // 5. Attach to report
                Path content = Paths.get(fileLocation);
                try (InputStream inputStream = Files.newInputStream(content)) {
                    Allure.addAttachment(methodName, inputStream);
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
