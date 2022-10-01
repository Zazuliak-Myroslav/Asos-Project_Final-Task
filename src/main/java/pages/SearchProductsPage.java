package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertTrue;
import static pages.HomePage.ACTIVE_CATEGORY;

public class SearchProductsPage extends BasePage {

    @FindBy(xpath = "//div[@data-auto-id='productTileDescription']//h2")
    private List<WebElement> productNameList;

    public SearchProductsPage(WebDriver driver) {
        super(driver);
    }

    public void clickRandomProduct(final String number_product) {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(productNameList.get(0)));
        implicitWait(DEFAULT_TIMEOUT);
        productNameList.get(Integer.parseInt(number_product) - 1).click();
    }

    public void checksIfSearchBySelectedCategoryHasTakenPlace() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        String name_category = ACTIVE_CATEGORY.toLowerCase(Locale.ROOT).replaceAll(" ", "+").replaceAll("&", "%26");
        assertTrue(driver.getCurrentUrl().contains(name_category));
    }

}
