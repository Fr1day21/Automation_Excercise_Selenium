package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.Helper;

public class PlaceOrderTest extends BaseTest {

    Faker faker = new Faker();

    //Place Order: Register while Checkout
    @Test(priority = 1)
    public void registerWhileCheckout(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToProductMenu();

        ProductPage productPage = new ProductPage(driver);
        productPage.scrollProduct("product2");
        productPage.hoverProduct("product2");
        productPage.addToCart("product2");
        productPage.viewCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        cartPage.clickLoginRegister();

        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        signupLoginPage.enterNameSignup(faker.name().fullName());
        signupLoginPage.enterEmailSignup(faker.internet().emailAddress());
        signupLoginPage.clickSignup();

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
        homePage.goToCartMenu();

        cartPage.clickCheckout();
        cartPage.enterCommentCart(faker.lorem().sentence());
        cartPage.clickPlaceOrder();
        cartPage.enterNameOnCard(faker.name().fullName());
        cartPage.enterCardNumber(faker.number().digits(5));
        cartPage.enterCVC(String.valueOf(faker.number().numberBetween(200,300)));
        cartPage.enterExpiryMonth(String.valueOf(faker.number().numberBetween(1,12)));
        cartPage.enterExpiryYear(String.valueOf(faker.number().numberBetween(2026,2100)));
        cartPage.clickPayAndConfirm();

        Assert.assertEquals(homePage.getOrderSuccess(), "ORDER PLACED!");
        homePage.clickDeleteAcc();
        Assert.assertTrue(homePage.verifyDeleteAcc().contains("ACCOUNT DELETED!"));

    }

    //Place Order: Register while Checkout
    @Test(priority = 2)
    public void registerBeforeCheckout(){

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToSignupLoginMenu();

        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        signupLoginPage.enterNameSignup(faker.name().fullName());
        signupLoginPage.enterEmailSignup(faker.internet().emailAddress());
        signupLoginPage.clickSignup();

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

        ProductPage productPage = new ProductPage(driver);
        productPage.scrollProduct("product2");
        productPage.hoverProduct("product2");
        productPage.addToCart("product2");
        productPage.viewCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartInfo().contains("Summer"));
        cartPage.clickCheckout();
        cartPage.enterCommentCart(faker.lorem().sentence());
        cartPage.clickPlaceOrder();
        cartPage.enterNameOnCard(faker.name().fullName());
        cartPage.enterCardNumber(faker.number().digits(5));
        cartPage.enterCVC(String.valueOf(faker.number().numberBetween(200,300)));
        cartPage.enterExpiryMonth(String.valueOf(faker.number().numberBetween(1,12)));
        cartPage.enterExpiryYear(String.valueOf(faker.number().numberBetween(2026,2100)));
        cartPage.clickPayAndConfirm();

        Assert.assertEquals(homePage.getOrderSuccess(), "ORDER PLACED!");
        homePage.clickDeleteAcc();
        Assert.assertTrue(homePage.verifyDeleteAcc().contains("ACCOUNT DELETED!"));
    }

    //Place Order: Login before Checkout
    @Test(priority = 3)
    public void loginBeforeCheckout(){

        Helper helper = new Helper();

        String email = faker.internet().emailAddress();
        helper.setEmail(email);
        String password = faker.internet().password();
        helper.setPassword(password);
        String name = faker.name().firstName();
        helper.setName(name);

        //On homepage
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getHomePageValidation().contains("Automation"));
        homePage.goToSignupLoginMenu();

        //on signup login page
        SignupLoginPage signupLoginPage = new SignupLoginPage(driver);
        signupLoginPage.enterNameSignup(helper.getName());
        signupLoginPage.enterEmailSignup(helper.getEmail());
        signupLoginPage.clickSignup();

        //register process
        SignupPage signupPage = new SignupPage(driver);
        Assert.assertEquals(signupPage.getSignupPageTitle(), "ENTER ACCOUNT INFORMATION"); //Validate signup page
        signupPage.chooseGender(); //choose gender
        signupPage.enterPasswordSignup(helper.getPassword()); //Fill password signup
        signupPage.enterDays(faker.number().numberBetween(1, 30)); //Fill days birth
        signupPage.enterMonths(faker.number().numberBetween(1, 12)); //Fill months birth
        signupPage.enterYears(faker.number().numberBetween(1990, 2003)); //Fill years birth
        signupPage.enterFirstName(helper.getName()); //Fill first name signup
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

        //logout
        homePage.clickLogout();
        homePage.goToSignupLoginMenu();

        //login
        signupLoginPage.enterEmailLogin(helper.getEmail());
        signupLoginPage.enterPasswordLogin(helper.getPassword());
        signupLoginPage.clickLogin();
        Assert.assertTrue(homePage.loggedIn().contains(helper.getName()));

        //add product
        ProductPage productPage = new ProductPage(driver);
        productPage.scrollProduct("product2");
        productPage.hoverProduct("product2");
        productPage.addToCart("product2");
        productPage.viewCart();

        //checkout process
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartInfo().contains("Summer"));
        cartPage.clickCheckout();
        cartPage.enterCommentCart(faker.lorem().sentence());
        cartPage.clickPlaceOrder();
        cartPage.enterNameOnCard(faker.name().fullName());
        cartPage.enterCardNumber(faker.number().digits(5));
        cartPage.enterCVC(String.valueOf(faker.number().numberBetween(200,300)));
        cartPage.enterExpiryMonth(String.valueOf(faker.number().numberBetween(1,12)));
        cartPage.enterExpiryYear(String.valueOf(faker.number().numberBetween(2026,2100)));
        cartPage.clickPayAndConfirm();

        Assert.assertEquals(homePage.getOrderSuccess(), "ORDER PLACED!");
        homePage.clickDeleteAcc();
        Assert.assertTrue(homePage.verifyDeleteAcc().contains("ACCOUNT DELETED!"));
    }
}
