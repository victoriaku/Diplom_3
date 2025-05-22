package ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ui.pageobjects.ConstructorPage.BUNS_TITLE;
import static ui.pageobjects.LoginPage.EMAIL_INPUT;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    //Хедер
    public static final By LOGO_LINK = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    public static final By CONSTRUCTOR_LINK = By.xpath(".//p[text()='Конструктор']/parent::a");
    public static final By ACCOUNT_LINK = By.linkText("Личный Кабинет");

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step("Проверить, находится ли элемент в зоне видимости")
    public boolean isElementInViewport(By locator) {
        String script = "var rect = arguments[0].getBoundingClientRect();" +
                "return (" +
                "rect.top >= 0 &&" +
                "rect.left >= 0 &&" +
                "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&" +
                "rect.right <= (window.innerWidth || document.documentElement.clientWidth)" +
                ");";
        try {
            return wait.until(d ->
                    (Boolean) ((JavascriptExecutor) driver).executeScript(script, driver.findElement(locator)));
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Нажать на логотип \"Stellar Burgers\"")
    public void clickLogoLink(){
        driver.findElement(LOGO_LINK).click();

        waitForVisibilityOfElementLocated(BUNS_TITLE);
    }

    @Step("Нажать на ссылку \"Конструктор\" в хедере")
    public void clickConstructorLink(){
        driver.findElement(CONSTRUCTOR_LINK).click();

        waitForVisibilityOfElementLocated(BUNS_TITLE);
    }

    @Step ("Нажать на ссылку \"Личный Кабинет\"")
    public void clickAccountLink(){
        driver.findElement(ACCOUNT_LINK).click();

        waitForVisibilityOfElementLocated(EMAIL_INPUT);
    }

    @Step("Ожидать видимости элемента по локатору: {locator}")
    public boolean waitForVisibilityOfElementLocated(By locator){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        }
        catch (TimeoutException e){
            System.err.println("Элемент не появился на странице: " + locator);
            return false;
        }
    }
}
