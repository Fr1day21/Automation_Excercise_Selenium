package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DetailProductPage;
import pages.HomePage;
import pages.ProductPage;
import utils.ConfigReader;

public class CartTest extends BaseTest {

    //Verify Subscription in Cart page
    @Test(priority = 1)
    public void verifySubscriptionInCartPage(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToCartMenu();

        CartPage cartPage = new CartPage(driver);
        cartPage.scrollToSubscribe();
        cartPage.enterEmailSubscription(ConfigReader.get("email"));
        cartPage.clickSubscription();
        Assert.assertTrue(cartPage.getSuccessSubscribe().contains("successfully subscribed!"));
    }

    //Add Products in Cart
    @Test(priority = 2)
    public void addProductInCart(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        ProductPage productPage = new ProductPage(driver);
        productPage.scrollProduct("product1");
        productPage.hoverProduct("product1");
        productPage.addToCart("product1");
        productPage.clickContinueShopping();
        productPage.scrollProduct("product2");
        productPage.hoverProduct("product2");
        productPage.addToCart("product2");
        productPage.viewCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartInfo().contains("Blue Top"));
        Assert.assertTrue(cartPage.getCartInfo().contains("Summer White Top"));
    }

    //Verify Product quantity in Cart
    @Test(priority = 3)
    public void verifyProductQuantity(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductTitle(), "ALL PRODUCTS");
        productPage.displayAllProduct();
        productPage.scrollProduct("anyProduct");
        productPage.clickDetailProduct();

        DetailProductPage detailProductPage = new DetailProductPage(driver);
        detailProductPage.getProductName();
        detailProductPage.enterQuantity("4");
        detailProductPage.clickCart();
        detailProductPage.clickViewCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartQuantity(), "4");
    }

}
