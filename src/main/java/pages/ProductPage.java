package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    static List<String> NAME_PRODUCT_ADDED_TO_WISHLIST = new ArrayList<>();
    static List<String> NAME_PRODUCT_ADDED_TO_CART = new ArrayList<>();

    @FindBy(xpath = "//button[@data-test-id='add-button']")
    private WebElement buttonAddToBagProduct;

    @FindBy(xpath = "//div[@class='product-title']")
    private List<WebElement> nameProductsList;

    @FindBy(xpath = "//li[@class='_2tqSFxt']//div[@class='_2g_Mcyd']//dd")
    private List<WebElement> nameProductsListInCart;

    @FindBy(xpath = "//div[@class='layout-aside']//span[@class='QYurw']")
    private WebElement heartIconOnProduct;

    @FindBy(xpath = "//select[@data-id='sizeSelect']")
    private List<WebElement> dropDawnListWithProductSize;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectSizeProduct() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        for (int i = 0; i < dropDawnListWithProductSize.size(); i++) {
            String str_countDropDownList = String.valueOf(i);
            List<WebElement> listWithSizeProduct = driver.findElements(By.xpath("//select[contains(@id,'size-select-" + str_countDropDownList + "')]//option[not(@disabled)]"));
            if (listWithSizeProduct.size() > 1) {
                driver.findElement(By.xpath("//select[contains(@id,'size-select-" + str_countDropDownList + "')]")).click();
                listWithSizeProduct.get(getRandomNumber(1, listWithSizeProduct.size() - 1)).click();
            }
        }
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
    }

    public void addToBagProduct() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        for (WebElement webElement : nameProductsList) {
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElement));
            NAME_PRODUCT_ADDED_TO_CART.add(webElement.getText());
        }
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonAddToBagProduct));
        buttonAddToBagProduct.click();
    }

    public void clickHeartIcon() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
        for (WebElement webElement : nameProductsList) {
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElement));
            NAME_PRODUCT_ADDED_TO_WISHLIST.add(webElement.getText());
            waitForAjaxToComplete(DEFAULT_TIMEOUT);
        }
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(heartIconOnProduct));
        heartIconOnProduct.click();
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
    }
}
