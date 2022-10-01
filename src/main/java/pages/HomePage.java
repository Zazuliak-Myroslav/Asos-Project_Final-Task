package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePage extends BasePage {

    private static String NAME_AUTOCOMPLETE = "";
    private static String ACTIVE_GENDER = "";
    static String ACTIVE_CATEGORY = "";

    @FindBy(xpath = "//div[@data-testid='topbar']//button[@data-testid='country-selector-btn']")
    private WebElement buttonPreferencesOnTopBar;

    @FindBy(xpath = "//select[@id='country']")
    private WebElement dropDawnListCountry;

    @FindBy(xpath = "//select[@id='country']//option")
    private List<WebElement> dropDawnListCountryList;

    @FindBy(xpath = "//select[@id='currency']")
    private WebElement dropDawnListCurrency;

    @FindBy(xpath = "//select[@id='currency']//option")
    private List<WebElement> dropDawnListCurrencyList;

    @FindBy(xpath = "//select[@id='country']//option[@selected]")
    private WebElement countrySelected;

    @FindBy(xpath = "//select[@id='currency']//option[@selected]")
    private WebElement currencySelected;

    @FindBy(xpath = "//button[@data-testid='save-country-button']")
    private WebElement buttonUpdatePreferences;

    @FindBy(xpath = "//input[@id='chrome-search']")
    private WebElement inputSearch;

    @FindBy(xpath = "//button[@data-testid='search-button-inline']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//button[@data-testid='miniBagIcon']")
    private WebElement buttonBagOnTopBar;

    @FindBy(xpath = "//a[@data-test-id='bag-link']")
    private WebElement buttonViewBag;

    @FindBy(xpath = "//div[contains(@class,'NOZiOAo')]")
    private WebElement divSearchAutocomplete;

    @FindBy(xpath = "//li[contains(@id,'search-result')]")
    private List<WebElement> dropDownSearchAutocompleteList;

    @FindBy(xpath = "//div[@id='search-term-banner']//p[contains(@class,'vp')]")
    private WebElement nameCategory;

    @FindBy(xpath = "//a[@class='TO7hyVB _3B0kHbC _3AH1eDT Tar7aO0']")
    private WebElement buttonActiveGender;

    @FindBy(xpath = "//a[@class='TO7hyVB _3B0kHbC _3AH1eDT']")
    private WebElement buttonInactiveGender;

    @FindBy(xpath = "//nav[@class='_3EAPxMS']//button[@class='_2syfS2P _1njuHQk otVu6ZN']")
    private List<WebElement> buttonCategoryList;

    @FindBy(xpath = "//nav[@class='_3EAPxMS']//div[@class='_238FTjh']//ul//li//ul//li//a")
    private List<WebElement> buttonDropDownCategoryList;

    @FindBy(xpath = "//a[@aria-label='Saved Items']")
    private WebElement heartIconOnTopBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(final String url) {
        driver.get(url);
    }

    public void searchByKeyword(final String keyword) {
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(inputSearch));
        inputSearch.sendKeys(keyword);
        implicitWait(DEFAULT_TIMEOUT);
    }

    public void clickOnSearchButton() {
        implicitWait(DEFAULT_TIMEOUT);
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonSearch));
        implicitWait(DEFAULT_TIMEOUT);
        buttonSearch.click();
        implicitWait(DEFAULT_TIMEOUT);
    }

    public void selectOneOptionsFromSearchAutocompleteList(final String number_option) {
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(divSearchAutocomplete));
        NAME_AUTOCOMPLETE = dropDownSearchAutocompleteList.get(Integer.parseInt(number_option) - 1).getText();
        System.out.println(number_option);
        dropDownSearchAutocompleteList.get(Integer.parseInt(number_option) - 1).click();
    }

    public void checkThatSearchWasPerformedBySearch(final String keyword) {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(nameCategory.getText().substring(1, nameCategory.getText().length() - 1).equalsIgnoreCase(keyword));
    }

    public void checkThatSearchWasPerformedBySearchAutocomplete() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(NAME_AUTOCOMPLETE.contains(nameCategory.getText().substring(1, nameCategory.getText().length() - 1)));
    }

    public void selectsDifferentGender() {
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonInactiveGender));
        ACTIVE_GENDER = buttonInactiveGender.getText();
        buttonInactiveGender.click();
    }

    public void checksIfGenderHasChanged() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(driver.getCurrentUrl().contains(ACTIVE_GENDER.toLowerCase(Locale.ROOT)));
    }

    public void selectsCategory() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        int random_number_category_list = getRandomNumber(0, buttonCategoryList.size() - 1);
        action.moveToElement(buttonCategoryList.get(random_number_category_list)).perform();
        int min_index = 0;
        boolean first_min = false;
        int max_index = 0;
        for (int i = 0; i < buttonDropDownCategoryList.size(); i++) {
            if (buttonDropDownCategoryList.get(i).isDisplayed()) {
                if (!first_min) {
                    first_min = true;
                    min_index = i;
                }
                if (i > max_index) max_index = i;
            }
        }
        int random_number_drop_dawn_category_list = getRandomNumber(min_index, max_index);
        ACTIVE_CATEGORY = buttonDropDownCategoryList.get(random_number_drop_dawn_category_list).getText();
        buttonDropDownCategoryList.get(random_number_drop_dawn_category_list).click();
    }

    public void clickOnHeartIconOnTopBar() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(heartIconOnTopBar));
        implicitWait(DEFAULT_TIMEOUT);
        heartIconOnTopBar.click();
    }

    public void clickOnBagIconOnTopBar() {
        implicitWait(DEFAULT_TIMEOUT);
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(buttonViewBag));
        if (!buttonViewBag.isDisplayed()) {
            implicitWait(DEFAULT_TIMEOUT);
            explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonBagOnTopBar));
            implicitWait(DEFAULT_TIMEOUT);
            buttonBagOnTopBar.click();
            implicitWait(DEFAULT_TIMEOUT);
        }
    }

    public void clickOnViewBagButton() {
        waitForAjaxToComplete(DEFAULT_TIMEOUT);
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonViewBag));
        implicitWait(DEFAULT_TIMEOUT);
        buttonViewBag.click();
    }

    public void clickOnFlagOnTopBar() {
        waitForPageLoadComplete(DEFAULT_TIMEOUT);
        buttonPreferencesOnTopBar.click();
    }

    public void choiceCountryInDropDownList(final String country) {
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(dropDawnListCountry));
        dropDawnListCountry.click();
        for (WebElement webElement : dropDawnListCountryList) {
            if (webElement.getText().equals(country)) webElement.click();
        }
    }

    public void choiceCurrencyInDropDownList(final String currency) {
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(dropDawnListCurrency));
        dropDawnListCurrency.click();
        for (WebElement webElement : dropDawnListCurrencyList) {
            if (webElement.getText().equals(currency)) webElement.click();
        }
    }

    public void clickOnUpdatePreferencesButton() {
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(buttonUpdatePreferences));
        buttonUpdatePreferences.click();
    }

    public void checksThatCountryAndCurrencyAreChosenCorrectly(final String country, final String currency) {
        explicitWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(buttonUpdatePreferences));
        assertEquals(countrySelected.getText(), country);
        assertEquals(currencySelected.getText(), currency);
    }
}
