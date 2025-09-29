package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;
import pages.SignupPage;
import utils.ConfigReader;
import utils.Helper;

public class LoginTest extends BaseTest {

    Faker faker = new Faker();
    Helper helper = new Helper();

    //Login User with correct email and password
    @Test (priority = 1)
    public void correctLogin(){
        //Register process
        HomePage homePage = new HomePage(driver);
        homePage.goToSignupLoginMenu();

        String nameSignup = faker.name().name();
        String emailSignup = faker.internet().emailAddress();
        String passwordSignup = faker.internet().password();

        helper.setName(nameSignup);
        helper.setEmail(emailSignup);
        helper.setPassword(passwordSignup);

        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        signupLoginPage.enterNameSignup(helper.getName());
        signupLoginPage.enterEmailSignup(helper.getEmail());
        signupLoginPage.clickSignup();

        SignupPage signupPage = new SignupPage(driver);
        signupPage.chooseGender(); //choose gender
        signupPage.enterPasswordSignup(helper.getPassword()); //Fill password signup
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
        signupPage.clickContinue();

        Assert.assertTrue(homePage.loggedIn().contains(helper.getName()));
        homePage.clickLogout();

        // login process
        // on homepage
        driver.get("https://automationexercise.com/");
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToSignupLoginMenu();

        //on login or signup page

        Assert.assertEquals(signupLoginPage.getLoginPageTitle(), "New User Signup!"); //Validation login/signup page
        signupLoginPage.enterEmailLogin(helper.getEmail());
        signupLoginPage.enterPasswordLogin(helper.getPassword());
        signupLoginPage.clickLogin();

        //on homepage
        Assert.assertTrue(homePage.loggedIn().contains(helper.getName()));
        homePage.clickDeleteAcc();
        Assert.assertEquals(homePage.verifyDeleteAcc(), "ACCOUNT DELETED!");
        homePage.clickContinueButton();
    }


    //Login User with correct email and password
    @Test(priority = 2)
    public void incorrectLogin(){
        //homepage
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToSignupLoginMenu();

        //on login or signup page
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupLoginPage.getLoginPageTitle().contains("User"));
        signupLoginPage.enterEmailLogin(faker.internet().emailAddress());
        signupLoginPage.enterPasswordLogin(faker.internet().password());
        signupLoginPage.clickLogin();
        Assert.assertTrue(signupLoginPage.getInvalidLogin().contains("incorrect"));

    }

    //Logout User
    @Test(priority = 3)
    public void logoutUser(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToSignupLoginMenu();

        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        signupLoginPage.enterEmailLogin(ConfigReader.get("email"));
        signupLoginPage.enterPasswordLogin(ConfigReader.get("password"));
        signupLoginPage.clickLogin();

        Assert.assertTrue(homePage.loggedIn().contains(ConfigReader.get("name")));
        homePage.clickLogout();

        Assert.assertTrue(signupLoginPage.getLoginPageTitle().contains("User"));
    }
}
