package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends BaseTest {
    Faker faker = new Faker();

    //Contact Us Form
    @Test
    public void contactUsFrom(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToContactUsMenu();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        Assert.assertTrue(contactUsPage.getContactUsPageTitle().contains("FEEDBACK"));
        contactUsPage.enterName(faker.name().fullName());
        contactUsPage.enterEmail(faker.internet().emailAddress());
        contactUsPage.enterSubject(faker.lorem().sentence());
        contactUsPage.enterMessage(faker.lorem().paragraph());
        contactUsPage.clickSubmit();
        contactUsPage.clickAcceptAlert();
        Assert.assertTrue(contactUsPage.getSuccessMessage().contains("Success!"));
        contactUsPage.clickGoHome();

        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));

    }
}
