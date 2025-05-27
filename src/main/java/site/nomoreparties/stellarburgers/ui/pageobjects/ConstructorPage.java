package site.nomoreparties.stellarburgers.ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage extends Page {

    private static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";

    // Ссылки перехода к разделам "Конструктора"
    private static final String BUNS_SECTION_LINK = ".//span[text()='Булки']/parent::div";
    private static final String SAUCES_SECTION_LINK = ".//span[text()='Соусы']/parent::div";
    private static final String FILLINGS_SECTION_LINK = ".//span[text()='Начинки']/parent::div";
    private static final String SECTION_LINK_SELECTED = "[contains(@class, 'tab_tab_type_current__2BEPc')]";

    // Заголовки разделов "Конструктора"
    private static final By BUNS_TITLE = By.xpath(".//h2[text()='Булки']");
    private static final By SAUCES_TITLE = By.xpath(".//h2[text()='Соусы']");
    private static final By FILLINGS_TITLE = By.xpath(".//h2[text()='Начинки']");

    private static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By PLACE_ORDER_BUTTON = By.xpath(".//button[text()='Оформить заказ']");

    public ConstructorPage(WebDriver driver){
        super(driver);
    }

    @Step ("Перейти на главную страницу \"Stellar Burgers\" - \"Конструктор\"")
    public void openConstructorPage(){
        driver.get(MAIN_URL);
    }

    @Step ("Нажать на кнопку \"Войти в аккаунт\"")
    public void clickLoginButton(){
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step ("Перейти к разделу \"Булки\"")
    public void clickBunsSectionLink(){
        driver.findElement(By.xpath(BUNS_SECTION_LINK)).click();
    }

    @Step ("Перейти к разделу \"Соусы\"")
    public void clickSaucesSectionLink(){
        driver.findElement(By.xpath(SAUCES_SECTION_LINK)).click();
    }

    @Step ("Перейти к разделу \"Начинки\"")
    public void clickFillingsSectionLink(){
        driver.findElement(By.xpath(FILLINGS_SECTION_LINK)).click();
    }

    @Step ("Проверить, выбран ли раздел \"Булки\"")
    public boolean isBunsSectionSelected(){
        return !driver.findElements(By.xpath(BUNS_SECTION_LINK + SECTION_LINK_SELECTED)).isEmpty();
    }

    @Step ("Проверить, выбран ли раздел \"Соусы\"")
    public boolean isSaucesSectionSelected(){
        return !driver.findElements(By.xpath(SAUCES_SECTION_LINK + SECTION_LINK_SELECTED)).isEmpty();
    }

    @Step ("Проверить, выбран ли раздел \"Начинки\"")
    public boolean isFillingsSectionSelected(){
        return !driver.findElements(By.xpath(FILLINGS_SECTION_LINK + SECTION_LINK_SELECTED)).isEmpty();
    }

    @Step ("Проверить, находится ли заголовок \"Булки\" в зоне видимости")
    public boolean isBunsTitleInViewport(){
        return isElementInViewport(BUNS_TITLE);
    }

    @Step ("Проверить, находится ли заголовок \"Соусы\" в зоне видимости")
    public boolean isSaucesTitleInViewport(){
        return isElementInViewport(SAUCES_TITLE);
    }

    @Step ("Проверить, находится ли заголовок \"Начинки\" в зоне видимости")
    public boolean isFillingsTitleInViewport(){
        return isElementInViewport(FILLINGS_TITLE);
    }

    @Step("Проверить, отображается ли кнопка \"Оформить заказ\"")
    public boolean hasPlaceOrderButton(){
        return !driver.findElements(PLACE_ORDER_BUTTON).isEmpty();
    }

    @Step("Ожидать загрузки страницы \"Конструктор\"")
    public boolean waitForPageLoad() {
        return waitForVisibilityOfElementLocated(BUNS_TITLE);
    }
}
