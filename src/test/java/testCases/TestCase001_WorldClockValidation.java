package testCases;
 
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.lang.System;
import java.text.SimpleDateFormat;
import java.text.ParseException;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
import pageObjects.BeCognizant;
import pageObjects.OneCognizant;
import testBase.BaseClass;
 
public class TestCase001_WorldClockValidation extends BaseClass{
	List<WebElement>timeZones;
	List<WebElement>timeZoneDifferences;
	String bangloreTime;
	String londonTime;
	String newYorkTime;
	@Test(priority=0)
	public void userVerification() throws InterruptedException, IOException 
	{
		Thread.sleep(5000);
		BeCognizant bec = new BeCognizant(driver);
		bec.clickProfile();
		Thread.sleep(1000);
		System.out.println("Account verified");
		bec.getProfile();
		captureScreen("img_userprofile");
		bec.closeProfile();
		Thread.sleep(10000);
		captureScreen("img_onecognizant");
	}
	@Test(priority = 1)
	@Parameters({"browser"})
	public void beCognizantValidation(String br) throws InterruptedException, IOException {
		BeCognizant b=new BeCognizant(driver);
		captureScreen("home page 1");
		b.viewWorldClock();
		Thread.sleep(5000);
		timeZones=b.isDisplayedLogo();
		timeZoneDifferences=b.diffBetweenTimeZones();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		captureScreen("world clock screenshot");
		Thread.sleep(4000);
//		
		b.openOneCognizantPage(driver);
	}
//	
	@Test(priority =4)
	public void windowHandling() throws InterruptedException {
		BeCognizant b=new BeCognizant(driver);
		Thread.sleep(4000);
		b.windowHandlesOneCog(driver);
		captureScreen("one cognizant screenshot");
	}
	@Test(priority =2)
	public void timeValidation() {

		LocalTime local=LocalTime.now();
		LocalTime local1=LocalTime.now(ZoneId.of("Europe/London"));
		LocalTime local2=LocalTime.now(ZoneId.of("America/New_York"));
		System.out.println("Banglore Time is "+timeConvert(local.toString()));
		System.out.println("London Time is "+timeConvert(local1.toString()));
		System.out.println("NewYork Time is "+timeConvert(local2.toString()));
		//
		//
		//
		//
		//
		System.out.println(timeZones.size());
		for(WebElement x:timeZones) {
			System.out.println(x.getText());
		}

		 bangloreTime=timeConvert(local.toString());
		 londonTime=timeConvert(local1.toString());
		 newYorkTime=timeConvert(local2.toString());
		if(validatingTimeDifference(timeZones.get(0).getText(), bangloreTime) && validatingTimeDifference(timeZones.get(1).getText(), londonTime) && validatingTimeDifference(timeZones.get(1).getText(), newYorkTime)){
			System.out.println("Time Zones Validated Successfully");
		}
		else {
			System.out.println("Invalid World Clock");
		}
	}
	@Test(priority=3)
	public void timeDifferenceValidation() {
		for(WebElement element:timeZoneDifferences) {
			System.out.println(element.getText());
		}
		String timediff1=validatingTimeDifferenceinHours(bangloreTime,londonTime);
		String timediff2=validatingTimeDifferenceinHours(bangloreTime,newYorkTime);
		System.out.println(timeZoneDifferences.size());
		if(timediff1.equals(timeZoneDifferences.get(1).getText()) && timediff2.equals(timeZoneDifferences.get(2).getText())) {
			System.out.println("Time Zone Differences are successfully validated");
		}
		else {
			System.out.println("Time Zone differences are not validated");
		}
	}
	@Test(priority=5)
	@Parameters({"browser"})
	public void oneCognizantValidation(String br) throws InterruptedException, IOException {
		OneCognizant o=new OneCognizant(driver);
		o.clickOnHotAppsButton();
		Thread.sleep(7000);
		o.validatingAllAlphabets();
		captureScreen("one cognizant hot apps screenshot");
//		o.validatingAllAppNames();
//		Thread.sleep(5000);
//		captureScreen("one cognizant all apps starting with one character");
		Thread.sleep(5000);
		o.randomAlphabetAppDetails(br);
		Thread.sleep(5000);
		captureScreen("random alphabet app details");
		o.getAppsDetails(br);
	}
}
