package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.JavascriptUtility;
import utils.WaitUtils;

public class CartPage {

    WebDriver driver;
    WaitUtils waitUtils;

    //Constructor
    public CartPage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 5);
    }

    //Locator
    private final By subscriptionTitle = By.xpath("//h2[normalize-space()='Subscription']");
    private final By emailSubscriptionField = By.xpath("//input[@id='susbscribe_email']");
    private final By submitSubscriptionButton = By.xpath("//button[@id='subscribe']");
    private final By messageSuccessSubscribe = By.xpath("//div[@class='alert-success alert']");
    private final By cartInfo = By.xpath("//div[@id='cart_info']");
    private final By cartQuantity = By.className("cart_quantity");
    private final By checkoutButton = By.xpath("//a[normalize-space()='Proceed To Checkout']");
    private final By RegLogCheckoutProcessButton = By.xpath("//u[normalize-space()='Register / Login']");
    private final By emptyCartMessage = By.xpath("//span[@id='empty_cart']");
    private final By removeProduct = By.xpath("//a[@class='cart_quantity_delete']");

    //Locator Checkout
    private final By commentCartField = By.xpath("//textarea[@name='message']");
    private final By placeOrderButton = By.xpath("//a[normalize-space()='Place Order']");

    //Locator Payment
    private final By nameOnCardField = By.name("name_on_card");
    private final By cardNumberField = By.name("card_number");
    private final By cvcField = By.name("cvc");
    private final By expiryMonthField = By.name("expiry_month");
    private final By expiryYearField = By.name("expiry_year");
    private final By payAndConfirmButton = By.xpath("//button[@id='submit']");

    //Action
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

    public String getCartInfo(){
        return driver.findElement(cartInfo).getText();
    }

    public String getCartQuantity(){
        return driver.findElement(cartQuantity).getText();
    }

    public void clickCheckout(){
        driver.findElement(checkoutButton).click();
    }

    public void clickLoginRegister(){
        driver.findElement(RegLogCheckoutProcessButton).click();
    }

    public void enterCommentCart(String comment){
        driver.findElement(commentCartField).sendKeys(comment);
    }

    public void clickPlaceOrder(){
        driver.findElement(placeOrderButton).click();
    }

    public void enterNameOnCard(String name){
        driver.findElement(nameOnCardField).sendKeys(name);
    }

    public void enterCardNumber(String cardNumber){
        driver.findElement(cardNumberField).sendKeys(cardNumber);
    }

    public void enterCVC(String cvcNumber){
        driver.findElement(cvcField).sendKeys(cvcNumber);
    }

    public void enterExpiryMonth(String exp){
        driver.findElement(expiryMonthField).sendKeys(exp);
    }

    public void enterExpiryYear(String exp){
        driver.findElement(expiryYearField).sendKeys(exp);
    }

    public void clickPayAndConfirm(){
        driver.findElement(payAndConfirmButton).click();
    }

    public String getEmptyCartMessage(){
        return waitUtils.waitForElementVisible(emptyCartMessage).getText();
    }

    public void clickRemoveProduct(){
        driver.findElement(removeProduct).click();
    }



}
