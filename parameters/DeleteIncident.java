package week5.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteIncident extends IncidentBaseClass {
@Test
	public void runDeleteIncident() throws InterruptedException {
		
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

	}

}
