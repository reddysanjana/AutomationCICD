package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTests extends BaseTest {

		
		@Test(groups = {"ErrorHandling"}, retryAnalyzer = rahulshettyacademy.TestComponents.Retry.class)
		public void loginErrorValidation() throws IOException, InterruptedException
		{
			
			String productName="ADIDAS ORIGINAL";
			String countryName ="India";
		
		//Products Page
		landingPage.loginApplication("minnesota.mn62@gmail.com", "Minnesota123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
		
		@Test
		public void productErrorValidation() throws IOException, InterruptedException
		{
			String productName="ADIDAS ORIGINAL";
			String countryName ="India";
		
		//Products Page
		ProductCatalogue productCatalogue = landingPage.loginApplication("shetty@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
		
		//Click on the cart Button present on top of page
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Cart Checkout Page
		Boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINAL1");
		Assert.assertFalse(match);
	
		
		
		
		
	}
}

