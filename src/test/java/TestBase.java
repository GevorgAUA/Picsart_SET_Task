import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Files;

/**
 * Base abstract class for all test classes. Creates a threadSafe driver which is compatible to use for
 * parallel testing, uses threadlocal driver to avoid race conditions.
 */

public abstract class TestBase {
    protected ThreadLocal<WebDriver> driverThreadSafe = new ThreadLocal<WebDriver>();
    protected WebDriver getDriverThreadSafe(){
        return driverThreadSafe.get();
    }

    /**
     * explicit wait for the specified number of seconds.
     */
    public void waitExplicitly(int seconds) throws InterruptedException {
        Thread.sleep(seconds*1000);
    }

    /**
     * executed before all test methods, sets up the threadsafe driver, needs the path of the chrome driver.
     */
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",  "PATH TO YOUR DRIVER");
        driverThreadSafe.set(new ChromeDriver());
    }

    /**
     * executed after all test methods, takes a screenshot after failure of test before quitting and saves to
     * a folder, if missing creates a folder.
     */
    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (!result.isSuccess()) {
            try {

                File scrFile = ((TakesScreenshot) driverThreadSafe.get()).getScreenshotAs(OutputType.FILE);

                File screenshotsDir = new File("screenshots");
                if (!screenshotsDir.exists()) {
                    screenshotsDir.mkdirs();
                }

                File destFile = new File(screenshotsDir, result.getName() +  "_"+ System.currentTimeMillis()+".png" );

                Files.copy(scrFile.toPath(), destFile.toPath());
                System.out.println("Screenshot taken: " + destFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error while taking screenshot: " + e.getMessage());
            }
        }
    }

    /**
     * executed after all test methods, quits the driver after test ensuring that test are independent if multiple
     */
    @AfterMethod
    public void tearDown()  {
        if (driverThreadSafe != null) {
            driverThreadSafe.get().quit();
        }
    }
}