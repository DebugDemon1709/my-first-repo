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

public class Swaglabs { // End-to-End automation for SwagLabs

    public static void main(String[] args) {
        // Define the product name to be added to the cart
        String productName = "Sauce Labs Backpack";

        // Set up WebDriver for Chrome browser
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait

        // Navigate to the SwagLabs login page
        driver.get("https://www.saucedemo.com/");

        // Log in with valid credentials (standard user)
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Initialize explicit wait object
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Get the list of available products on the product page
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        // Search for the desired product (Sauce Labs Backpack) in the product list
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.id("item_4_title_link")).getText().equals(productName))
                .findFirst().orElse(null);

        // Add the selected product to the cart
        prod.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']")).click();

        // Click on the cart icon in the top right corner
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        // Wait until cart items are visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_item")));

        // Get the list of products in the cart
        List<WebElement> cartProducts = driver.findElements(By.className("inventory_item_name"));

        // Verify that the correct product is present in the cart
        boolean match = cartProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match); // Assert that the product is in the cart

        // Click on the checkout button to proceed
        driver.findElement(By.cssSelector("#checkout")).click();

        // Generate random data for first name, last name, and postal code
        String randomAlphabetic = RandomStringUtils.randomAlphabetic(8); // Random first and last name
        String randomNumeric = RandomStringUtils.randomNumeric(6); // Random postal code

        // Fill in the checkout form with random user information
        driver.findElement(By.name("firstName")).sendKeys(randomAlphabetic);
        driver.findElement(By.name("lastName")).sendKeys(randomAlphabetic);
        driver.findElement(By.name("postalCode")).sendKeys(randomNumeric);

        // Continue to the final step of the checkout process
        driver.findElement(By.id("continue")).click();

        // Click on the finish button to complete the purchase
        driver.findElement(By.id("finish")).click();

        // Verify the order confirmation message
        String confirmMessage = driver.findElement(By.cssSelector(".complete-header")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thank you for your order!")); // Confirm success message

        // Close the browser
        driver.quit();
    }
}
