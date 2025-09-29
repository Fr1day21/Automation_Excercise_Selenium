package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasePage {

    private WebDriver driver;

    //Constructor
    public TestCasePage (WebDriver driver){
        this.driver = driver;
    }

    //Locator
    private By testCaseTitlePage = By.xpath("//b[normalize-space()='Test Cases']");

    //Action
    public String getTestCaseTitle(){
        return driver.findElement(testCaseTitlePage).getText();
    }

}
