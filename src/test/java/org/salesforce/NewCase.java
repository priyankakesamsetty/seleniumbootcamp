package org.salesforce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewCase {
	
	public static void main(String[] args) {

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

		//Select case origin
		WebElement more=driver.findElement(By.xpath("//span[@class='slds-p-right_small']"));
		more.click();
		WebElement cases=driver.findElement(By.xpath("//a[@role='menuitem']//span[text()='Cases']"));
		Actions action = new Actions(driver);
		action.moveToElement(cases).click().build().perform();
		driver.findElement(By.xpath("//div[@title='New']")).click();
		driver.findElement(By.xpath("(//a[@class='select'])[2]")).click();
		WebElement email =driver.findElement(By.xpath("//a[@title='Email']"));
		Actions action1= new Actions(driver);
		action1.moveToElement(email).click().build().perform();

		//select status
		driver.findElement(By.xpath("//button[contains(@class,'slds-combobox__input slds-input_faux')]")).click();
		WebElement status= driver.findElement(By.xpath("(//span[text()='Escalated'])[1]"));
		Actions action2= new Actions(driver);
		action2.moveToElement(status).click().build().perform();

		//Select contact from dropdown
		driver.findElement(By.xpath("(//input[@role='combobox'])[3]")).click();
		WebElement name= driver.findElement(By.xpath("//div[@title='priyanka xyz']"));
		Actions action4= new Actions(driver);
		action4.moveToElement(name).click().build().perform();
		driver.findElement(By.xpath("(//div[@class='slds-form-element__control']//input)[9]")).sendKeys("Testing");
		driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("Dummy");
		driver.findElement(By.xpath("//button[@title='Save']")).click();

		//Verify sucess message
		String Message =driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		if(Message.isEmpty())
		{
			System.out.println("case is not created");
		}
		else if(Message.contains("created"))
		{
			System.out.println("Case is created");
		}



	}

}
