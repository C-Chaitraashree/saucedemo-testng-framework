package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

public class CheckoutTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void testCompleteCheckoutFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.goToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutInfo("John", "Doe", "560001");
        checkoutPage.finishOrder();

        Assert.assertEquals(checkoutPage.getOrderCompleteMessage(), "Thank you for your order!");
    }
}
