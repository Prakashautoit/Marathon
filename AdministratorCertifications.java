package com.marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class AdministratorCertifications {

	@Test
	public  void AdminCerts() throws IOException, InterruptedException {

		String turl = null;
		String Fcerts =null;
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("hari.radhakrishnan@qeagle.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Leaf@123");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		String parent = driver.getWindowHandle();
		// System.out.println(parent);
		String sourceurl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		Set<String> childs = driver.getWindowHandles();
		List<String> winlist = new ArrayList<String>(childs);
		// int size = childs.size();
		//System.out.println(size);

		for (String each : winlist) {

			if (!each.equals(parent)) {
				driver.switchTo().window(each);
				String targeturl = driver.getCurrentUrl();
				turl = targeturl;

			}

		}
		// System.out.println(turl);

		if (sourceurl.equals(turl)) {

			System.out.println("No Redirect Happened");
		} else {

			System.out.println("Redirect is successfull");
		}

		String s1 = driver.getCurrentUrl();
		// System.out.println(s1);
		String p1 = driver.getWindowHandle();
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		String t1 = driver.getCurrentUrl();
		// System.out.println(t1);
		if (s1.equals(t1)) {

			System.out.println("Navigation is not successfull");
		} else {

			System.out.println("Navigation is  successfull");
		}
		Set<String> c2 = driver.getWindowHandles();
		for (String each2 : c2) {

			if (!each2.equals(p1)) {
				driver.switchTo().window(each2);

			}
			else {
				driver.switchTo().window(p1);
			}

		}

		System.out.println(driver.getTitle());
		Shadow sdw1 =new Shadow(driver);
		sdw1.findElementByXPath("//span[text()='Learning']").click();
		WebElement learn = sdw1.findElementByXPath("//span[text()='Learning on Trailhead']");
		Actions act =new Actions(driver);
		act.moveToElement(learn).perform();
		Shadow sdw2 =new Shadow(driver);
		sdw2.findElementByXPath("//a[text()='Salesforce Certification']").click();
		driver.findElement(By.xpath("//a[text()='Administrator']")).click();
		String Admintitle = driver.getTitle();
		System.out.println(Admintitle);
		if(Admintitle.contains("Administrator")) {
			
			System.out.println("Navigated to Admin Page");
		} else {
			
			System.out.println("Not Navigated to Admin Page");
		}
		
		List<WebElement> certslist = driver.findElements(By.xpath("//div[@class='credentials-card_type']//following::div[@class='credentials-card_title']"));
		List<String> finalscerstlist = new ArrayList<String>();
		int size = certslist.size();
		for (int i = 0; i <size; i++) {
			
			String FinalCerts = certslist.get(i).getText();
			//finalscerstlist.add(FinalCerts);
			//Fcerts=FinalCerts;
			System.out.println("List of the available Certifications" +FinalCerts);
		}
		
		
		WebElement scrollele = driver.findElement(By.xpath("//a[text()='Business Administration Specialist']"));
		Actions act3 =new Actions(driver);
		act3.scrollToElement(scrollele).perform();
		Thread.sleep(6000);
		File sourcefile = driver.getScreenshotAs(OutputType.FILE);
		File target =new File("./salesforceAdminCerts/litsofcerts.png");
		FileUtils.copyFile(sourcefile, target);
		
		
		

	}

}
