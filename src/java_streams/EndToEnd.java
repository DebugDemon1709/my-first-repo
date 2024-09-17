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

// End-to-End automation for a demo e-commerce app
public class EndToEnd {

    public static void main(String[] args) {
        // Define the product to be added to the cart
        String productName = "ZARA COAT 3";

        // Set up WebDriver for Chrome browser
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait

        // Navigate to the login page of the e-commerce site
        driver.get("https://rahulshettyacademy.com/client");

        // Log in to the application
        driver.findElement(By.id("userEmail")).sendKeys("srikanthv1709@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Selenium@123");
        driver.findElement(By.id("login")).click();

        // Initialize actions class for advanced user interactions
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Explicit wait object

        // Wait until the product list is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        // Get all available products
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        // Stream through the product list and find the desired product (ZARA COAT 3)
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst()
                .orElse(null);

        // Click on the "Add to Cart" button for the selected product
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        // Wait for the toast message to appear indicating item is added to the cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        // Wait for the loading animation to disappear
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        // Navigate to the shopping cart page
        driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

        // Get all products listed in the cart
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        // Verify if the added product is present in the cart
        boolean match = cartProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match); // Assert that the product is present in the cart

        // Scroll to and click on the "Checkout" button
        WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
        checkout.sendKeys(Keys.PAGE_DOWN); // Scroll down to view the button
        checkout.click();

        // Select the country for shipping ("India")
        action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); // Wait for the dropdown results
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click(); // Select the country from the dropdown

        // Click on "Place Order" to confirm the purchase
        driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();

        // Verify the order confirmation message
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        // Close the browser
        driver.quit();
    }
}
