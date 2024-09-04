package java_streams;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//lecture 152
public class EndToEnd {

	public static void main(String[] args) {
		String productName = "ZARA COAT 3";
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("srikanthv1709@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@123");
		driver.findElement(By.id("login")).click();
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));  //wait until all items are displayed
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));//lecture 153
		WebElement prod = products.stream().filter(product->  // searching ZARA COAT 3 inside "products" list
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);    
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //click on "add to cart" in prod webElement
		// lecture 154(toast message display)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));  //wait until toast message is displayed
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // wait until loading icon is invisible
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click(); //click on cart button
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));//lecture 155 : get all the item in the cart list
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));//matching all the elements in the cart page with productName
		Assert.assertTrue(match);//if item is present, then match is true, then assertion is true
	    WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
	    checkout.sendKeys(Keys.PAGE_DOWN);checkout.click();  //scroll to view checkOut
	    action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").perform(); //lecture 156 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
	    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	    driver.quit();		
	}
}











