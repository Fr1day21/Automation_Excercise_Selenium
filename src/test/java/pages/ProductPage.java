package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ActionHelper;
import utils.JavascriptUtility;
import utils.WaitUtils;

public class ProductPage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    Faker faker = new Faker();

    //Constructor
    public ProductPage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 3);
    }

    int index = faker.number().numberBetween(1, 18);

    //Locator
    //Locator validation
    //Home Product
    private final By productTitlePage = By.xpath("//h2[normalize-space()='All Products']");
    private final By allProduct = By.xpath("//div[@class='features_items']");
    private final By brand = By.xpath("//div[@class='brands_products']");
    //Detail product
    private final By tabReview = By.xpath("//div[@class='category-tab shop-details-tab']");
    private final By successReviewMessage = By.xpath("//span[normalize-space()='Thank you for your review.']");

    //Locator field
    //Home Product
    private final By searchField = By.xpath("//input[@id='search_product']");
    //Detail product
    private final By nameReviewField = By.xpath("//input[@id='name']");
    private final By emailReviewField = By.xpath("//input[@id='email']");
    private final By reviewField = By.xpath("//textarea[@id='review']");

    //Locator button or action
    //Home Product
    private final By detailProduct = By.xpath("(//a[contains(text(),'View Product')])["+index+"]");
    private final By searchButton = By.xpath("//button[@id='submit_search']");
    private final By hoverProduct = By.xpath("(//div[@class='product-image-wrapper'])[1]");
    private final By otherProduct = By.xpath("(//div[@class='product-image-wrapper'])[6]");
    private final By addCartButton = By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[2]");
    private final By addOtherCartButton = By.xpath("(//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart'])[12]");
    private final By continueShoppingButton = By.xpath("//button[normalize-space()='Continue Shopping']");
    private final By viewCartButton = By.xpath("//u[normalize-space()='View Cart']");
    private final By womenCategory = By.xpath("//a[normalize-space()='Women']");
    private final By menCategory = By.xpath("//a[normalize-space()='Men']");
    private final By subCategoryDress = By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]");
    private final By subCategoryJeans = By.xpath("//a[normalize-space()='Jeans']");
    private final By poloBrand = By.xpath("//a[@href='/brand_products/Polo']");
    private final By HaMBrand = By.xpath("//a[@href='/brand_products/H&M']");
    //Detail product
    private final By reviewSubmitButton = By.xpath("//button[@id='button-review']");


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
                waitUtils.waitForElementClickable(addCartButton).click();
                break;
            case "product2" :
                waitUtils.waitForElementClickable(addOtherCartButton).click();
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

    public void clickWomenCategory(){
        JavascriptUtility javascriptUtility = new JavascriptUtility(driver);
        javascriptUtility.scrollToElement(driver.findElement(womenCategory));
        driver.findElement(womenCategory).click();
    }

    public void clickMenCategory(){
        driver.findElement(menCategory).click();
    }

    public void clickSubCategoryWomen(){
        driver.findElement(subCategoryDress).click();
    }

    public void clickSubCategoryMen(){
        driver.findElement(subCategoryJeans).click();
    }

    public void viewBrand(){
        driver.findElement(brand).isDisplayed();
    }

    public void clickBrandCategory(String brand){
        switch (brand){
            case "POLO" :
                driver.findElement(poloBrand).click();
                break;
            case "HnM" :
                driver.findElement(HaMBrand).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid brand");
        }

    }

    public String getReviewTab(){
        return driver.findElement(tabReview).getText();
    }

    public void enterNameReview(String name){
        driver.findElement(nameReviewField).sendKeys(name);
    }

    public void enterEmailReview(String email){
        driver.findElement(emailReviewField).sendKeys(email);
    }

    public void enterReview(String review){
        driver.findElement(reviewField).sendKeys(review);
    }

    public void clickSubmitReview(){
        driver.findElement(reviewSubmitButton).click();
    }

    public String getSuccessReview(){
        return waitUtils.waitForElementVisible(successReviewMessage).getText();
    }

}
