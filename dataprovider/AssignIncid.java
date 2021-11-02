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

public class AssignIncid {
	@Test(dataProvider="readData")

	public void runAssignIncid(String u,String p,String f) throws InterruptedException {
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
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='vt']/a)[1]")).click();
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> wlist = new ArrayList<String>(windowHandles);
		driver.switchTo().window(wlist.get(1));
		driver.findElement(By.xpath("(//td[@class='text-align-right']//button)[3]")).click();
		String text2 = driver.findElement(By.xpath("//a[text()='Software']")).getText();
		driver.findElement(By.xpath("//a[text()='Software']")).click();
		driver.switchTo().window(wlist.get(0));
		Thread.sleep(3000);
		WebElement element3 = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(element3);

		driver.findElement(By.xpath("(//div[@class='col-xs-10 col-md-9 col-lg-8 form-field']//textarea)[3]"))
				.sendKeys("assignmentservicenow");
		driver.findElement(By.id("sysverb_update_bottom")).click();
		String text3 = driver.findElement(By.xpath("(//tbody[@class='list2_body']//td/a)[4]")).getText();
		if (text3.contains("Software")) {
			System.out.println("assignment group verified");

		} else {
			System.out.println("assignment group not verified");
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
