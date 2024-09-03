package java_streams;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Streams_Example {
// lecture 116 
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		// click on the column
		driver.findElement(By.xpath("//tr/th[1]")).click();
		// capture all the webelEment into list
		List<WebElement> elementList = driver.findElements(By.xpath("//tr/td[1]"));
		// capture text of all webElements into new list(original list)
		List<String> originalList = elementList.stream().map(s -> s.getText()).collect(Collectors.toList());
		// sort in the list of step 3 --> sorted list
		List<String> sortedlist = originalList.stream().sorted().collect(Collectors.toList());
		// compare original list vs sorted list
		Assert.assertTrue(originalList.equals(sortedlist));
//lecture 117-> scan the name column with getText -> Rice -> print the price of the Beans
		List<String> price;
		do {
			List<WebElement> rows = driver.findElements(By.xpath("//tr/td[1]"));

			price = rows.stream().filter(s -> s.getText().contains("Rice")).map(s -> getVeggiPrice(s))
					.collect(Collectors.toList());// rice is in 4th page
			price.forEach(a -> System.out.println(a));// to print all elements in the list(a represents every element in
														// the list)
// lecture 118 paginations(loop it to every page for conditional check
			if (price.size() < 1) {
				driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
			}
		} while (price.size() < 1);
		driver.quit();
	}
	
	private static String getVeggiPrice(WebElement s) {
		String priceValue = s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return priceValue;
	}
}
/*
 * In lambda expressions (s->any action) left side s is items present in list or
 * stream right side what action you perform on the list/stream present in the
 * left side
 */
