package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.DBUtil;

public class DbValidationTest extends BaseTest {

    @Test(groups = {"regression"})
    public void testUiPriceMatchesDatabasePrice() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        String productName = "Sauce Labs Backpack";
        double uiPrice = homePage.getProductPrice(productName);
        double dbPrice = DBUtil.getProductPriceFromDB(productName);

        Assert.assertEquals(uiPrice, dbPrice);
    }
}
