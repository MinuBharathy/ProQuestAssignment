package seleniumproj.assignment.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import seleniumproj.assignment.pageobjects.GoogleSearchPage;
import seleniumproj.assignment.pageobjects.HomePage;

public class SearchTests {

	WebDriver driver;
	GoogleSearchPage googleSearchPage;
	HomePage homePage;
	String driverPath = "C:\\gecko.exe";

	@BeforeClass(alwaysRun=true)
	public void setup() {
		driver = new FirefoxDriver();
		System.setProperty("webdriver.gecko.driver", driverPath);
	}

	@Test(priority=0)
	public void testWriteSearchResults() throws IOException {
		googleSearchPage = new GoogleSearchPage(driver);
		homePage = new HomePage(driver);
		
		googleSearchPage.loadPage();
		
		googleSearchPage.search("ProQuest");
		googleSearchPage.waitForPageLoad(driver, 20);
		
		googleSearchPage.writeResultsToTextFile("C:\\output.txt");	
	}
	
	@Test(priority=1)
	public void testSearchKeywordOnHomePage() throws IOException {
		googleSearchPage.clickFirstLink();
		homePage.waitForPageLoad();
		
		homePage.acceptCookies();

		homePage.searchKeyword("QA");
		homePage.takeScreenshot("C:\\screenshot.png");
	}

	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}



