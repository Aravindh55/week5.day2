package week5.day2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIncident extends IncidentBaseClass{
	@Test
	public void runCreateIncident() throws InterruptedException, IOException {
		
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
		
}
}
