package Read;

import java.io.File;
import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DpWithExcel {
	WebDriver driver = null;
	Workbook wb = null;

	@BeforeSuite
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\shubh\\Desktop\\chrome driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(
				"file:///C:/Users/shubh/Desktop/Selenium/Offline%20Website/Offline%20Website/index.html");

	}

	@AfterTest
	public void tearDown() {
		driver.close();

	}

	@Test(priority = 1, dataProvider = "dp")
	public void loginTest(String uname, String pass) {
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(uname);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/div/button"))
				.click();

		if (driver.getTitle().contains("Dashboard"))
			driver.findElement(
					By.xpath("/html/body/div/header/nav/div/ul/li/a")).click();

		Assert.assertEquals(driver.getTitle(), "JavaByKiran | Log in");
	}
	@DataProvider
	public Object[][] dp() {

		try {
			File file = new File(
					"C:\\Users\\shubh\\Documents\\Ex work\\Book2.xls");
			FileInputStream fis = new FileInputStream(file);
			wb = Workbook.getWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet sh = wb.getSheet("Sheet3");
		int rows = sh.getRows();// 3
		int col = sh.getColumns();// 2

		String data[][] = new String[rows][col];// [3][2]
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < col; j++) {
				Cell c = sh.getCell(j, i);
				String value = c.getContents();
				data[i][j] = value;
			}
		}
		return data;
	}

}
