package site.nomoreparties.stellarburgers.ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends Page {

    private static final By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на кнопку \"Выход\"")
    public void clickExitButton(){
        driver.findElement(EXIT_BUTTON).click();
    }

    @Step("Ожидать загрузки страницы \"Личный кабинет\"")
    public boolean waitForPageLoad() {
        return waitForVisibilityOfElementLocated(EXIT_BUTTON);
    }
}
