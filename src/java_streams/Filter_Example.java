package java_streams;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Filter_Example {
// lecture 120
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.id("search-field")).sendKeys("Rice");
		List<WebElement> veggies = driver.findElements(By.xpath("//tr/td[1]"));
		//get the text of the all the vegetables  (5 results)
		List<WebElement> filteredList = veggies.stream().filter(veggie->veggie.getText().
				contains("Rice")).collect(Collectors.toList());//checking all the elements in the list where "Rice" is present in their name or not
		//filtered list is having only one result
		//1 result
		Assert.assertEquals(veggies.size(),filteredList.size());
		driver.quit();
	}
}	