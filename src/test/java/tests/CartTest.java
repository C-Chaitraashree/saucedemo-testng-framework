package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void testAddProductToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(homePage.getCartCount(), "1");
    }

    @Test(groups = {"regression"})
    public void testRemoveProductFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.removeProductFromCart("Sauce Labs Backpack");

        Assert.assertEquals(homePage.getCartCount(), "0");
    }
}
