package site.nomoreparties.stellarburgers.ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends Page {

    // Поля формы "Регистрация"
    private static final By NAME_INPUT = By.xpath(".//label[text()='Имя']/parent::*/input");
    private static final By EMAIL_INPUT = By.xpath(".//label[text()='Email']/parent::*/input");
    private static final By PASSWORD_INPUT = By.xpath(".//label[text()='Пароль']/parent::*/input");

    private static final By REGISTRATION_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");

    // Ссылка в тексте "Уже зарегистрированы? Войти"
    private static final By LOGIN_LINK = By.linkText("Войти");

    // Ошибка "Некорректный пароль"
    private static final By INCORRECT_PASSWORD_ERROR = By.xpath(".//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести данные в поле \"Имя\"")
    public void setName(String name){
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    @Step("Ввести данные в поле \"Email\"")
    public void setEmail(String email){
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    @Step("Ввести данные в поле \"Пароль\"")
    public void setPassword(String password){
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    @Step ("Заполнить форму регистрации и нажать на кнопку \"Зарегистрироваться\"")
    public void registerUser(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);

        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Нажать на ссылку \"Войти\"")
    public void clickLoginLink(){
        driver.findElement(LOGIN_LINK).click();
    }

    @Step("Проверить, отображается ли ошибка \"Некорректный пароль\"")
    public boolean hasIncorrectPasswordError(){
        return !driver.findElements(INCORRECT_PASSWORD_ERROR).isEmpty();
    }

    @Step("Ожидать загрузки страницы регистрации")
    public boolean waitForPageLoad() {
        return waitForVisibilityOfElementLocated(REGISTRATION_BUTTON);
    }
}
