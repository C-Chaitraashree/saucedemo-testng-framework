package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"invalid_user", "wrong_password", false}
        };
    }

    @Test(dataProvider = "loginData", groups = {"smoke", "regression"})
    public void testLoginWithMultipleUsers(String username, String password, boolean shouldSucceed) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login(username, password);

        if (shouldSucceed) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed());
        }
    }
}
