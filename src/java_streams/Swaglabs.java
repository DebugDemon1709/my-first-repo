package java_streams;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Swaglabs { // END TO END 

	public static void main(String[] args) {
		String productName = "Sauce Labs Backpack";
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item']")));  //wait until are elements are loaded
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item']"));
		WebElement prod = products.stream().filter(product->  // searching ZARA COAT 3 inside "products" list
		product.findElement(By.id("item_4_title_link")).getText().equals(productName)).findFirst().orElse(null); 
		prod.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']")).click(); //click on "add to cart" in prod webElement
		driver.findElement(By.cssSelector(".shopping_cart_link")).click();//click on cart button on right top corner
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_item")));
		List<WebElement> cartProducts = driver.findElements(By.className("inventory_item_name"));//list of cart items in cart page 
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));//matching productName with cart items
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("#checkout")).click();//click on checkout button
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(8); //getting random Alphabetic string
		String randomNumeric = RandomStringUtils.randomNumeric(6); //getting random Numeric String
		driver.findElement(By.name("firstName")).sendKeys(randomAlphabetic);
		driver.findElement(By.name("lastName")).sendKeys(randomAlphabetic);
		driver.findElement(By.name("postalCode")).sendKeys(randomNumeric);
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("finish")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".complete-header")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thank you for your order!"));
		driver.quit();	
	}
}