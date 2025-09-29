package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DetailProductPage;
import pages.HomePage;
import pages.ProductPage;

public class ProductTest extends BaseTest {

    //View all product and detail product
    @Test (priority = 1)
    public void viewAllProductAndDetail(){

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductTitle(), "ALL PRODUCTS");
        productPage.displayAllProduct();
        productPage.clickDetailProduct();

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

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductTitle(), "ALL PRODUCTS");
        productPage.enterProductName("Blue");
        productPage.clickSearch();
        Assert.assertTrue(productPage.getProduct().contains("Blue"));
    }



}
