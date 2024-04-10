package pageObjects;
 
import java.io.IOException;
 
import java.time.LocalDate;
import java.util.ArrayList;
 
import java.util.List;
import java.util.Set;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
import org.openqa.selenium.support.FindBy;
 
import utilities.ExcelUtilis;
import testBase.BaseClass;
 
public class BeCognizant extends BasePage{
	JavascriptExecutor js;
	public BeCognizant(WebDriver driver) throws InterruptedException {
		super(driver);
		Thread.sleep(5000);
	}


	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement userprofile;
	@FindBy(id="mectrl_currentAccount_primary")
	WebElement name;
	@FindBy(id="mectrl_currentAccount_secondary")
	WebElement emailId;
	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement profileXpathClose;
	@FindBy(id="mectrl_headerPicture")
	WebElement profileIdClose;

	public void clickProfile()
	{
//		System.out.println("close.....");
		userprofile.click();
	}
	public void getProfile() throws IOException
	{
		String Name = name.getText();
		String Email = emailId.getText();
		System.out.println("--------------- Personal Info ---------------");
		System.out.println("Name     : "+Name+"\nMail Id  : "+Email);
		System.out.println("---------------------------------------------");
		System.out.println("");
		ExcelUtilis.write("Profile Info", 0, 0, Name);
		ExcelUtilis.write("Profile Info", 0, 1, Email);
	}
 
	
	public void closeProfile()
	{
		try
		{
			profileXpathClose.click();
		}
		catch(Exception e)
		{
			profileIdClose.click();
		}
	}
	@FindBy(xpath="//*[text()='OneCognizant']")
	WebElement oneCognizant;

 
    @FindBy(xpath="//strong[text()='Around Cognizant']")
    WebElement AroundCognizant;

 
    @FindBy(xpath="//*[@data-automation-id=\"webPartTitleReadMode\"]")
    List<WebElement>List;
    @FindBy(xpath="//span[text()='World Clock']")
    WebElement SeeAll;
	public void viewWorldClock() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		WebElement s=driver.findElement(By.xpath("//*[text()='Visit the Gen AI Hub for info & resources']"));
		WebElement s1=driver.findElement(By.xpath("//h4"));
		//WebElement x=driver.findElement(By.xpath("//span[text()='Add a caption']"));
		js.executeScript("arguments[0].scrollIntoView();", s);
		js.executeScript("arguments[0].scrollIntoView();", s1);
		//jse.executeScript("arguments[0].scrollIntoView();", x);
		WebElement y=driver.findElement(By.xpath("//span[text()='Add a caption']"));
		js.executeScript("arguments[0].scrollIntoView();", y);
		Thread.sleep(5000);
		BaseClass.captureScreen("World Clock");
	    //js.executeScript("arguments[0].scrollIntoView();",SeeAll);
	    js.executeScript("arguments[0].scrollIntoView();",List.get(1));
	    Thread.sleep(5000);
	}
	@FindBy(xpath="//*[text()='World Clock']")
	WebElement WorldClockLogo;
 
	@FindBy(xpath="//*[@id=\"vpc_WebPart.WorldClockWebPart.internal.60655e4a-73c8-49d0-9571-c762791557af\"]/div/div/div[2]/div/div/div/div/div/div/div[1]/div/div/div/div[2]/div[2]/div[2]")
	WebElement CurrentDateAndWeek;
	public List<WebElement> isDisplayedLogo() throws IOException {
		if(WorldClockLogo.isDisplayed()) {
			System.out.println(CurrentDateAndWeek.getText());
			BaseClass b=new BaseClass();
			String currentDate=b.convertDateFormat(LocalDate.now().toString());
			if(currentDate.equals(CurrentDateAndWeek.getText())) {
				System.out.println("Current Dates are Validated");
			}
			else {
				System.out.println("Current Dates are not Validated");
			}
			List<WebElement> TimeZones = driver.findElements(By.xpath("//*[@data-automation-id='clock-card-AM-PM-time']"));
			int row = 0;
			 for(int i=0; i<TimeZones.size(); i++) {
				 ExcelUtilis.write("Time Zones Details", row, 0, TimeZones.get(i).getText());
				 row++;
			 }
			return TimeZones;
		}
		else {
			System.out.println("World Clock not displayed");
			return null;
		}
	}
	public List<WebElement> diffBetweenTimeZones(){
		List<WebElement> zones = driver.findElements(By.xpath("//div[@data-automation-id='clock-card-time-offset']"));
		return zones;
	}
	public void openOneCognizantPage(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(4000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",AroundCognizant);
 
		oneCognizant.click();
	}
 
	public void windowHandlesOneCog(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(10000);
		Set <String> Window = driver.getWindowHandles();
	    List <String> Window1 = new ArrayList<String>(Window); 
	    //One Cognizant's Window Handle - Window1.get(1)
	    driver.switchTo().window(Window1.get(1));
	}
}