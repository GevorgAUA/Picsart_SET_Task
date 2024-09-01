package POMs;

import Utilities.Locators;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * serves as an abstract class for other POMs, most items in here are common to all POMs.
 */

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    /**
     * Navigates to the Search page by clicking on the search button on the main page after it's clickable.
     *
     * @return a new instance of {@link SearchPage}
     */
    public SearchPage goToSearchPage() {
        driver.switchTo().defaultContent();
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_BUTTON));
        searchButton.click();
        return new SearchPage(driver);
    }

    /**
     * Ensures that the correct frame is selected, and the Login popup is open.
     * if the popup cannot be found, returns false.
     */
    public boolean isLoginPopupOpen() {
        driver.switchTo().defaultContent();
        try{
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.LOGIN_POPUP));
        List<WebElement> popup = driver.findElements(Locators.LOGIN_POPUP);
        return !popup.isEmpty(); }
        catch (TimeoutException exception){
            return false;
        }
    }

    /**
     * finds the "X" icon and clicks on it to close the login popup.
     */
    public void closeLoginPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.POPUP_CLOSE_ICON)).click();
    }

    /**
     * finds the button for accepting cookies and clicks on it.
     */
    public void acceptCookies() {
        WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(Locators.ACCEPT_COOKIES_BUTTON));
        acceptCookies.click();
    }

}