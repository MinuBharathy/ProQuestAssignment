package seleniumproj.assignment.pageobjects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {
    @FindBy(name = "q")
    private WebElement searchBar;
    
    @FindBy(className = "LC20lb")
    private List<WebElement> searchResults;

    WebDriver driver;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void loadPage() {
        driver.get("https://google.com");
        
        WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(searchBar));
    }
    
    public void search(String keyword) {
    	searchBar.sendKeys(keyword);
    	searchBar.sendKeys(Keys.RETURN);
    }
    
    public void waitForPageLoad(WebDriver driver, long timeOut) {
    	WebDriverWait wait1 = new WebDriverWait(driver, timeOut);
		wait1.until(ExpectedConditions.visibilityOfAllElements(searchResults));
    }
    
    public void clickFirstLink() {
    	searchResults.get(0).click();
    }
    public void writeResultsToTextFile(String filePath) throws IOException {
    	File file = new File(filePath);
    	FileWriter fw = new FileWriter(file);
    		for (WebElement result : searchResults) {
    			if (result.getText()!=null && result.getText().isEmpty()==false) {
    				fw.write(result.getText());
    				fw.write("\n");
    			}
    		}   
    	fw.close();
    }

}

