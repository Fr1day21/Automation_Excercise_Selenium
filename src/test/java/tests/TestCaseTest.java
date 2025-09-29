package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.TestCasePage;

public class TestCaseTest extends BaseTest {


    //Verify test case page
    @Test
    public void verifyTestCasePage(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToTestCaseMenu();

        TestCasePage testCasePage = new TestCasePage(driver);
        Assert.assertTrue(testCasePage.getTestCaseTitle().contains("TEST"));
    }



}
