package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver Driver;
	public CheckoutPage(WebDriver Driver)
	{
		// initialization code
		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement placeOrder;

	@FindBy(css = "[placeholder='Select Country']")
    WebElement country;

	@FindBy(xpath = "//button[2]//span[1]")
	 WebElement selectCountry;
	
	@FindBy(css = ".ta-results.list-group.ng-star-inserted")
	 WebElement results;
	
	//By placeOrder = By.cssSelector(".btnn.action__submit.ng-star-inserted");
	//By results = By.cssSelector(".ta-results.list-group.ng-star-inserted");

	public void selectCountry(String countryName) {
		Actions a = new Actions(Driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
		//Thread.sleep(2000);
		
	}
	
	

	public ConfirmationPage submitOrder() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor) Driver;
	     jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		//waitForElementToClick(placeOrder);
		placeOrder.click();
		return new ConfirmationPage(Driver);
		
		
	}

}
