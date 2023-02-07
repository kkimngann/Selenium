package tests;

import Driver.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeTest
    public void initBrowserSession(){
        driver = DriverFactory.getChromeDriver();
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession(){
        if(driver != null){
            driver.quit();
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
