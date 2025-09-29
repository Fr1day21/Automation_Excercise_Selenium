package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailProductPage {

    WebDriver driver;

    //Constructor
    public DetailProductPage(WebDriver driver){
        this.driver = driver;
    }

    //Locator
    private By detailProductName = By.xpath("(//h2[normalize-space()])[3]");
    private By singleDetailProduct = By.xpath("//div[@class='product-information']");
    private By quantityField = By.xpath("//input[@id='quantity']");
    private By addCartButton = By.xpath("//button[normalize-space()='Add to cart']");
    private By viewCartButton = By.xpath("//u[normalize-space()='View Cart']");


    //Actions
    public void getProductName(){
        driver.findElement(detailProductName).isDisplayed();
    }

    public String getProductDetails(){
        return driver.findElement(singleDetailProduct).getText();
    }

    public void enterQuantity(String quantity){
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(quantity);
    }

    public void clickCart(){
        driver.findElement(addCartButton).click();
    }

    public void clickViewCart(){
        driver.findElement(viewCartButton).click();
    }

}
