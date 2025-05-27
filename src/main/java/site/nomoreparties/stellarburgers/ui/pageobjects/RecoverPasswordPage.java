package site.nomoreparties.stellarburgers.ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage extends Page{

    private static final By LOGIN_LINK = By.linkText("Войти");

    public RecoverPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на ссылку \"Войти\"")
    public void clickLoginLink(){
        driver.findElement(LOGIN_LINK).click();
    }

    @Step("Ожидать загрузки страницы восстановления пароля")
    public boolean waitForPageLoad() {
        return waitForVisibilityOfElementLocated(LOGIN_LINK);
    }
}
