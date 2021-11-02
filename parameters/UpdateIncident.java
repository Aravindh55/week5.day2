package week5.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateIncident extends IncidentBaseClass {
@Test
	public void runUpateIncident() throws InterruptedException {
		
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

	}

}
