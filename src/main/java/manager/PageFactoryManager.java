package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public SearchProductsPage getSearchProductPage() {
        return new SearchProductsPage(driver);
    }

    public ProductPage getProductPage() {
        return new ProductPage(driver);
    }

    public WishlistPage getWishlistPage() {
        return new WishlistPage(driver);
    }

    public CartPages getCartPage() {
        return new CartPages(driver);
    }

}
