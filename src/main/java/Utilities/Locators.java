package Utilities;

import org.openqa.selenium.By;

public class Locators {
    public static final By FILTER_BUTTON = By.id("filter_icon");
    public static final By SEARCH_BUTTON = By.cssSelector("a[href='/search']");
    public static final By PERSONAL_CHECK_BUTTON = By.id("checkbox_filter_item2");
    public static final By PREMIUM_ITEM = By.cssSelector("[data-automation='search-item-premium']");
    public static final By SEARCH_GRID_ITEM = By.cssSelector("[data-testid='search-card-root']");
    public static final By PERSONAL_FILTER_INDICATOR = By.cssSelector("a[href*='licenses=personal']");
    public static final By LIKE_BUTTON = By.id("like_button_item0");
    public static final By SAVE_BUTTON = By.id("save_button_item0");
    public static final By TRY_NOW_BUTTON = By.id("try_now_button_item0");
    public static final By LOGIN_POPUP = By.cssSelector("[data-testid='registration-modal-container']");
    public static final By POPUP_CLOSE_ICON = By.cssSelector("[data-testid='modal-close-icon']");
    public static final By CLEAR_ALL_FILTERS_BUTTON = By.id("search-filter-header-clear");
    public static final By ACCEPT_COOKIES_BUTTON = By.id("onetrust-accept-btn-handler");
    public static final By CANVAS = By.className("konvajs-content");
}
