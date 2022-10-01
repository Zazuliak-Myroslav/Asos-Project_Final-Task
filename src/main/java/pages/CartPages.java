package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pages.ProductPage.NAME_PRODUCT_ADDED_TO_CART;
import static pages.WishlistPage.NAME_PRODUCT_MOVE_TO_BAG_FROM_WISHLIST;

public class CartPages extends BasePage{

    static List<String> NAME_PRODUCT_DELETED_FROM_CART = new ArrayList<>();
    static List<String> NAME_PRODUCT_MOVE_TO_WISHLIST_FROM_CART= new ArrayList<>();

    @FindBy(xpath = "//button[@aria-label='Delete this item']")
    private List<WebElement> buttonDeleteProductFromCart;

    @FindBy(xpath = "//button[@class='bag-item-moveToSaved']")
    private List<WebElement> buttonSaveForLaterProduct;

    @FindBy(xpath = "//p[@class='bag-item-name']")
    private List<WebElement> productNameInCart;

    public CartPages(WebDriver driver) {
        super(driver);
    }

    public void clickOnDeleteProductButtonFromCart() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        for (int i = productNameInCart.size() - 1; i >= 0; i--) {
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(productNameInCart.get(i)));
            NAME_PRODUCT_DELETED_FROM_CART.add(productNameInCart.get(i).getText());
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonDeleteProductFromCart.get(i)));
            buttonDeleteProductFromCart.get(i).click();
        }
    }

    public void checksIfProductIsAddedToCart() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
        for (String webElement : NAME_PRODUCT_ADDED_TO_CART) {
            boolean isCart = false;
            for (WebElement webElementWishlist : productNameInCart) {
                explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElementWishlist));
                if (webElement.contains(webElementWishlist.getText())) {
                    isCart = true;
                }
            }
            assertTrue(isCart);
        }
    }

    public void checksIfProductIsDeletedFromCart() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        for (String webElement : NAME_PRODUCT_MOVE_TO_WISHLIST_FROM_CART) {
            boolean isCart = true;
            for (WebElement webElementCart : productNameInCart) {
                explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElementCart));
                if (!webElement.equals(webElementCart.getText())) {
                    isCart = false;
                }
            }
            assertFalse(isCart);
        }
    }

    public void checksIfProductIsAddedToCartFromWishlist() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
        System.out.println(NAME_PRODUCT_MOVE_TO_BAG_FROM_WISHLIST.size());
        System.out.println(productNameInCart.size());
        for (String s : NAME_PRODUCT_MOVE_TO_BAG_FROM_WISHLIST) {
            boolean isCart = false;
            for (WebElement webElement : productNameInCart) {
                explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElement));
                if (s.contains(webElement.getText())) {
                    System.out.println("true");
                    isCart = true;
                }
            }
            assertTrue(isCart);
        }
    }

    public void clickOnSaveForLaterButton() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
        for (int i = productNameInCart.size() - 1; i >= 0; i--) {
            waitForAjaxToComplete(DEFAULT_TIMEOUT);
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(productNameInCart.get(i)));
            NAME_PRODUCT_MOVE_TO_WISHLIST_FROM_CART.add(productNameInCart.get(i).getText());
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonSaveForLaterProduct.get(i)));
            buttonSaveForLaterProduct.get(i).click();
            waitForAjaxToComplete(DEFAULT_TIMEOUT);
        }
    }

}
