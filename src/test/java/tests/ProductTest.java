package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;

public class ProductTest extends BaseTest {

    Faker faker = new Faker();

    //View all product and detail product
    @Test (priority = 1)
    public void viewAllProductAndDetail(){
        //homepage
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        //Product page
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductTitle(), "ALL PRODUCTS");
        productPage.displayAllProduct();
        productPage.clickDetailProduct();

        //detail product
        DetailProductPage detailProductPage = new DetailProductPage(driver);
        detailProductPage.getProductName();
        Assert.assertTrue(detailProductPage.getProductDetails().contains("Category"));
        Assert.assertTrue(detailProductPage.getProductDetails().contains("Rs."));
        Assert.assertTrue(detailProductPage.getProductDetails().contains("Quantity"));
        Assert.assertTrue(detailProductPage.getProductDetails().contains("Availability"));
        Assert.assertTrue(detailProductPage.getProductDetails().contains("Condition"));
        Assert.assertTrue(detailProductPage.getProductDetails().contains("Brand"));
    }

    //search product
    @Test(priority = 2)
    public void searchProduct(){
        //homepage
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        //product page
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductTitle(), "ALL PRODUCTS");
        productPage.enterProductName("Blue");
        productPage.clickSearch();
        Assert.assertTrue(productPage.getProduct().contains("Blue"));
    }

    //View category product
    @Test(priority = 3)
    public void viewCategoryProduct(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        ProductPage productPage = new ProductPage(driver);
        productPage.clickWomenCategory();
        productPage.clickSubCategoryWomen();
        Assert.assertTrue(productPage.getProduct().contains("Dress"));
        productPage.clickMenCategory();
        productPage.clickSubCategoryMen();
        Assert.assertTrue(productPage.getProduct().contains("Jeans"));

    }

    //View and cart brand products
    @Test(priority = 4)
    public void viewBrandProduct(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        ProductPage productPage = new ProductPage(driver);
        productPage.viewBrand();
        productPage.clickBrandCategory("POLO");
        Assert.assertTrue(productPage.getProduct().contains("POLO"));
        productPage.clickBrandCategory("HnM");
        Assert.assertTrue(productPage.getProduct().contains("H&M"));
    }

    //Search product and verify cart after login
    @Test(priority = 5)
    public void searchProductAndVerifyCartAfterLogin(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        //on product page
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductTitle(), "ALL PRODUCTS");
        productPage.enterProductName("Blue");
        productPage.clickSearch();
        productPage.scrollProduct("product1");
        Assert.assertTrue(productPage.getProduct().contains("Blue"));
        productPage.hoverProduct("product1");
        productPage.addToCart("product1");
        productPage.viewCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartInfo().contains("Blue"));

        homePage.goToSignupLoginMenu();

        //Login process
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        signupLoginPage.enterEmailLogin(ConfigReader.get("email"));
        signupLoginPage.enterPasswordLogin(ConfigReader.get("password"));
        homePage.goToCartMenu();
        Assert.assertTrue(cartPage.getCartInfo().contains("Blue"));
    }

    //Add review on product
    @Test(priority = 6)
    public void addReviewOnProduct(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        //on product page
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductTitle(), "ALL PRODUCTS");
        productPage.scrollProduct("anyProduct");
        productPage.clickDetailProduct();
        Assert.assertTrue(productPage.getReviewTab().contains("WRITE YOUR REVIEW"));
        productPage.enterNameReview(faker.name().fullName());
        productPage.enterEmailReview(faker.internet().emailAddress());
        productPage.enterReview(faker.lorem().paragraph());
        productPage.clickSubmitReview();
        Assert.assertTrue(productPage.getSuccessReview().contains("review"));
    }
}
