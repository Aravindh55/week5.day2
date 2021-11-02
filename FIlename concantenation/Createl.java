package week5.day2assign;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Createl  {
	@Test(dataProvider="fetchdata")

	public void runcreate(String u,String p,String cn,String em,String sec,String num) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(u);
		driver.findElement(By.id("password")).sendKeys(p);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(cn);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(em);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(sec);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(num);
		driver.findElement(By.name("submitButton")).click();
	    driver.close();
	}

	@DataProvider(name="fetchdata")
	public String[][] readexcel() throws IOException {
		ExcelbaseClass excel = new ExcelbaseClass();
		String[][] dataread = excel.dataread("createl");
		return dataread;

	}

}
