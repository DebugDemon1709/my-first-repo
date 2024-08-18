package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetTextFromWebPage {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoapp.skillrary.com/login.php?type=login");
		String Text = driver.findElement(By.id("keepLoggedInLabel")).getText(); //getting text of the WebElement
		System.out.println(Text);
		driver.quit();
	}

}
