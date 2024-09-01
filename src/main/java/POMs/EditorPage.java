package POMs;

import Utilities.Locators;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * extends the basepage class and gets the singleton instance of the driver.
 */

public class EditorPage extends BasePage{

    public EditorPage(WebDriver driver) {
        super(driver);
    }

    /**
     * waits until the canvas is visible and returns true, else returns false by cathing the timeout exception
     * caused by not finding the element
     */
    public boolean isCanvasVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.CANVAS));
            return true;
        }
        catch (TimeoutException exception) {
            return false;
        }
    }

}
