package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;
import pages.SignupPage;
import utils.ConfigReader;


public class RegisterTest extends BaseTest {

    Faker faker = new Faker();

    //Register User
    @Test (priority = 1)
    public void register(){

        //on homepage
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation")); //Validation Home Page
        homePage.goToSignupLoginMenu(); //Go to Login/Signup Menu

        //on login/signup page
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        Assert.assertEquals(signupLoginPage.getLoginPageTitle(), "New User Signup!"); //Validation login/signup page
        signupLoginPage.enterNameSignup(faker.name().username()); //Fill name field signup
        signupLoginPage.enterEmailSignup(faker.internet().emailAddress()); //Fill email field signup
        signupLoginPage.clickSignup(); //Click button signup

        //on signup page
        SignupPage signupPage = new SignupPage(driver);
        Assert.assertEquals(signupPage.getSignupPageTitle(), "ENTER ACCOUNT INFORMATION"); //Validate signup page
        signupPage.chooseGender(); //choose gender
        signupPage.enterPasswordSignup(faker.internet().password()); //Fill password signup
        signupPage.enterDays(faker.number().numberBetween(1, 30)); //Fill days birth
        signupPage.enterMonths(faker.number().numberBetween(1, 12)); //Fill months birth
        signupPage.enterYears(faker.number().numberBetween(1990, 2003)); //Fill years birth
        signupPage.enterFirstName(faker.name().firstName()); //Fill first name signup
        signupPage.enterLastName(faker.name().lastName()); //Fill last name
        signupPage.enterCompanyName(faker.company().name()); //Fill company name
        signupPage.enterAddress(faker.address().fullAddress()); //Fill address
        signupPage.chooseCountry(); //choose country
        signupPage.enterState(faker.address().state()); //Fill state
        signupPage.enterCity(faker.address().city()); //Fill city
        signupPage.enterZipcode(faker.address().zipCode()); //Fill zipcode
        signupPage.enterMobileNum(faker.phoneNumber().phoneNumber()); //Fill phone number
        signupPage.clickRegisterButton();
        Assert.assertEquals(signupPage.successRegister(), "ACCOUNT CREATED!");
        signupPage.clickContinue();

        //on homepage
        Assert.assertTrue(homePage.loggedIn().contains("Logged in"));
        homePage.clickDeleteAcc();
        Assert.assertEquals(homePage.verifyDeleteAcc(), "ACCOUNT DELETED!");
        homePage.clickContinueButton();
    }

    // Register User with existing email
    @Test(priority = 2)
    public void registerEmailExisting(){

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToSignupLoginMenu();

        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupLoginPage.getLoginPageTitle().contains("User"));
        signupLoginPage.enterNameSignup(faker.name().username());
        signupLoginPage.enterEmailSignup(ConfigReader.get("email"));
        signupLoginPage.clickSignup();
        Assert.assertTrue(signupLoginPage.getEmailExistMessage().contains("already exist!"));
    }


}
