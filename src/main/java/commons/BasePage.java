package commons;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGenerator;
import pageObjects.liveGuru.LiveGuruUserHomePageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductPreviewsPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;
import pageObjects.nopCommerce.user.UserShippingAndReturnPageObject;
import pageObjects.nopCommerce.user.UserSiteMapPageObject;
import pageObjects.nopCommerce.user.UserWishlistPageObject;
import pageUIs.hrm.BasePageUI;
import pageUIs.hrm.MyInfoPageUI;
import pageUIs.liveGuru.LiveGuruBasePageUI;
import pageUIs.nopCommerce.admin.AdminBasePageUI;
import pageUIs.nopCommerce.user.UserBasePageUI;

public class BasePage {
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;

	protected static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageỦl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToWindowByIDs(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String ID : allWindowIDs) {
			if (!ID.equals(windowID)) {
				driver.switchTo().window(ID);
			}
		}
	}

	public void switchToWindowByTitles(WebDriver driver, String windowTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String ID : allWindowIDs) {
			driver.switchTo().window(ID);
			if (driver.getTitle().equals(windowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindows(WebDriver driver, String parentID) {
		Set<String> listWindowIDs = driver.getWindowHandles();
		for (String ID : listWindowIDs) {
			if (!ID.equals(parentID)) {
				driver.switchTo().window(ID).close();
			}
			driver.switchTo().window(parentID);
		}
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(xpathLocator, params)));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, xpathLocator);
			sleepInSecond(2);
		} else {
			getWebElement(driver, xpathLocator).click();
		}
	}

	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void clickToElement(WebDriver driver, String xpathLocator, String... params) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, xpathLocator, params);
			sleepInSecond(2);
		} else {
			getWebElement(driver, getDynamicLocator(xpathLocator, params)).click();
		}
	}

	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void pressKey(WebDriver driver, String xpathLocator, Keys key, String... params) {
		WebElement element = getWebElement(driver, xpathLocator, params);
		element.sendKeys(key);
	}

	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue, String... params) {
		WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String... params) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, params)));
		select.selectByVisibleText(textItem);
	}

	public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator, String... params) {
		Select select = new Select(getWebElement(driver, getDynamicLocator(xpathLocator, params)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}

	public void actionsOnDropdownList(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		JavascriptExecutor jsExecuter = (JavascriptExecutor) driver;
		List<WebElement> listItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));
		for (WebElement element : listItems) {
			if (element.getText().equals(expectedTextItem)) {
				System.out.print(element.getText());
				jsExecuter.executeScript("arguments[0].scrollIntoView(true);", element);
				sleepInSecond(2);
				element.click();
				break;
			}
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);

		} catch (InterruptedException e) {

		}
	}

	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}

	public String getElementText(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).getText().trim();
	}

	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	public int getElementSize(WebDriver driver, String xpathLocator, String... params) {
		return getListWebElement(driver, getDynamicLocator(xpathLocator, params)).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator, String... params) {
		WebElement element = getWebElement(driver, getDynamicLocator(xpathLocator, params));
		if (!element.isSelected()) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, xpathLocator, params);
				sleepInSecond(3);
			} else {
				element.click();
			}
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		try {
			System.out.println("Start time" + new Date().toString());
			overrideGlobalTimeout(driver, shortTimeOut);
			return getWebElement(driver, xpathLocator).isDisplayed();
		} catch (Exception e) {
			return false;
		} finally {
			overrideGlobalTimeout(driver, longTimeOut);
			System.out.println("End time" + new Date().toString());
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, shortTimeOut);
		System.out.println("Start time" + new Date().toString());
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		overrideGlobalTimeout(driver, longTimeOut);
		if (elements.size() == 0) {
			System.out.println("Element does not display and does not exist in DOM");
			System.out.println("End time" + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !(elements.get(0).isDisplayed())) {
			System.out.println("Element does not display but exist in DOM");
			System.out.println("End time" + new Date().toString());
			return true;
		} else {
			System.out.println("Element displays and exists in DOM");
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator, String... params) {
		return getWebElement(driver, getDynamicLocator(xpathLocator, params)).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator, String... params) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicLocator(xpathLocator, params))).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style',arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style',arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator, String... params) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicLocator(xpathLocator, params)));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String attributeRemove, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String attributeRemove, String xpathLocator, String... params) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, getDynamicLocator(xpathLocator, params)));
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean areExpectedTextInInnnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !='undefined' && arguments[0].naturalWidth >0  ", getWebElement(driver, xpathLocator));
	}

	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		JavascriptExecutor jsExecuter = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecuter.executeScript("return(window.jQuery!=null) && (jQuery.active==0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForElementVisibleFluentWait(WebDriver driver, String xpathLocator, String... params) {
		FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(15000)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);
		fluentDriver.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	public void waitForElementInVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public void waitForElementInVisible(WebDriver driver, String xpathLocator, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public void waitForAllElementsInVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator, String... params) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, params))));
	}

	public UserMyProductPreviewsPageObject openMyProductPreviewsPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.MY_PRODUCT_REVIEWS_LINK);
		clickToElement(driver, UserBasePageUI.MY_PRODUCT_REVIEWS_LINK);
		return PageGeneratorManager.getUserProductReviewsPage(driver);
	}

	public UserRewardPointsPageObject openRewardPointsPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.REWARD_POINTS_LINK);
		clickToElement(driver, UserBasePageUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointsPage(driver);
	}

	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ADDRESSES_LINK);
		clickToElement(driver, UserBasePageUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserCustomerInfoPageObject openCustomerInfoPageOnHeader(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.USER_LOGOUT_LINK);
		clickToElement(driver, UserBasePageUI.USER_LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ADMIN_LOGOUT_LINK);
		clickToElement(driver, UserBasePageUI.ADMIN_LOGOUT_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}

	public UserSearchPageObject openSearchPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.SEARCH_LINK);
		clickToElement(driver, UserBasePageUI.SEARCH_LINK);
		return PageGeneratorManager.getUserSearchPage(driver);
	}

	public UserShippingAndReturnPageObject openShippingAndSearchPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.SHIPPING_AND_RETURN_LINK);
		clickToElement(driver, UserBasePageUI.SHIPPING_AND_RETURN_LINK);
		return PageGeneratorManager.getUserShippingAndReturnPage(driver);
	}

	public UserSiteMapPageObject openSiteMapPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.SITEMAP_LINK);
		clickToElement(driver, UserBasePageUI.SITEMAP_LINK);
		return PageGeneratorManager.getUserSiteMapPage(driver);
	}

	public UserWishlistPageObject openWishlistPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.HEADER_WISHLIST_LINK);
		clickToElement(driver, UserBasePageUI.HEADER_WISHLIST_LINK);
		return PageGeneratorManager.getUserWishlistPage(driver);
	}

	public UserLoginPageObject openLoginPageObject(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.FOOTER_MY_ACCOUNT_LINK);
		clickToElement(driver, UserBasePageUI.FOOTER_MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public BasePage openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		if (pageName.equals("Search")) {
			return PageGeneratorManager.getUserSearchPage(driver);
		} else if (pageName.equals("My account")) {
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		} else {
			return PageGeneratorManager.getUserHomePage(driver);
		}
	}

	public void getFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
	}

	public void getHeaderPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
	}

	public void openSubMenuPage(WebDriver driver, String categoryName, String subCategoryName) {
		waitForElementClickable(driver, AdminBasePageUI.DASHBOARD_MENU_BY_NAME, categoryName);
		if (driver.toString().contains("firefox")) {
			sleepInSecond(10);
		}
		clickToElement(driver, AdminBasePageUI.DASHBOARD_MENU_BY_NAME, categoryName);
		waitForElementClickable(driver, AdminBasePageUI.DASHBOARD_SUB_MENU_BY_NAME, subCategoryName);
		clickToElement(driver, AdminBasePageUI.DASHBOARD_SUB_MENU_BY_NAME, subCategoryName);
	}

	public void openAddNewItemPage(WebDriver driver, String itemName) {
		waitForElementClickable(driver, AdminBasePageUI.ADD_NEW_ITEM, itemName);
		if (driver.toString().contains("firefox")) {
			clickToElementByJS(driver, AdminBasePageUI.ADD_NEW_ITEM, itemName);
		} else {
			clickToElement(driver, AdminBasePageUI.ADD_NEW_ITEM, itemName);
		}
	}

	public void addNewItem(WebDriver driver, String value) {
		waitForElementVisible(driver, AdminBasePageUI.ITEM_NAME_TEXTBOX);
		if (driver.toString().contains("firefox")) {
			sleepInSecond(5);
		}
		getWebElement(driver, AdminBasePageUI.ITEM_NAME_TEXTBOX).sendKeys(value);
		waitForElementClickable(driver, AdminBasePageUI.NEW_ITEM_SAVE_BUTTON);
		if (driver.toString().contains("firefox")) {
			clickToElementByJS(driver, AdminBasePageUI.NEW_ITEM_SAVE_BUTTON);
		} else {
			clickToElement(driver, AdminBasePageUI.NEW_ITEM_SAVE_BUTTON);
		}
	}

	public void expandPanelByCardName(WebDriver driver, String cardName) {
		String iconStatus = getElementAttribute(driver, AdminBasePageUI.TOGGLE_ICON_BY_CARD_NAME, "class", cardName);
		if (iconStatus.contains("fa-plus")) {
			waitForElementClickable(driver, AdminBasePageUI.TOGGLE_ICON_BY_CARD_NAME, cardName);
			clickToElement(driver, AdminBasePageUI.TOGGLE_ICON_BY_CARD_NAME, cardName);
		}
	}

	public void uploadMultipleFiles(WebDriver driver, String cardName, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, AdminBasePageUI.UPLOAD_FILE_BUTTON_BY_CARD_NAME, cardName).sendKeys(fullFileName);
	}

	public boolean isUploadedFileDisplayed(WebDriver driver, String fileName) {
		waitForElementVisible(driver, AdminBasePageUI.UPLOADED_FILE_TITLE, fileName);
		return isElementDisplayed(driver, AdminBasePageUI.UPLOADED_FILE_TITLE, fileName);
	}

	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendKeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}

	public void clickToRadioButtonByID(WebDriver driver, String radioButtonID) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonID);
		clickToElement(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonID);
	}

	public void selectDropdownByName(WebDriver driver, String dropdownName, String value) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		selectItemInDefaultDropdown(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, value, dropdownName);
	}

	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}

	public void clickToAccountLink(WebDriver driver) {
		waitForElementClickable(driver, LiveGuruBasePageUI.ACCOUNT_LINK);
		clickToElement(driver, LiveGuruBasePageUI.ACCOUNT_LINK);
	}

	public LiveGuruUserHomePageObject clickToLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, LiveGuruBasePageUI.LOGOUT_LINK);
		clickToElement(driver, LiveGuruBasePageUI.LOGOUT_LINK);
		return new LiveGuruUserHomePageObject(driver);
	}

	// HRM - Menu
	public void openMenuPageHRM(WebDriver driver, String menuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

		isJQueryLoadedSuccess(driver);
	}

	// Sub Menu
	public void openSubMenuPageHRM(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		if (driver.toString().contains("internet explorer")) {
			openPageUrl(driver, getElementAttribute(driver, BasePageUI.MENU_BY_PAGE_NAME, "href", menuPageName));
		} else {
			clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		}
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		if (driver.toString().contains("internet explorer")) {
			openPageUrl(driver, getElementAttribute(driver, BasePageUI.MENU_BY_PAGE_NAME, "href", subMenuPageName));
		} else {
			clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		}
		isJQueryLoadedSuccess(driver);

		if (driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		}
	}

	// Child Sub Menu
	public void openChildSubMenuPageHRM(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		hoverMouseToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);

		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);

		isJQueryLoadedSuccess(driver);

		if (driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		}
	}

	// Button
	public void clickToButtonByID(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonName);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonName);
		sleepInSecond(3);
	}

	// Enter Textbox
	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		// getWebElement(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName).sendKeys(value);
		sendKeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxIDName);
	}

	// Get Textbox Value

	/**
	 * Get textbox value by textbox ID
	 * 
	 * @param driver
	 * @param textboxIDName
	 * @return attribute value
	 * @author thule
	 */
	public String getTextboValueByID(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxIDName);
	}

	// Dropdown
	public void selectDropdownByID(WebDriver driver, String dropdownIDName, String value) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropdownIDName);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, value, dropdownIDName);
	}

	// Get first selected value

	/**
	 * Get selected text item in dropdown
	 * 
	 * @author thule
	 * @param driver
	 * @param dropdownIDName
	 * @return selected text in dropdown
	 */
	public String getSelectedValueInDropdownByID(WebDriver driver, String dropdownIDName) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownIDName);
		return getFirstSelectedItemDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, dropdownIDName);
	}

	/**
	 * Click to checkbox by label
	 * 
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}

	public void clickToRadioByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxRadio(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
	}

	public String getValueDisplayedInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String columnName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_INDEX_BY_ID_AND_COLUMN_HEADER, tableID, columnName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_ROW_AND_COLUMN_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_ROW_AND_COLUMN_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}

	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.WELCOME_USER_LINK);
		clickToElement(driver, BasePageUI.WELCOME_USER_LINK);

		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		return PageGenerator.getLoginPage(driver);
	}

	public DashboardPO loginToSystem(WebDriver driver, String userName, String password) {
		waitForElementVisible(driver, BasePageUI.USERNAME_TEXTBOX);
		getWebElement(driver, BasePageUI.USERNAME_TEXTBOX).sendKeys(userName);
		getWebElement(driver, BasePageUI.PASSWORD_TEXTBOX).sendKeys(password);
		clickToElement(driver, BasePageUI.LOGIN_BUTTON);

		if (driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		}
		return PageGenerator.getDashboardPage(driver);
	}

	public void uploadImage(WebDriver driver, String filePath) {
		sendKeyToElement(driver, BasePageUI.UPLOAD_IMAGE, filePath);
	}

	public boolean isSuccessMessageDisplayed(WebDriver driver, String successMessage) {
//		waitForElementVisibleFluentWait(driver, BasePageUI.SUCCESS_MESSAGE_VALUE, successMessage);
//		return isElementDisplayed(driver, BasePageUI.SUCCESS_MESSAGE_VALUE, successMessage);
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(10000)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(getByXpath(getDynamicLocator(BasePageUI.SUCCESS_MESSAGE_VALUE, successMessage)));
			}
		});

		return element.isDisplayed();
	}

	public boolean isFieldEnabledByID(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
		return isElementEnabled(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
	}

	public boolean isRadioButtonSelected(WebDriver driver, String radioLabel) {
		waitForElementVisible(driver, BasePageUI.RADIO_BY_LABEL, radioLabel);
		return isElementSelected(driver, BasePageUI.RADIO_BY_LABEL, radioLabel);
	}
}
