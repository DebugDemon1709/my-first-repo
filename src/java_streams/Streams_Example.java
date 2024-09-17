package java_streams;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Streams_Example {
    // End-to-end test automation using Selenium Streams on the GreenKart offers page

    public static void main(String[] args) {
        // Set up WebDriver for Chrome browser
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize browser window
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers"); // Navigate to GreenKart Offers page

        // Click on the first column to sort it
        driver.findElement(By.xpath("//tr/th[1]")).click();

        // Capture all web elements (vegetable names) into a list
        List<WebElement> elementList = driver.findElements(By.xpath("//tr/td[1]"));

        // Capture text from each web element into a new list (original list)
        List<String> originalList = elementList.stream().map(s -> s.getText()).collect(Collectors.toList());

        // Sort the original list alphabetically to create a sorted list
        List<String> sortedlist = originalList.stream().sorted().collect(Collectors.toList());

        // Compare the original list with the sorted list to verify sorting
        Assert.assertTrue(originalList.equals(sortedlist));

        // ** Lecture 117 ** -> Scan the name column for "Rice" and print its price
        List<String> price; // Declare list to hold the price of "Rice"
        do {
            // Capture all rows in the table
            List<WebElement> rows = driver.findElements(By.xpath("//tr/td[1]"));

            // Filter the rows for the item "Rice" and fetch its price
            price = rows.stream().filter(s -> s.getText().contains("Rice")).map(s -> getVeggiPrice(s)) 
                    .collect(Collectors.toList()); // Call the method to retrieve the price

            // Print the price of "Rice" (if found)
            price.forEach(a -> System.out.println(a));

            // ** Lecture 118 ** -> Paginate to the next page if "Rice" is not found
            if (price.size() < 1) {
                driver.findElement(By.xpath("//a[@aria-label='Next']")).click(); // Click "Next" if no result on current page
            }
        } while (price.size() < 1); // Continue until "Rice" is found

        // Close the browser
        driver.quit();
    }

    // Helper method to fetch the price of a vegetable from the table
    private static String getVeggiPrice(WebElement s) {
        // Locate and return the price in the following column (sibling) for the selected vegetable
        String priceValue = s.findElement(By.xpath("following-sibling::td[1]")).getText();
        return priceValue;
    }
}

/*
 * Explanation of Lambda Expressions:
 * In lambda expressions (s -> any action), the left side 's' represents items present in a list or stream.
 * The right side represents the action performed on the list/stream (e.g., filtering, mapping).
 */
