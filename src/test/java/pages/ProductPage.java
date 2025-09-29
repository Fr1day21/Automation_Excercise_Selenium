package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ActionHelper;
import utils.JavascriptUtility;

public class ProductPage {

    private WebDriver driver;
    Faker faker = new Faker();

    //Constructor
    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    int index = faker.number().numberBetween(1, 18);

    //Locator
    private By productTitlePage = By.xpath("//h2[normalize-space()='All Products']");
    private By allProduct = By.xpath("//div[@class='features_items']");
    private By detailProduct = By.xpath("(//a[contains(text(),'View Product')])["+index+"]");
    private By searchField = By.xpath("//input[@id='search_product']");
    private By searchButton = By.xpath("//button[@id='submit_search']");
    private By hoverProduct = By.xpath("(//div[@class='product-image-wrapper'])[1]");
    private By otherProduct = By.xpath("(//div[@class='product-image-wrapper'])[6]");
    private By addCartButton = By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[2]");
    private By addOtherCartButton = By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[12]");
    private By continueShoppingButton = By.xpath("//button[normalize-space()='Continue Shopping']");
    private By viewCartButton = By.xpath("//u[normalize-space()='View Cart']");


    //Action
    public String getProductTitle(){
        return driver.findElement(productTitlePage).getText();
    }

    public void displayAllProduct(){
        driver.findElement(allProduct).isDisplayed();
    }

    public void clickDetailProduct(){
        driver.findElement(detailProduct).click();
    }


    public void enterProductName(String search){
        driver.findElement(searchField).sendKeys(search);
    }

    public void clickSearch(){
        driver.findElement(searchButton).click();
    }

    public String getProduct(){
        return driver.findElement(allProduct).getText();
    }

    public void scrollProduct(String product){
        JavascriptUtility javascriptUtility = new JavascriptUtility(driver);

        switch (product){
            case "product1" :
                javascriptUtility.scrollToElement(driver.findElement(hoverProduct));
                break;
            case "product2" :
                javascriptUtility.scrollToElement(driver.findElement(otherProduct));
                break;
            case "anyProduct" :
                javascriptUtility.scrollToElement(driver.findElement(detailProduct));
                break;
            default:
                throw new IllegalArgumentException("Invalid product");
        }


    }

    public void hoverProduct(String product){
        ActionHelper actionHelper = new ActionHelper(driver);

        switch (product){
            case "product1" :
                actionHelper.hoverOver(driver.findElement(hoverProduct));
                break;
            case "product2" :
                actionHelper.hoverOver(driver.findElement(otherProduct));
                break;
            default:
                throw new IllegalArgumentException("product invalid");
        }

    }

    public void addToCart(String product){
        switch (product){
            case "product1" :
                driver.findElement(addCartButton).click();
                break;
            case "product2" :
                driver.findElement(addOtherCartButton).click();
                break;
            default:
                throw new IllegalArgumentException("product invalid");
        }
    }

    public void clickContinueShopping(){
        driver.findElement(continueShoppingButton).click();
    }

    public void viewCart(){
        driver.findElement(viewCartButton).click();
    }

}
