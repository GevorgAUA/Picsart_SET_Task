package POMs;

import org.openqa.selenium.WebDriver;
/**
 * extends the basepage class and gets the singleton instance of the driver.
 */
public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

}
