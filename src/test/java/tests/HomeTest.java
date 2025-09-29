package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ConfigReader;

public class HomeTest extends BaseTest {

    //Verify Subscription in home page
    @Test
    public void subscribe(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.scrollToSubscribe();
        homePage.enterEmailSubscription(ConfigReader.get("email"));
        homePage.clickSubscription();
        Assert.assertTrue(homePage.getSuccessSubscribe().contains("successfully subscribed!"));
    }



}
