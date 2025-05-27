package site.nomoreparties.stellarburgers.ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage  extends Page{

    private static final By REGISTRATION_LINK = By.linkText("Зарегистрироваться");
    private static final By RECOVER_PASSWORD_LINK = By.linkText("Восстановить пароль");

    // Поля формы "Вход"
    private static final By EMAIL_INPUT = By.xpath(".//label[text()='Email']/parent::*/input");
    private static final By PASSWORD_INPUT = By.xpath(".//label[text()='Пароль']/parent::*/input");

    private static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на ссылку \"Зарегистрироваться\"")
    public void clickRegistrationLink(){
        driver.findElement(REGISTRATION_LINK).click();
    }

    @Step("Нажать на ссылку \"Восстановить пароль\"")
    public void clickRecoverPasswordLink(){
        driver.findElement(RECOVER_PASSWORD_LINK).click();
    }

    @Step("Ввести данные в поле \"Email\"")
    public void setEmail(String email){
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    @Step("Ввести данные в поле \"Пароль\"")
    public void setPassword(String password){
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    @Step ("Заполнить форму входа в аккаунт и нажать на кнопку \"Войти\"")
    public void login(String email, String password){
        waitForPageLoad();
        setEmail(email);
        setPassword(password);

        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Ожидать загрузки страницы входа в аккаунт")
    public boolean waitForPageLoad() {
        return waitForVisibilityOfElementLocated(LOGIN_BUTTON);
    }
}
