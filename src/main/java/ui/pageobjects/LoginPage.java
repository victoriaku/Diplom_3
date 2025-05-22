package ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ui.pageobjects.ConstructorPage.BUNS_TITLE;
import static ui.pageobjects.RegistrationPage.REGISTRATION_BUTTON;

public class LoginPage  extends Page{

    public static final By REGISTRATION_LINK = By.linkText("Зарегистрироваться");
    public static final By RECOVER_PASSWORD_LINK = By.linkText("Восстановить пароль");

    // Поля формы "Вход"
    public static final By EMAIL_INPUT = By.xpath(".//label[text()='Email']/parent::*/input");
    public static final By PASSWORD_INPUT = By.xpath(".//label[text()='Пароль']/parent::*/input");

    public static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на ссылку \"Зарегистрироваться\"")
    public void clickRegistrationLink(){
        driver.findElement(REGISTRATION_LINK).click();

        waitForVisibilityOfElementLocated(REGISTRATION_BUTTON);
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
        setEmail(email);
        setPassword(password);
        driver.findElement(LOGIN_BUTTON).click();

        waitForVisibilityOfElementLocated(BUNS_TITLE);
    }

    @Step("Проверить, отображается ли кнопка \"Войти\"")
    public boolean hasLoginButton(){
        return !driver.findElements(LOGIN_BUTTON).isEmpty();
    }
}
