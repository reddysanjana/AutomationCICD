package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PracticeGreenCart {

    public static void main(String[] args) throws InterruptedException {
        // Array of vegetables
        String[] veggie = {"Cauliflower", "Tomato", "Brinjal", "Mushroom", "Carrot"};
        
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\reddy\\OneDrive\\Documents\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        
        // Add elements from array to ArrayList
        ArrayList<String> veggies = new ArrayList<String>();
        Collections.addAll(veggies, veggie);
        
        // Print the veggies list to verify
       // System.out.println(veggies);
        
        // Find elements that match the veggies list
        List<WebElement> elements = driver.findElements(By.cssSelector("div[class='product']"))
                .stream()
                .filter(element -> {
                    String productName = element.findElement(By.cssSelector("h4[class='product-name']")).getText().split("-")[0].trim();
                    return veggies.stream().anyMatch(veggieItem -> productName.contains(veggieItem));
                })
                .collect(Collectors.toList());
   

        
        // Print the matching elements
        elements.forEach(element -> {
            String productName = element.findElement(By.cssSelector("h4[class='product-name']")).getText().split("-")[0].trim();
            System.out.println(productName);
            element.findElement(By.cssSelector("div[class='product-action']")).click();
        });  

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='added']")));
       
        Thread.sleep(2000);
        driver.findElement(By.className("cart-icon")).click();
        driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
        
       int count = elements.size();
       System.out.println(count);
       Assert.assertEquals(driver.findElements(By.cssSelector("[class='cartTable'] [class='product-name']")).size(), count);
      
      driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
      driver.findElement(By.className("promoBtn")).click();
      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class='promoInfo']")));
      driver.findElement(By.cssSelector("button:last-child")).click();
      driver.findElement(By.tagName("select")).click();
      Actions a = new Actions(driver);
      a.sendKeys("India").click().build().perform();
      
      driver.findElement(By.className("chkAgree")).click();
      driver.findElement(By.tagName("button")).click();      
     // System.out.println(driver.findElement(By.tagName("span")).getText());
      
      
    }
}