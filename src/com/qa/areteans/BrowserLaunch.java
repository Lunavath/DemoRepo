package com.qa.areteans;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunch {
	public static Properties prop;
	public static FileInputStream fis;
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {

		prop = new Properties();
		fis = new FileInputStream(Variable.propertiesfilepath);

		prop.load(fis);
		if (prop.getProperty("browser").contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", Variable.chromerdriverpath);
			driver = new ChromeDriver();
			System.out.println("===== ***** chrome browser launched successfully *****==========");

		}

		else if (prop.getProperty("browser").contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", Variable.ffdriverpath);
			driver = new FirefoxDriver();
			System.out.println("===== ***** firefox browser launched successfully *****==========");

		}

		else {
			System.out.println("======****** browser not launched bad connection *******========");
		}
		
		
		loginOutlook();
		readSubject();
		
		

	}
	
	public static void loginOutlook() throws Exception {
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.findElement(By.xpath("//a[@aria-label='Sign in']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='i0116']")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='i0118']")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();
		driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();
		driver.findElement(By.xpath("//div[@id='ShellMail_link_text']")).click();
		
	}
	
	public static void readSubject() throws Exception {
		
		ArrayList<String> list=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(list.get(1));
		Thread.sleep(3000);
		
		String subject = driver.findElement(By.xpath("//span[@class='ITWTqi_23IoOPmK3O6ErT']")).getText();
		System.out.println("subject content:"+subject);
		Thread.sleep(3000);
		
		String msgcontent = driver.findElement(By.xpath("//div[@class='_3U2q6dcdZCrTrR_42Nxby JWNdg1hee9_Rz6bIGvG1c allowTextSelection']")).getText();
		System.out.println("message content:"+msgcontent);
		
		/*List<WebElement> tolist=new ArrayList<WebElement>(driver.findElements(By.xpath("//ul[@class='EhHTokBxnf7etdz-mes94']/li")));
		
		for(int i=0;i<tolist.size();i++) {
			String tolistname = tolist.get(i).getText();
		}
		
		String cclist=driver.findElement(By.xpath("//ul[@class='EhHTokBxnf7etdz-mes94']/parent::div/following-sibling::div/ul/li")).getText();
		System.out.println("TO list:"+cclist);*/
		
		
	}

}
