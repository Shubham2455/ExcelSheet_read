package Read;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class datadriventest {
	WebDriver driver = null;
	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\shubh\\Desktop\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(
				"file:///C:/Users/shubh/Desktop/Selenium/Offline%20Website/Offline%20Website/index.html");

	}

	@Test(priority = 1, dataProvider = "Logindata")
	public void logintest(String uname, String pass) {
		driver.findElement(By.xpath("//input[@id='email']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.id("email")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@id='password']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button"))
				.click();

		if (driver.getTitle().contains("Dashboard"))
			driver.findElement(
					By.xpath("/html/body/div/header/nav/div/ul/li/a")).click();

		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log in");

	}

	@DataProvider(name = "Logindata")
	public String[][] getdata() {

		String[][] logindata = {{"kiran@gmail.com", "123456"},

				{"shubham@gmail.com", "1231456"},
				{"vaibhav@gmail.com", "012345"}

		};

		return logindata;
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
