package week5.day2;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
@Test
public class IncidentBaseClass {
	public ChromeDriver driver;
	
@Parameters({"user","pass","filter"})
@BeforeMethod
public void preconditions(String u, String p, String f) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://dev65973.service-now.com");
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	Thread.sleep(3000);
	WebElement element = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
	driver.switchTo().frame(element);
	driver.findElement(By.id("user_name")).sendKeys(u);

	driver.findElement(By.id("user_password")).sendKeys(p);
	driver.findElement(By.id("sysverb_login")).click();
	driver.switchTo().defaultContent();
	driver.findElement(By.id("filter")).sendKeys(f);
	driver.findElement(By.id("filter")).sendKeys(Keys.ENTER);
}
@AfterMethod
public void postconditions() {
	driver.close();
	

}
}
