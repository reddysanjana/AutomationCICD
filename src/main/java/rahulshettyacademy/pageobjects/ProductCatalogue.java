package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver Driver;
	public ProductCatalogue(WebDriver Driver)
	{
		// initialization code
		super(Driver);
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//PageFactory Initialization
		
	@FindBy(css ="div[class='card-body']")
	List<WebElement> products;
	
	@FindBy(css =".ng-animating")
	WebElement spinner;


	@FindBy(css=".container")
	WebElement productsBy;

	@FindBy(css="#toast-container")
	WebElement toastMessage;
	
	//By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button[class='btn w-10 rounded'] i[class='fa fa-shopping-cart']");
	//By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	

}
