package org.yahoo.project;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class YahooProject {

 public static <string> void main(String[] args) throws InterruptedException {


		System.setProperty("webdriver.chromedriver","C:\\Neha\\Selenium\\SeleniumJavaProject\\LearnSelenium\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		List<WebElement> allLinks=null;//Declaring the object to store 
		//Here i am creating an object of HashMap class  and it will store string values.
		
		HashMap<String,String> hashMap=new HashMap<String,String>();
//1.Access the Yahoo.com
		driver.get("https://www.yahoo.com/");
		driver.manage().window().maximize();
//2.Enter the Selenium in text box.
		WebElement e=driver.findElement(By.xpath("//input[@type='text' and @name=\"p\"]"));
		e.sendKeys("Selenium");
		Thread.sleep(3000);
//3.Select the Selenium Interview Questions
		
		//Select dropdown=new Select(driver.findElement(By.tagName("//li[@data='selenium interview questions']")));
		//dropdown.selectByVisibleText("selenium interview questions");
		//Actions action=new Actions(driver);
		//action.moveToElement(driver.findElement(By.xpath("//li[@data='selenium interview questions']")));

		//action.click();
		//action.perform();	
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data='selenium interview questions']"))).click();
		
		
//4.Loop throgh the 5 pages and fetch all the hyperlinks.

		for(int i=1;i<6;i++) {

			if(i>1) {
				WebElement e1=driver.findElement(By.xpath("//a[contains(text(),'" + i + "') and contains(@title,'Results')]"));
				e1.click();
			  Thread.sleep(3000);
			}
			

		//Here i put all the tagname by element a within allLinks object.
		
			allLinks = driver.findElements(By.tagName("a"));
			for(WebElement link:allLinks) {
				hashMap.put(link.getAttribute("href"), link.getText());//Here we are calling a method to store key value pair.
			}
			
			//System.out.println("By Page " + i + " Number of hyperlinks inside hashMap object " + hashMap.size());
		}
		
//5.Validate the link text contains Selenium Interview Questions
		//Here in below line hashMap.keySet will give set view of keys contained in the map.
		Set<String> keys = hashMap.keySet();
		System.out.println("----------------------PASSED TESTDATA-------------------------");
		  for(String s : keys)//String s object  will go in each hash key pass the value
		  {
			  String actual = hashMap.get(s);//Here we are retrieving the value from the s using get method.This is for Validation.
			  actual = actual.toLowerCase();
			  String expected = "selenium interview question";
		  
			if(actual.contains(expected)) {
			System.out.println(actual + " Test Case Pass");
		  }   
		  }
		  
		  System.out.println("----------------FAILED TESTDATA-----------------");
		  for(String s : keys)
		  {
			  String actual = hashMap.get(s);
			  actual = actual.toLowerCase();
			  String expected = "selenium interview question";
		   
			if(!actual.contains(expected)) {
			System.out.println(actual + " Test Case Fail");

		}
		   
		  }
	
		driver.close();
	}
}


