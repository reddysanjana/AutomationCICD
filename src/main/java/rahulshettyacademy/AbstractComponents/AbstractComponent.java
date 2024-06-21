package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver Driver;
	
	public AbstractComponent(WebDriver Driver)
	{
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(css ="button[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	/*
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(Driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}*/
	
	public void waitForElementToAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(Driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void waitForElementToClick(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(Driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException
	{
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(Driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(Driver);
		return cartPage;
		
	}
	
	public OrdersPage goToOrdersPage()
	{
		orderHeader.click();
		OrdersPage orderPage = new OrdersPage(Driver);
		return orderPage;
	}
	
	
	


}
