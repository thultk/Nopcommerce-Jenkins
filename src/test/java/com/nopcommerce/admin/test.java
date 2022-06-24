package com.nopcommerce.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test {
	static WebDriver driver;
	static WebDriverWait wait;
	static String chromeDriverPath = System.getProperty("user.dir");

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath + "\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/");
		wait = new WebDriverWait(driver, 15);
		// driver.findElement(By.id("Email"));
		// driver.findElement(By.id("Password"));
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class,'nav-sidebar') and @role='menu']//i[contains(@class,'fas fa-')]//following-sibling::p[contains(string(),'Catalog')]")));
		driver.findElement(By.xpath("//ul[contains(@class,'nav-sidebar') and @role='menu']//i[contains(@class,'fas fa-')]//following-sibling::p[contains(string(),'Catalog')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='nav-item has-treeview menu-open']//i[contains(@class,'fa-dot-circle')]//following-sibling::p[contains(text(),'Products')]")));
		driver.findElement(By.xpath("//li[@class='nav-item has-treeview menu-open']//i[contains(@class,'fa-dot-circle')]//following-sibling::p[contains(text(),'Products')]")).click();
		driver.findElement(By.xpath("//h1[contains(string(),'Products')]//parent::div//div//a")).click();
	}

}
