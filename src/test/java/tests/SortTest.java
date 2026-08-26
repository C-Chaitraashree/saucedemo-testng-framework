package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortTest extends BaseTest {

    @DataProvider(name = "sortOptions")
    public Object[][] sortOptions() {
        return new Object[][]{
                {"az"},
                {"za"},
                {"lohi"},
                {"hilo"}
        };
    }

    @Test(dataProvider = "sortOptions", groups = {"regression"})
    public void testProductSortOrder(String sortValue) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.selectSortOption(sortValue);

        if (sortValue.equals("az") || sortValue.equals("za")) {
            List<String> actualNames = homePage.getAllProductNames();
            List<String> expectedNames = new ArrayList<>(actualNames);
            Collections.sort(expectedNames);
            if (sortValue.equals("za")) {
                Collections.reverse(expectedNames);
            }
            Assert.assertEquals(actualNames, expectedNames);
        } else {
            List<Double> actualPrices = homePage.getAllProductPrices();
            List<Double> expectedPrices = new ArrayList<>(actualPrices);
            Collections.sort(expectedPrices);
            if (sortValue.equals("hilo")) {
                Collections.reverse(expectedPrices);
            }
            Assert.assertEquals(actualPrices, expectedPrices);
        }
    }
}
