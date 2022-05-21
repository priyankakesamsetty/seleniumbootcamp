package org.salesforce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditCase {

	public static void main(String[] args) throws InterruptedException {
		//1) Launch the app //
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver(options);
		driver.get("https://www.salesforce.com/in/");

		//2)Click Login//
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		driver.findElement(By.linkText("Login")).click();

		//3 Login with Credentials//
		driver.findElement(By.id("username")).sendKeys("mars@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("BootcampSel$123");
		driver.findElement(By.id("Login")).click();

		//4.click on sds icon//
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'View All')]")).click();
		driver.findElement(By.xpath("(//span//p[contains(text(),'Sales')])[3]")).click();

		//click more and select cases
		WebElement more=driver.findElement(By.xpath("//span[@class='slds-p-right_small']"));
		more.click();
		WebElement cases=driver.findElement(By.xpath("//a[@role='menuitem']//span[text()='Cases']"));
		Actions action = new Actions(driver);
		action.moveToElement(cases).click().build().perform();

		//click on dropdown icon
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//a[contains(@class,'slds-button slds-button--icon-x-small')])[1]")).click();
		driver.findElement(By.xpath("//a[@title='Edit']")).click();

		//Update Status as Working
		Thread.sleep(3000);
		WebElement drpdownbutton=driver.findElement(By.xpath("(//label[text()='Status']//following::lightning-icon)[1]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", drpdownbutton);
		Thread.sleep(1000);
		WebElement status = driver.findElement(By.xpath("//span[@title='Working']"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(status).click().build().perform();

		//Update Priority to low
		WebElement prioritydrpdown= driver.findElement(By.xpath("//span[text()='Priority']//following::a[1]"));
		js.executeScript("arguments[0].click();", prioritydrpdown);
		WebElement priority = driver.findElement(By.xpath("//a[@title='High']"));
		Actions action2= new Actions(driver);
		action2.moveToElement(priority).click().build().perform();


		//Update Case Origin as Phone
		WebElement caseorigindrpdown= driver.findElement(By.xpath("//span[text()='Case Origin']//following::a[1]"));
		js.executeScript("arguments[0].click();", caseorigindrpdown);
		WebElement phone =driver.findElement(By.xpath("//a[@title='Email']"));
		Actions action3= new Actions(driver);
		action3.moveToElement(phone).click().build().perform();

		//update SLA violation to No
		WebElement SLADrpdown= driver.findElement(By.xpath("//span[text()='SLA Violation']//following::a[1]"));
		js.executeScript("arguments[0].click();", SLADrpdown);
		WebElement SLA = driver.findElement(By.xpath("//a[text()='Yes']"));
		Actions action4= new Actions(driver);
		action4.moveToElement(SLA).click().build().perform();

		Thread.sleep(50000);

		//click on save
		driver.findElement(By.xpath("//button[@title='Save']")).click();

		//verification
		String edited_status= driver.findElement(By.xpath("//span[text()='Working']")).getText();
		Assert.assertEquals(edited_status, "Working" );
		System.out.println("Edited case successfully");
		driver.close();
	}

}
