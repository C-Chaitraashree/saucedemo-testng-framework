package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By productTitles = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By sortDropdown = By.className("product_sort_container");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProductToCart(String productName) {
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId))).click();
    }

    public void removeProductFromCart(String productName) {
        String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId))).click();
    }

    public double getProductPrice(String productName) {
        List<WebElement> names = driver.findElements(productTitles);
        List<WebElement> prices = driver.findElements(productPrices);
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equalsIgnoreCase(productName)) {
                return Double.parseDouble(prices.get(i).getText().replace("$", ""));
            }
        }
        return -1;
    }

    public String getCartCount() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void selectSortOption(String value) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        new Select(dropdown).selectByValue(value);
    }

    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : driver.findElements(productTitles)) {
            names.add(element.getText());
        }
        return names;
    }

    public List<Double> getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement element : driver.findElements(productPrices)) {
            prices.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        return prices;
    }
}
