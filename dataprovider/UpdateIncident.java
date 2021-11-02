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

public class UpdateIncident {
@Test(dataProvider="readData")
	public void runUpateIncident(String u,String p,String f) throws InterruptedException {
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
	
		driver.findElement(By.xpath("(//div[text()='Incidents'])[2]")).click();
		Thread.sleep(2000);
		WebElement element2 = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(element2);
		String text = driver.findElement(By.xpath("(//td[@class='vt']/a)[1]")).getText();
		driver.findElement(By.xpath("(//td[@class='vt']/a)[1]")).click();
		driver.findElement(By.xpath("(//select[@role='listbox'])[4]")).sendKeys("In Progress");
		driver.findElement(By.xpath("(//select[@role='listbox'])[6]")).sendKeys("1 - High");
		driver.findElement(By.id("sysverb_update_bottom")).click();
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(text, Keys.ENTER);
		String text2 = driver.findElement(By.xpath("(//tbody[@class='list2_body']//td)[14]")).getText();
		String text3 = driver.findElement(By.xpath("//td[text()='In Progress']")).getText();
		if (text2.contains("High") && text3.contains("In Progress")) {
			System.out.println("state and urgency verified");

		} else {
			System.out.println("state and urgency not verified");
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
