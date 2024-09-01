import org.openqa.selenium.Dimension;

import org.testng.annotations.Test;
import POMs.*;
import org.testng.Assert;

import static Utilities.AssertionMessages.*;
import static Utilities.Parameters.SUT_URL;

/**
 * Test class containg the only test. extends the base test class to inherit all its conditions.
 * makes use of data provider to provide multiple resoultions and run the same test multiple times
 * avoiding code duplicates. Detailed steps are provided in readme file.
 */

public class TestPicsart extends TestBase {

    @Test(dataProvider = "resolutions", dataProviderClass = DataProvider.class)
    public void testTask(int width, int height) throws InterruptedException {
        //setup, opens the link by getting a thread safe driver. sets size according to given width and height from data provider
        //starts in homepage, hence creates an instance of it in the setup.
        getDriverThreadSafe().get(SUT_URL);
        getDriverThreadSafe().manage().window().setSize(new Dimension(width, height));
        HomePage homePage = new HomePage(driverThreadSafe.get());

        //goes to search page from homepage, provides the singletone driver and creates an instance of searchpage.
        //accepts cookies to avoid UI overlapping.
        SearchPage searchPage = homePage.goToSearchPage();
        searchPage.acceptCookies();

        //since the smallest resolution has the initial bar closed, only for it first opens it to have same start.
        if (width == 1024 && height == 768 && !searchPage.isFilterBarOpen()) {
            searchPage.clickFilterButton();
        }

        //closes the filter bar and asserts that it is closed.
        searchPage.clickFilterButton();
        Assert.assertFalse(searchPage.isFilterBarOpen(), WRONG_FILTER_OPEN_STATE_MESSAGE);

        //opens the filter bar, checks the personal filter. for small resolution closes the filter bar.
        searchPage.clickFilterButton()
                .checkPersonalFilter();
        if (width == 1024 && height == 768) {
            searchPage.clickFilterButton();
        }
        //uses explicit wait to wait for all personal items to load, asserts that no premium items are present.
        waitExplicitly(2);
        Assert.assertFalse(searchPage.hasPremiumItems(), PREMIUM_EXISTS_MESSAGE);

        //hovers over first item and asserts that it has correct three options displayed.
        Assert.assertTrue(searchPage.hoverOverFirstItem()
                .checkIfFirstItemThreeOptionsAreShown(), WRONG_OPTIONS_SHOWN_FOR_DEFAULT_ITEM_MESSAGE);

        //clicks on like button for the previously hovered item, asserts that popup comes up.
        Assert.assertTrue(searchPage.clickLikeButtonOnHoveredItem()
                .isLoginPopupOpen(), LOGIN_POPUP_NOT_OPEN_MESSAGE);

        //closes the popup.
        searchPage.closeLoginPopup();

        //For small resolutions opens the filter bar, for all resolutions clears filters.
        // for only small resolution closes the filter bar again
        if (width == 1024 && height == 768 && !searchPage.isFilterBarOpen()) {
            searchPage.clickFilterButton();
        }
        waitExplicitly(1);
        searchPage.removeAllFilters();
        if (width == 1024 && height == 768 && searchPage.isFilterBarOpen()) {
            searchPage.clickFilterButton();
        }

        //hovers over the first appeared premium item, asserts that the correct options are displayed.
        Assert.assertTrue(searchPage.hoverOverFirstPremiumItem()
                .checkIfFirstPremiumItemOptionsAreShown(), WRONG_OPTIONS_SHOWN_FOR_PREMIUM_ITEM_MESSAGE);

        //clicks try now button for the hovered image, goes to editor page, gives the driver to it and creates
        //an instance of editor page.
        EditorPage editorPage = searchPage.clickTryNowFromHoveredImage();

        //closes the popup in the editor, asserts that the canvas is visible.
        editorPage.closeLoginPopup();
        Assert.assertTrue(editorPage.isCanvasVisible(), CANVAS_NOT_VISIBLE_MESSAGE);
    }

}