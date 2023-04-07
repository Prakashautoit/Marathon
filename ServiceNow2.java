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

public class ServiceNow2 {

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
		//Thread.sleep(4000);
		//shdw.findElementByXPath("//input[@id='filter']").sendKeys("");
		shdw.findElementByXPath("//input[@id='filter']").sendKeys("incident");
		shdw.findElementByXPath("//span[text()='Create New']").click();
	}

}
