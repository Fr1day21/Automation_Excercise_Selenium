package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.JavascriptUtility;

public class HomePage {

    private WebDriver driver;

    //Locator
    //Locator validation
    private final By homePageValidation = By.xpath("//section[@id='slider']//div[@class='item active']//div[1]");
    private final By loggedIn = By.xpath("//li[10]//a[1]");
    private final By verifyDeleteAcc = By.xpath("//b[normalize-space()='Account Deleted!']");
    private final By subscriptionTitle = By.xpath("//h2[normalize-space()='Subscription']");
    private final By messageSuccessSubscribe = By.xpath("//div[@class='alert-success alert']");
    private final By messageOrderSuccess = By.xpath("//b[normalize-space()='Order Placed!']");

    //Locator go to menu
    private final By signupLoginMenu = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By contactUsMenu = By.xpath("//a[normalize-space()='Contact us']");
    private final By testCaseMenu = By.xpath("//a[contains(text(),'Test Cases')]");
    private final By productMenu = By.xpath("//a[@href='/products']");
    private final By cartMenu = By.xpath("//a[normalize-space()='Cart']//i[@class='fa fa-shopping-cart']");

    //Locator action button
    private final By deleteAccButton = By.xpath("//a[normalize-space()='Delete Account']");
    private final By continueButton = By.xpath("//a[normalize-space()='Continue']");
    private final By logoutButton = By.xpath("//a[normalize-space()='Logout']");
    private final By submitSubscriptionButton = By.xpath("//button[@id='subscribe']");

    //Locator action field
    private final By emailSubscriptionField = By.xpath("//input[@id='susbscribe_email']");


    //Constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Action
    public String getHomePageValidation(){
        return driver.findElement(homePageValidation).getText();
    }

    public void goToSignupLoginMenu(){
        driver.findElement(signupLoginMenu).click();
    }

    public String loggedIn(){
        return driver.findElement(loggedIn).getText();
    }

    public void clickDeleteAcc(){
        driver.findElement(deleteAccButton).click();
    }

    public String verifyDeleteAcc(){
        return driver.findElement(verifyDeleteAcc).getText();
    }

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }

    public void clickLogout(){
        driver.findElement(logoutButton).click();
    }

    public void goToContactUsMenu(){
        driver.findElement(contactUsMenu).click();
    }

    public void goToTestCaseMenu(){
        driver.findElement(testCaseMenu).click();
    }

    public void goToProductMenu(){
        driver.findElement(productMenu).click();
    }

    public void scrollToSubscribe(){
        JavascriptUtility javascriptUtility = new JavascriptUtility(driver);
        javascriptUtility.scrollToElement(driver.findElement(subscriptionTitle));
    }

    public void enterEmailSubscription(String email){
        driver.findElement(emailSubscriptionField).sendKeys(email);
    }

    public void clickSubscription(){
        driver.findElement(submitSubscriptionButton).click();
    }

    public String getSuccessSubscribe(){
        return driver.findElement(messageSuccessSubscribe).getText();
    }

    public void goToCartMenu(){
        driver.findElement(cartMenu).click();
    }

    public String getOrderSuccess(){
        return driver.findElement(messageOrderSuccess).getText();
    }
}
