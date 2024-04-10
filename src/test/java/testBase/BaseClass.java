package testBase;
 
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
 
 
public class BaseClass {
	public static WebDriver driver;
	@BeforeClass
	@Parameters({"browser"})
	public void driverSetup(String value) throws IOException, InterruptedException {
		Properties p = new Properties();
		FileReader f=new FileReader("C:\\Users\\2318831\\eclipse-workspace\\CAS_Project\\resources\\config.properties");
		p.load(f);
		if(value.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else {
			driver=new EdgeDriver();
		}
		driver.get(p.getProperty("appURL"));
		System.out.println(p.getProperty("appURL"));
		Thread.sleep(5000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	public String timeConvert(String s) {
		LocalTime time = LocalTime.parse(s);
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("h:mma");
		return time.format(format1);
	}
	public boolean validatingTimeDifference(String time1,String time2) {
		try {
			SimpleDateFormat format=new SimpleDateFormat("hh:mma");
			Date date1=format.parse(time1);
			Date date2=format.parse(time2);
			long difference =date2.getTime()-date1.getTime();
			long differenceInMinutes=difference/(60*1000);
			if(differenceInMinutes<=1) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public  String convertDateFormat(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, M/d/yyyy");
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error converting date.";
        }
    }

	 public  String validatingTimeDifferenceinHours(String time1,String time2) {
			try {
				SimpleDateFormat format=new SimpleDateFormat("hh:mma");
				Date date1=format.parse(time1);
				Date date2=format.parse(time2);
				long difference =date2.getTime()-date1.getTime();
				long differenceInMinutes=difference/(60*1000);

				if(differenceInMinutes<0) {
					differenceInMinutes=differenceInMinutes*-1;
					long differenceInHours=differenceInMinutes/60;
					String diff=differenceInHours+"h "+(differenceInMinutes%60)+"m "+"behind";
					return diff;
				}
				else {
					long differenceInHours=differenceInMinutes/60;
					String diff=differenceInHours+"h "+(differenceInMinutes%60)+"m "+"ahead";
					return diff;
				}

			}
			catch(ParseException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}
	public static String captureScreen(String name) 
		{
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
			sourceFile.renameTo(targetFile);
			return targetFilePath;
		}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	@AfterTest
	public void OnTestCompletion() {
		System.out.println("Test Completed.......");
		System.out.println();
		System.out.println();
		System.out.println();
	}
 
}