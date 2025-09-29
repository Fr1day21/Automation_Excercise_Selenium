package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage {

    private final WebDriver driver;

    //Locators
    private final By emailLoginField = By.name("email");
    private final By passwordLoginField = By.name("password");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");
    private final By nameSignupField = By.xpath("//input[@placeholder='Name']");
    private final By emailSignupField = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[normalize-space()='Signup']");
    private final By loginPageTitle = By.xpath("//h2[normalize-space()='New User Signup!']");
    private final By invalidMessageLogin = By.xpath("//p[normalize-space()='Your email or password is incorrect!']");
    private final By emailExistMessage = By.xpath("//p[normalize-space()='Email Address already exist!']");

    //Constructor
    public SignupLoginPage(WebDriver driver){
        this.driver = driver;
    }

    //Actions Login
    public void enterEmailLogin(String username){
        driver.findElement(emailLoginField).sendKeys(username);
    }

    public void enterPasswordLogin(String password){
        driver.findElement(passwordLoginField).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }

    //Action Signup

    public void enterNameSignup(String name){
        driver.findElement(nameSignupField).sendKeys(name);
    }

    public void enterEmailSignup(String email){
        driver.findElement(emailSignupField).sendKeys(email);
    }

    public void clickSignup(){
        driver.findElement(signupButton).click();
    }

    public String getLoginPageTitle(){
        return driver.findElement(loginPageTitle).getText();
    }

    public String getInvalidLogin(){
        return driver.findElement(invalidMessageLogin).getText();
    }

    public String getEmailExistMessage(){
        return driver.findElement(emailExistMessage).getText();
    }
}
