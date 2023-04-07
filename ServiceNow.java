package com.marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class ServiceNow {

	@Test
	public  void Snow() throws InterruptedException, IOException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://dev57553.service-now.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("2oaD6KN$q$vN");
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();
		Thread.sleep(10000);
		Shadow shdw =new Shadow(driver);
		shdw.findElementByXPath("//div[text()='All']").click();
		Thread.sleep(4000);
		//shdw.findElementByXPath("//input[@id='filter']").sendKeys("");
		shdw.findElementByXPath("//span[text()='Service Catalog']").click();
		Thread.sleep(10000);
		WebElement eleframe = shdw.findElementByXPath("//iframe[@name='gsft_main']");
		driver.switchTo().frame(eleframe);
		driver.findElement(By.xpath("(//h2[contains(text(),Mobiles)])[8]")).click();
		driver.findElement(By.xpath("//div[@class='sc_category_item']/table/tbody/tr/td[2]/div/a/strong[text()='iPhone 6s']")).click();
		WebElement dd1 = driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[1]"));
		Select sel1 =new Select(dd1);
		sel1.selectByVisibleText("Gold");
		Thread.sleep(2000);
		WebElement dd2 = driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[2]"));
		Select sel2 =new Select(dd2);
		sel2.selectByVisibleText("128");
		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();
		Thread.sleep(2000);
		String thanks = driver.findElement(By.xpath("//span[text()='Thank you, your request has been submitted']")).getText();
		if(thanks.contains("Thank you")) {
		System.out.println("Order is placed successfully");
		}
		else {
			
			System.out.println("Order is not placed successfully");
		}
		
		String reqno = driver.findElement(By.xpath("//a[@id='requesturl']")).getText();
		System.out.println("Request Number for this order is  "  +reqno);
		File sourcefile = driver.getScreenshotAs(OutputType.FILE);
		File target =new File("./ServiceNow/OrderPlaced.png");
		FileUtils.copyFile(sourcefile, target);

	}

}
