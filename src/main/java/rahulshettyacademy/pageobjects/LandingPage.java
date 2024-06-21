package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver Driver;
	public LandingPage(WebDriver Driver)
	{
		// initialization code
		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//PageFactory Initialization
	
	@FindBy(id ="userEmail")
	WebElement userEmail;
	
	@FindBy(id ="userPassword")
	WebElement userPassword;
	
	@FindBy(id ="login")
	WebElement login;
	
	//.ng-tns-c4-9.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(Driver);
		return productCatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		Driver.get("https://rahulshettyacademy.com/client");
		
	}
	

}
