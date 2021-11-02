package week5.day2excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIncident {
	@Test(dataProvider="readData")
	public void runCreateIncident(String u,String p,String f) throws InterruptedException, IOException {
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
		
        driver.findElement(By.xpath("(//div[text()='Create New'])[1]")).click();
    	WebElement element2 = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(element2);
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Set<String> wh1 = driver.getWindowHandles();
		List<String> setwh1 = new ArrayList<String>(wh1);
		driver.switchTo().window(setwh1.get(1));
		driver.findElement(By.xpath("//span[@class='table-btn-lg']/following::a[1]")).click();
		driver.switchTo().window(setwh1.get(0));
		driver.switchTo().frame(element2);
		driver.findElement(By.id("incident.short_description")).sendKeys("automation testing");
		String attribute = driver.findElement(By.id("incident.number")).getAttribute("value");
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(attribute);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(Keys.ENTER);
        String text = driver.findElement(By.xpath("(//td[@class='vt']/a)[1]")).getText();
        if (attribute.equals(text)) {
			System.out.println("the incident was created successfully");
		} else {
			System.out.println("the incident was not created ");
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
