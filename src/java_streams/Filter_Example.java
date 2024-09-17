package java_streams;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Filter_Example {
    // End-to-End automation example for filtering vegetables using Selenium streams in GreenKart

    public static void main(String[] args) {
        // Set up WebDriver for Chrome browser
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window

        // Navigate to GreenKart Offers page
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        // Enter "Rice" in the search field to filter products
        driver.findElement(By.id("search-field")).sendKeys("Rice");

        // Capture all the vegetable names displayed after filtering
        List<WebElement> veggies = driver.findElements(By.xpath("//tr/td[1]"));

        // ** Lecture 120 ** -> Filter the list for any vegetable containing "Rice" in its name
        List<WebElement> filteredList = veggies.stream()
                .filter(veggie -> veggie.getText().contains("Rice"))
                .collect(Collectors.toList());

        // Compare the size of the original list (filtered results) with the filtered list
        // Both lists should have the same size as we're searching specifically for "Rice"
        Assert.assertEquals(veggies.size(), filteredList.size());

        // Close the browser
        driver.quit();
    }
}
