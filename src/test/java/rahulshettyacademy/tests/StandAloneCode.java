package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneCode {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//new comments are added
		String productName="ADIDAS ORIGINAL";
		String countryName =" India";
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\reddy\\OneDrive\\Documents\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		WebDriver Driver = new ChromeDriver();
		Driver.manage().window().maximize();
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		Driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		Driver.get("https://rahulshettyacademy.com/client");
		
		//Login Page
		LandingPage landingPage = new LandingPage(Driver);
		Driver.findElement(By.id("userEmail")).sendKeys("minnesota.mn62@gmail.com");
		Driver.findElement(By.id("userPassword")).sendKeys("Minnesota@123");
		Driver.findElement(By.id("login")).click();
		
		//Products Page
		WebDriverWait wait = new WebDriverWait(Driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		
		List<WebElement> products = Driver.findElements(By.cssSelector("div[class='card-body']"));
		WebElement productSelected = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		productSelected.findElement(By.cssSelector("button[class='btn w-10 rounded'] i[class='fa fa-shopping-cart']")).click();
		
		//Waiting till the product Added to Cart is Displayed
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfAllElements(Driver.findElements(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
		
		//Click on the cart Button present on top of page
		Driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		//Cart Checkout Page
		List<WebElement> checkoutProduct = Driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		Boolean match = checkoutProduct.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		// Alternate way
		/*
		System.out.println(checkoutProduct);
		Assert.assertEquals(productName, checkoutProduct);
		*/		
		//clicking on checkout button in checkout page
		Driver.findElement(By.cssSelector("div:nth-child(2) ul li:nth-child(3) button[class='btn btn-primary']")).click();
		
		//Payment page
		Actions a = new Actions(Driver);
		a.sendKeys(Driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("i[class='fa fa-search']")));
		Driver.findElement(By.cssSelector(".ta-item.list-group-item.ng-star-inserted:last-child")).click();
		
		JavascriptExecutor jse = (JavascriptExecutor)Driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");		
		
		//Thread.sleep(2000);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(Driver.findElement(By.cssSelector(".container a"))));
	//	jse.executeScript("arguments[0].click();", element);
		element.click();
		
		
		//Thankyou Page Information
		Assert.assertEquals(Driver.findElement(By.className("hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		
		Driver.close();
		
		
	}

}
