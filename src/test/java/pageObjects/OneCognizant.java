package pageObjects;
 
import java.io.IOException;
import java.util.List;
import java.util.Random;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
import testBase.BaseClass;
import utilities.ExcelUtilis;
 
public class OneCognizant extends BasePage{
	@FindBy(xpath="//div[@aria-label='GoPerform']")
	WebElement GoPerform_logo;
	@FindBy(xpath="//div[@class='viewAllHotAppsBtn']")
	WebElement hotAppsBtn;
	@FindBy(xpath="//div[@class='col s6 m3 l2 appStoreAppDiv']")
	List<WebElement> appNames;
	@FindBy(xpath="//*[@id=\"divAppstoreContainer\"]/div[1]/div/div/div[2]/div/div[24]")
	WebElement X;
	@FindBy(xpath="//*[@id=\"divAppstoreContainer\"]/div[1]/div/div/div[2]/div/div[25]")
	WebElement Y;
	@FindBy(xpath="//*[@class='charAZBtn']")
	List<WebElement> alphabets;


	public OneCognizant(WebDriver driver) {
		super(driver);
	}
	public void clickOnHotAppsButton() throws InterruptedException {
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();",GoPerform_logo);
//		System.out.print(hotAppsBtn.getText());
		System.out.println();
		hotAppsBtn.click();
		Thread.sleep(5000);
 
	}
	public char getRandomAlphabet() {
        Random random = new Random();
        return (char) ('A' + random.nextInt(26));
    }
	public void validatingAllAlphabets() {
		//X,Y are having different xpath because of that we create
		System.out.println(X.getText());
		System.out.println(Y.getText());
		//mutable strings
		//creating stringbuffer object
		StringBuffer s=new StringBuffer("");
		for(WebElement ele:alphabets) {
			s.append(ele.getText());
		}
		s.append(X.getText());
		s.append(Y.getText());
		System.out.println(s.toString());
		String str=s.toString();
		if(str.matches("[A-Z#]{27}")) {
			System.out.println("All Alphabets are displayed");
		}
		else{
			System.out.println("all alphabets are not displayed");
		}
	}
	public void randomAlphabetAppDetails(String br) throws InterruptedException, IOException {

		String originalString = "Filter apps starts with alphabet Q";
 
        // Generate a random alphabet
		char randomAlphabet = getRandomAlphabet();
		if(br.equalsIgnoreCase("chrome")) {
			ExcelUtilis.write("Random Alphabet in chrome", 0, 0, String.valueOf(randomAlphabet));
		}
		else {
		ExcelUtilis.write("Random Alphabet in edge", 0, 0, String.valueOf(randomAlphabet));
		}
        // Replace "Q" with the random alphabet
		String newString = originalString.replace("Q", String.valueOf(randomAlphabet));
 
        // Print the new string
		String z="//div[@aria-label=";
		String my=z+"'"+newString+"']";
		Thread.sleep(5000);
		driver.findElement(By.xpath(my)).click();
		Thread.sleep(5000);
		BaseClass.captureScreen("random alphabet app details");
	}
	public void getAppsDetails(String br) throws IOException {
		//after clicking the random alphabet 
		List<WebElement> ll = driver.findElements(By.xpath("//*[@class=\"col s6 m3 l2 appStoreAppDiv\"]"));
		if(br.equalsIgnoreCase("chrome")) {
			int row = 0;
			 for(int i=0; i<ll.size(); i++) {
				 ExcelUtilis.write("App details in "+br, row, 0, ll.get(i).getText());
				 row++;
			 }
		}
		else {
			int row = 0;
			 for(int i=0; i<ll.size(); i++) {
				 ExcelUtilis.write("App details in Edge", row, 0, ll.get(i).getText());
				 row++;
			 }
		}
	}
}