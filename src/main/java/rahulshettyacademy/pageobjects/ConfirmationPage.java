package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver Driver;
	public ConfirmationPage(WebDriver Driver) {
		
		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}	
		@FindBy(css =".hero-primary")
		WebElement confirmationMessage;
		
		public String getConfirmationMessage()
		{
			return confirmationMessage.getText();
		}
	}


