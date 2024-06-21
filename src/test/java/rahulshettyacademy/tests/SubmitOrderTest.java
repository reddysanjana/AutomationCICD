package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName="ADIDAS ORIGINAL";
	String countryName ="India";

		@Test(dataProvider = "getData", groups = "Purchase")
		//Data Provider using Arrays
		//public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException
		
		//Data Provider using HashMap
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
		{
			
		
		//Products Page
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		
		
		//Click on the cart Button present on top of page
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Cart Checkout Page
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
	
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		//ConfirmationPAge
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
		
		//dependsOnMethods= {"submitOrder"}
		//To verify that ADIDAS ORIGINAL is displayed in the orders page.
		@Test(dependsOnMethods= {"submitOrder"}, groups = "debug")
		public void OrderHistoryTest()
		{
			//"ADIDAS ORIGINAL";
			ProductCatalogue productCatalogue = landingPage.loginApplication("minnesota.mn62@gmail.com", "Minnesota@123");
			OrdersPage ordersPage = productCatalogue.goToOrdersPage();
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
			
			
	}

		@DataProvider
		//DAta Provider using json
		public Object[][] getData() throws IOException
		{
		
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		
		}
		
		
		// data provider not only allows arrays but it also supports arrays.
		/*
		// Data Provider using Arrays
		public Object[][] getData()
		{
			return new Object[][] {{"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"},{"minnesota.mn62@gmail.com", "Minnesota@123", "ZARA COAT 3"}};
		}
		*/
		
		
		/*
		// Data Provider using HashMap
		public Object[][] getData()
		{
			HashMap<Object,Object> map= new HashMap<Object,Object>();
			map.put("email", "anshika@gmail.com");
			map.put("password", "Iamking@000");
			map.put("productName", "IPHONE 13 PRO");
			
			HashMap<Object,Object> map1= new HashMap<Object,Object>();
			map1.put("email", "minnesota.mn62@gmail.com");
			map1.put("password", "Minnesota@123");
			map1.put("productName", "ADIDAS ORIGINAL");
			return new Object[][] {{map},{map1}};
		}
		*/
}

