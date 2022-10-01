package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pages.CartPages.NAME_PRODUCT_MOVE_TO_WISHLIST_FROM_CART;
import static pages.ProductPage.NAME_PRODUCT_ADDED_TO_WISHLIST;

public class WishlistPage extends BasePage{

    static List<String> NAME_PRODUCT_DELETED_FROM_WISHLIST = new ArrayList<>();
    static List<String> NAME_PRODUCT_MOVE_TO_BAG_FROM_WISHLIST = new ArrayList<>();

    @FindBy(xpath = "//div[@class='overflowFade_VtoIq']//p")
    private List<WebElement> productNameListInWishlist;

    @FindBy(xpath = "//button[@aria-label='Delete']")
    private List<WebElement> buttonDeleteProductFromWishlistList;

    @FindBy(xpath = "//button[@class='toBagButton_mYSbq toBagButton_LHRGf']")
    private List<WebElement> buttonMoveToBagProductList;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public void checksIfProductIsAddedToWishlist() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        for (String webElement : NAME_PRODUCT_ADDED_TO_WISHLIST) {
            boolean isWishlist = false;
            for (WebElement webElementWishlist : productNameListInWishlist) {
                explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElementWishlist));
                if (webElement.contains(webElementWishlist.getText())) {
                    isWishlist = true;
                }
            }
            assertTrue(isWishlist);
        }
    }

    public void checksIfProductIsDeletedFromWishlist() {
        driver.navigate().refresh();
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        if (productNameListInWishlist.size() == 0) {
            assertTrue(true);
        } else {
            for (String webElement : NAME_PRODUCT_DELETED_FROM_WISHLIST) {
                boolean isWishlist = true;
                for (WebElement webElementWishlist : productNameListInWishlist) {
                    if (!webElement.equals(webElementWishlist.getText())) {
                        isWishlist = false;
                    }
                }
                assertFalse(isWishlist);
            }
        }
    }

    public void clickOnDeleteProductButtonFromWishlist() {
        implicitWait(DEFAULT_TIMEOUT);
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
        for (int i = NAME_PRODUCT_ADDED_TO_WISHLIST.size() - 1; i >= 0; i--) {
            implicitWait(DEFAULT_TIMEOUT);
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonDeleteProductFromWishlistList.get(i)));
            implicitWait(DEFAULT_TIMEOUT);
            NAME_PRODUCT_DELETED_FROM_WISHLIST.add(productNameListInWishlist.get(i).getText());
            waitForAjaxToComplete(DEFAULT_TIMEOUT);
            implicitWait(DEFAULT_TIMEOUT);
            buttonDeleteProductFromWishlistList.get(i).click();
            waitForAjaxToComplete(DEFAULT_TIMEOUT);
            implicitWait(DEFAULT_TIMEOUT);
        }
    }

    public void clickOnMoveToBagButton() {
        implicitWait(DEFAULT_TIMEOUT);
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        implicitWait(DEFAULT_TIMEOUT);
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
        implicitWait(DEFAULT_TIMEOUT);
        for (int i = NAME_PRODUCT_ADDED_TO_WISHLIST.size() - 1; i >= 0; i--) {
            implicitWait(DEFAULT_TIMEOUT);
            waitForAjaxToComplete(DEFAULT_TIMEOUT);
            implicitWait(DEFAULT_TIMEOUT);
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonMoveToBagProductList.get(i)));
            implicitWait(DEFAULT_TIMEOUT);
            NAME_PRODUCT_MOVE_TO_BAG_FROM_WISHLIST.add(productNameListInWishlist.get(i).getText());
            implicitWait(DEFAULT_TIMEOUT);
            buttonMoveToBagProductList.get(i).click();
            implicitWait(DEFAULT_TIMEOUT);
            waitForAjaxToComplete(DEFAULT_TIMEOUT);
            implicitWait(DEFAULT_TIMEOUT);
        }
    }

    public void checksIfProductIsAddedToWishlistFromCart() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        for (String webElement : NAME_PRODUCT_MOVE_TO_WISHLIST_FROM_CART) {
            boolean isCart = false;
            for (WebElement webElementCart : productNameListInWishlist) {
                explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElementCart));
                if (webElement.contains(webElementCart.getText())) {
                    isCart = true;
                }
            }
            assertTrue(isCart);
        }
    }

}
