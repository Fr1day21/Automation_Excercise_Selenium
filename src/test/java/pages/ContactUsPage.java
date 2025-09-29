package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {

    private WebDriver driver;


    //locator
    private By contactUsValidatePage = By.xpath("//h2[normalize-space()='Feedback For Us']");
    private By nameField = By.xpath("//input[@placeholder='Name']");
    private By emailField = By.xpath("//input[@placeholder='Email']");
    private By subjectField = By.xpath("//input[@placeholder='Subject']");
    private By messageField = By.xpath("//textarea[@id='message']");
    private By submitButton = By.xpath("//input[@name='submit']");
    private By successMessage = By.xpath("//div[@class='status alert alert-success']");
    private By goHomeButton = By.xpath("//span[normalize-space()='Home']");



    //Contructor
    public ContactUsPage(WebDriver driver){
        this.driver = driver;
    }

    //Action
    public String getContactUsPageTitle(){
        return driver.findElement(contactUsValidatePage).getText();
    }

    public void enterName(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterSubject(String subject){
        driver.findElement(subjectField).sendKeys(subject);
    }

    public void enterMessage(String message){
        driver.findElement(messageField).sendKeys(message);
    }

    public void clickSubmit(){
        driver.findElement(submitButton).click();
    }

    public void clickAcceptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String getSuccessMessage(){
        return driver.findElement(successMessage).getText();
    }

    public void clickGoHome(){
        driver.findElement(goHomeButton).click();
    }

}
