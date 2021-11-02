package week5.day2excel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteIncident {
@Test(dataProvider="readData")
	public void runDeleteIncident(String u,String p,String f) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
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
	
		
		driver.findElement(By.xpath("(//div[text()='Open'])[1]")).click();
		WebElement element2 = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(element2);
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//td[@class='vt']/a[1]")).getText();
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(text);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//td[@class='vt']/a[1]")).click();
		driver.findElement(By.xpath("//button[text()='Delete']")).click();
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(text);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String text2 = driver.findElement(By.xpath("//tbody[@class='list2_body']//td")).getText();

		if (text2.equalsIgnoreCase("No records to display")) {
			System.out.println("the deleted incident is verified");

		} else {
			System.out.println("the incident is not deleted");
		}
		driver.close();
	}
@DataProvider
public String[][] readData() throws IOException {
ExcelClass a=new ExcelClass();
String[][] excel = a.readExcel();
return excel;
}
}
