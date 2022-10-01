package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps {

    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    SearchProductsPage searchProductPage;
    ProductPage productPage;
    WishlistPage wishlistPage;
    CartPages cartPages;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        searchProductPage = pageFactoryManager.getSearchProductPage();
        productPage = pageFactoryManager.getProductPage();
        wishlistPage = pageFactoryManager.getWishlistPage();
        cartPages = pageFactoryManager.getCartPage();

        homePage.openHomePage(url);
    }

    @And("User makes search by keyword {string}")
    public void makesSearchByKeyword(final String keyword) {
        homePage.searchByKeyword(keyword);
    }

    @And("User selects {string} options from search autocomplete list")
    public void selectOneOptionsFromSearchAutocompleteList(final String number_option) {
        homePage.selectOneOptionsFromSearchAutocompleteList(number_option);
    }

    @And("User check that search was performed by {string} keyword search")
    public void checkThatSearchWasPerformedBySearch(final String keyword) {
        homePage.checkThatSearchWasPerformedBySearch(keyword);
    }

    @And("User check that search was performed by search autocomplete")
    public void checkThatSearchWasPerformedBySearchAutocomplete() {
        homePage.checkThatSearchWasPerformedBySearchAutocomplete();
    }

    @And("User click on {string} product")
    public void clickOnRandomProduct(final String number_product) {
        searchProductPage.clickRandomProduct(number_product);
    }

    @And("User selects size on clothing")
    public void userChoiceSizeOnClothing() {
        productPage.selectSizeProduct();
    }

    @And("User click on ADD TO BAG button")
    public void clickAddToBagProduct() {
        productPage.addToBagProduct();
    }

    @And("User checks if product is added to cart")
    public void checksIfProductIsAddedToCart() {
        cartPages.checksIfProductIsAddedToCart();
    }

    @And("User selects different gender")
    public void selectsDifferentGender() {
        homePage.selectsDifferentGender();
    }

    @And("User checks if gender has changed")
    public void checksIfGenderHasChanged() {
        homePage.checksIfGenderHasChanged();
    }

    @And("User selects category")
    public void selectsCategory() {
        homePage.selectsCategory();
    }

    @And("User checks if search by selected category has taken place")
    public void checksIfSearchBySelectedCategoryHasTakenPlace() {
        searchProductPage.checksIfSearchBySelectedCategoryHasTakenPlace();
    }

    @And("User click on heart icon on top bar")
    public void clickOnHeartIconOnTopBar() {
        homePage.clickOnHeartIconOnTopBar();
    }

    @And("User checks if product is added to wishlist")
    public void checksIfProductIsAddedToWishlist() {
        wishlistPage.checksIfProductIsAddedToWishlist();
    }

    @And("User click on heart icon")
    public void clickOnHeartIcon() {
        productPage.clickHeartIcon();
    }

    @And("User click on search button")
    public void clickOnSearchButton() {
        homePage.clickOnSearchButton();
    }

    @And("User click on delete button from wishlist")
    public void clickOnDeleteProductButtonFromWishlist() {
        wishlistPage.clickOnDeleteProductButtonFromWishlist();
    }

    @And("User click on delete button from cart")
    public void clickOnDeleteProductButtonFromCart() {
        cartPages.clickOnDeleteProductButtonFromCart();
    }

    @And("User click on bag icon on top bar")
    public void clickOnBagIconOnTopBar() {
        homePage.clickOnBagIconOnTopBar();
    }

    @And("User click on view bag button")
    public void clickOnViewBagButton() {
        homePage.clickOnViewBagButton();
    }

    @And("User click on Save for later button")
    public void clickOnSaveForLaterButton() {
        cartPages.clickOnSaveForLaterButton();
    }

    @And("User click on MOVE TO BAG button")
    public void clickOnMoveToBagButton() {
        wishlistPage.clickOnMoveToBagButton();
    }

    @And("User checks if product is deleted from wishlist")
    public void checksIfProductIsDeletedFromWishlist() {
        wishlistPage.checksIfProductIsDeletedFromWishlist();
    }

    @And("User checks if product is deleted from cart")
    public void checksIfProductIsDeletedFromCart() {
        cartPages.checksIfProductIsDeletedFromCart();
    }

    @And("User checks if product is added to cart from wishlist")
    public void checksIfProductIsAddedToCartFromWishlist() {
        cartPages.checksIfProductIsAddedToCartFromWishlist();
    }

    @And("User checks if product is added to wishlist from cart")
    public void checksIfProductIsAddedToWishlistFromCart() {
        wishlistPage.checksIfProductIsAddedToWishlistFromCart();
    }

    @And("User click on flag on top bar")
    public void clickOnFlagOnTopBar() {
        homePage.clickOnFlagOnTopBar();
    }

    @And("User choice country in {string} SHOP IN in drop down list")
    public void choiceCountryInDropDownList(final String country) {
        homePage.choiceCountryInDropDownList(country);
    }

    @And("User choice currency in {string} CURRENCY in drop down list")
    public void choiceCurrencyInDropDownList(final String currency) {
        homePage.choiceCurrencyInDropDownList(currency);
    }

    @And("User click on UPDATE PREFERENCES button")
    public void clickOnUpdatePreferencesButton() {
        homePage.clickOnUpdatePreferencesButton();
    }

    @And("User checks that {string} country and {string} currency are chosen correctly")
    public void checksThatCountryAndCurrencyAreChosenCorrectly(final String country, final String currency) {
        homePage.checksThatCountryAndCurrencyAreChosenCorrectly(country, currency);
    }
}
