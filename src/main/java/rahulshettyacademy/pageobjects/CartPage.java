package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver Driver;
	public CartPage(WebDriver Driver)
	{
		// initialization code
		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//PageFactory Initialization
		
	@FindBy (css ="div[class='cartSection'] h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css="div:nth-child(2) ul li:nth-child(3) button[class='btn btn-primary']")
	WebElement checkoutEle;
	
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
		
	public CheckoutPage goToCheckout()
	{
		
		checkoutEle.click();
		return new CheckoutPage(Driver);
	}

}
