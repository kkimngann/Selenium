package stepdefinitions;

import Driver.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import url.Urls;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class BaseSteps {
    private final static List<DriverFactory> webdriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String browser;

    protected WebDriver getDriver(){
        return driverThread.get().getDriver(this.browser);
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
        if(driverThread.get().getDriver(browser) != null){
            driverThread.get().getDriver(browser).quit();
        }
    }
    @AfterMethod
    public void captureScreenshot(ITestResult result){
        if(true){
            //name partern menthodNAME-DD-MM-YY-HH-MM-SS.png
            String methodName = result.getName();
            Calendar calendar = new GregorianCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) +1 ;
            int day = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR);
            int min = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            String screenshotName = methodName + "-" + year + "-" + month + "-" + day
                    + "-" + hour + "-" + min + "-" + second + ".jpg";
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + screenshotName;
            takeScreenshot(fileLocation);
        }
    }

    private void takeScreenshot(String fileLocation){
        try {
            // Get the size of the screen
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            // Create a robot to take the screenshot
            Robot robot = new Robot();
            BufferedImage screenshot = robot.createScreenCapture(screenRect);

            // Save the screenshot as a JPEG image
            ImageIO.write(screenshot, "jpg", new File(fileLocation));


            //add to report
            Path content = Paths.get(fileLocation);
            InputStream is = Files.newInputStream(content);
            Allure.addAttachment("my attachment" , is);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
