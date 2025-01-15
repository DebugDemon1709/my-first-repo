package demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRMbyChatGPT {
    public static void main(String[] args) {
        // Setup WebDriverManager to manage ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        
        // Create an instance of ChromeDriver (this launches Chrome)
        WebDriver driver = new ChromeDriver();
        
        try {
            // Navigate to the OrangeHRM demo login page
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            
            // Maximize the browser window
            driver.manage().window().maximize();
            
            // Wait for page elements to load (optional, you can add more specific waits later)
            Thread.sleep(2000);
            
            // Locate the username and password fields, and login button
            WebElement usernameField = driver.findElement(By.name("username"));
            WebElement passwordField = driver.findElement(By.name("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            
            // Enter the username and password
            usernameField.sendKeys("Admin");
            passwordField.sendKeys("admin123");
            
            // Click the login button
            loginButton.click();
            
            // Add a wait to see the result (in real cases, use explicit waits)
            Thread.sleep(3000);

            // Optionally, print the title of the landing page after login
            System.out.println("Logged in, page title: " + driver.getTitle());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Close the browser after use
            driver.quit();
        }
    }
}
