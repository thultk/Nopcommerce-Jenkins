package pageObjects.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.InventoryPageUI;

public class InventoryPageObject extends BasePage {
	WebDriver driver;

	public InventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectIteminSortDropdown(String itemName) {
		waitForElementClickable(driver, InventoryPageUI.SORT_DROPDOWN_BY_NAME);
		selectItemInDefaultDropdown(driver, InventoryPageUI.SORT_DROPDOWN_BY_NAME, itemName);
	}

	public boolean isProductNameSortByAscending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		List<String> productNameText = new ArrayList<String>();
		for (WebElement productElement : productNameElements) {
			productNameText.add(productElement.getText());
		}

		System.out.println("Product name before sort ASC");
		for (String proNameBeforeSort : productNameText) {
			System.out.println(proNameBeforeSort);
		}

		List<String> productNameTextClone = new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);

		System.out.println("Product name after sort ASC");
		for (String proNameAfterSort : productNameTextClone) {
			System.out.println(proNameAfterSort);
		}

		return productNameTextClone.equals(productNameText);
	}

	public boolean isProductNameSortByDescending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		List<String> productNameText = new ArrayList<String>();
		for (WebElement productElement : productNameElements) {
			productNameText.add(productElement.getText());
		}

		System.out.println("Product name before sort DSC");
		for (String proNameBeforeSort : productNameText) {
			System.out.println(proNameBeforeSort);
		}

		List<String> productNameTextClone = new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);

		System.out.println("Product name after sort DSC");
		for (String proNameAfterSort : productNameTextClone) {
			System.out.println(proNameAfterSort);
		}

		return productNameTextClone.equals(productNameText);
	}

	public boolean isProductPriceSortByAscending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		List<Float> productPriceNumber = new ArrayList<Float>();
		for (WebElement productPriceElement : productPriceElements) {
			productPriceNumber.add(Float.parseFloat(productPriceElement.getText().replace("$", "")));
		}

		System.out.println("Product name before sort ASC");
		for (float proNameBeforeSort : productPriceNumber) {
			System.out.println(proNameBeforeSort);
		}

		List<Float> productPriceTextClone = new ArrayList<Float>(productPriceNumber);
		Collections.sort(productPriceTextClone);

		System.out.println("Product name after sort ASC");
		for (float proNameAfterSort : productPriceTextClone) {
			System.out.println(proNameAfterSort);
		}

		return productPriceTextClone.equals(productPriceNumber);
	}

	public boolean isProductPriceSortByDescending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		List<Float> productPriceNumber = new ArrayList<Float>();
		for (WebElement productPriceElement : productPriceElements) {
			productPriceNumber.add(Float.parseFloat(productPriceElement.getText().replace("$", "")));
		}

		System.out.println("Product name before sort ASC");
		for (float proNameBeforeSort : productPriceNumber) {
			System.out.println(proNameBeforeSort);
		}

		List<Float> productPriceTextClone = new ArrayList<Float>(productPriceNumber);
		Collections.sort(productPriceTextClone);
		Collections.reverse(productPriceTextClone);

		System.out.println("Product name after sort ASC");
		for (float proNameAfterSort : productPriceTextClone) {
			System.out.println(proNameAfterSort);
		}

		return productPriceTextClone.equals(productPriceNumber);
	}

}
