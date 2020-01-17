package seleniumproj.assignment.pageobjects;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(tagName = "iframe")
    private WebElement agreeFrame;
    
    @FindBy(className = "call")
    private WebElement agreeButton;
    
    @FindBy(className = "close")
    private WebElement closeButton;
    
    @FindBy(linkText = "Search the site")
    private WebElement searchButton;
    
    @FindBy(xpath = "//li/form/div/span[1]/input[2]")
    private WebElement searchBar;
    
    @FindBy(id = "filterSubject")
    private WebElement searchPage;
    
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void waitForPageLoad() {
    	WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(agreeFrame));
    }
    
    public void waitFor(WebElement element) {
    	WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void acceptCookies() {
    	driver.switchTo().frame(0);	
    	waitFor(agreeButton);
		agreeButton.click();
		waitFor(closeButton);
		closeButton.click();	
		driver.switchTo().defaultContent();
    }
    
    public void searchKeyword(String keyword) {
    	waitFor(searchButton);
    	searchButton.click();
    	waitFor(searchBar);
    	searchBar.sendKeys(keyword);
    	searchBar.sendKeys(Keys.RETURN);
    	waitFor(searchPage);
    }
    
    public void takeScreenshot(String filePath) throws IOException {
    	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	FileHandler.copy(src, new File(filePath));
    }
 
}
