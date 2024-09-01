package POMs;

import Utilities.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * extends the basepage class and gets the singleton instance of the driver.
 */

public class SearchPage extends BasePage{

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * ensures that the correct frame is selected, clicks on the filters button after its clickable
     * returns itself.
     */
    public SearchPage clickFilterButton()  {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        WebElement filterIcon = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_BUTTON));
        filterIcon.click();
        return this;
    }

    /**
     * ensures that the correct frame is selected, and clicks on the check for
     * personal filter after it's clickable.
     */
    public void checkPersonalFilter() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        WebElement check = wait.until(ExpectedConditions.elementToBeClickable(Locators.PERSONAL_CHECK_BUTTON));
        check.click();
    }

    /**
     *ensures correct frame is selected, and clicks on the clear all filters button after it's clickable.
     */
    public void removeAllFilters() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        WebElement clear = wait.until(ExpectedConditions.elementToBeClickable(Locators.CLEAR_ALL_FILTERS_BUTTON));
        clear.click();
    }

    /**
     * checks if the filter bar is open or not by checking its attribute responsible for its state.
     * returns true if its open.
     */
    public boolean isFilterBarOpen() {
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

        WebElement filterIcon = wait.until(ExpectedConditions.elementToBeClickable(Locators.FILTER_BUTTON));
        String dataAutomationValue = filterIcon.getAttribute("data-automation");
        return "open".equals(dataAutomationValue);
    }

    /**
     * waits until personal type items are loaded, then checks if any items have the premium
     * data-automation attribute. if so returns true, else returns false.
     */
    public boolean hasPremiumItems() {
        wait.until(ExpectedConditions.presenceOfElementLocated(Locators.PERSONAL_FILTER_INDICATOR));
        // Locate all items in the list using the identifier
        List<WebElement> items = driver.findElements(Locators.SEARCH_GRID_ITEM);

        for (WebElement item : items) {
            // Check if the data-automation attribute equals "search-item-premium"
            String dataAutomationValue = item.getAttribute("data-automation");

            if ("search-item-premium".equals(dataAutomationValue)) {
                // If a premium item is found, return true
                return true;
            }
        }

        // If no premium items were found, return false
        return false;
    }

    /**
     * hovers over the first item on the search page. returns itself.
     */
    public SearchPage hoverOverFirstItem() {
        Actions actions = new Actions(driver);
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_GRID_ITEM));
        actions.moveToElement(item).perform();
        return this;
    }

    /**
     * hovers over the first premium item on the search page. returns itself.
     */
    public SearchPage hoverOverFirstPremiumItem() {
        Actions actions = new Actions(driver);
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(Locators.PREMIUM_ITEM));
        actions.moveToElement(item).perform();
        return this;
    }

    /**
     * checks if the three options are shown on the first item, and are uniquely available and returns true.
     * else returns false. applicable for non premium items.
     */
    public boolean checkIfFirstItemThreeOptionsAreShown() {
        Actions actions = new Actions(driver);
        WebElement tryNow = driver.findElement(Locators.TRY_NOW_BUTTON);
        actions.moveToElement(tryNow).perform();
        // Check LIKE_BUTTON
        List<WebElement> likeButtons = driver.findElements(Locators.LIKE_BUTTON);
        if (likeButtons.size() != 1) {
            return false;  // LIKE_BUTTON is either missing or not unique
        }

        // Check SAVE_BUTTON
        List<WebElement> saveButtons = driver.findElements(Locators.SAVE_BUTTON);
        if (saveButtons.size() != 1) {
            return false;  // SAVE_BUTTON is either missing or not unique
        }

        // Check TRY_NOW_BUTTON
        List<WebElement> tryNowButtons = driver.findElements(Locators.TRY_NOW_BUTTON);
        if (tryNowButtons.size() != 1) {
            return false;  // TRY_NOW_BUTTON is either missing or not unique
        }

        // All elements are present and unique
        return true;
    }

    /**
     * checks if the two options are not shown on the first premium item, and only one is uniquely available
     * and returns true. else returns false. applicable for premium items.
     */
    public boolean checkIfFirstPremiumItemOptionsAreShown() {
        Actions actions = new Actions(driver);
        WebElement tryNow = driver.findElement(Locators.TRY_NOW_BUTTON);
        actions.moveToElement(tryNow).perform();

        // Check LIKE_BUTTON
        List<WebElement> likeButtons = driver.findElements(Locators.LIKE_BUTTON);
        if (!likeButtons.isEmpty()) {
            return false;
        }

        // Check SAVE_BUTTON
        List<WebElement> saveButtons = driver.findElements(Locators.SAVE_BUTTON);
        if (!saveButtons.isEmpty()) {
            return false;
        }

        // Check TRY_NOW_BUTTON
        List<WebElement> tryNowButtons = driver.findElements(Locators.TRY_NOW_BUTTON);
        if (tryNowButtons.size() != 1) {
            return false;  // TRY_NOW_BUTTON is either missing or not unique
        }

        // All elements are present and unique
        return true;
    }

    /**
     * clicks the like button on the hovered item and returns itself.
     */
    public SearchPage clickLikeButtonOnHoveredItem(){
        WebElement likeButton = driver.findElement(Locators.LIKE_BUTTON);
        likeButton.click();
        return this;
    }

    /**
     * clicks the try now button on the hovered item and returns the editor page indicating a transition
     * to the editor page. Provides the singletone instance of the driver to the new page.
     */
    public EditorPage clickTryNowFromHoveredImage(){
        Actions actions = new Actions(driver);
        WebElement tryNowButton = driver.findElement(Locators.TRY_NOW_BUTTON);
        actions.moveToElement(tryNowButton).perform();
        tryNowButton.click();
        return new EditorPage(driver);
    }
}
