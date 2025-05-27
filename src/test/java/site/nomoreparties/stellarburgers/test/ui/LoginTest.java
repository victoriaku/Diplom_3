package site.nomoreparties.stellarburgers.test.ui;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.api.models.UserModel;
import site.nomoreparties.stellarburgers.test.BaseTest;
import site.nomoreparties.stellarburgers.ui.pageobjects.LoginPage;
import site.nomoreparties.stellarburgers.ui.pageobjects.RecoverPasswordPage;
import site.nomoreparties.stellarburgers.ui.pageobjects.RegistrationPage;

import static org.junit.Assert.assertTrue;
import static site.nomoreparties.stellarburgers.api.steps.UserSteps.*;
import static site.nomoreparties.stellarburgers.test.testdata.TestData.*;

public class LoginTest extends BaseTest {

    private final String email = getTestUserEmail();
    private final String password = getTestUserPassword();
    private final String name = getTestUserName();

    @Before
    public void setup() {
        createUser(new UserModel(email, password, name));
    }

    private void loginAndCheckSuccess() {
        loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        assertTrue("После успешного входа в систему на странице \"Конструктор\" появляется кнопка \"Оформить заказ\"",
                constructorPage.hasPlaceOrderButton());
    }

    @Test
    @DisplayName("Логин через кнопку \"Войти в аккаунт\" в \"Конструкторе\"")
    public void loginFromConstructorButtonSuccess() {
        constructorPage.clickLoginButton();
        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Логин через ссылку \"Личный кабинет\" в хедере")
    public void loginFromHeaderLinkSuccess() {
        constructorPage.clickAccountLink();
        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Логин через ссылку в форме регистрации")
    public void loginFromRegistrationFormLinkSuccess() {
        constructorPage.clickAccountLink();

        loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();

        registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();

        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Логин через ссылку в форме восстановления пароля")
    public void loginFromRecoverPasswordFormLinkSuccess() {
        constructorPage.clickAccountLink();

        loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordLink();

        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        recoverPasswordPage.clickLoginLink();

        loginAndCheckSuccess();
    }

    @After
    public void cleanUp() {
        removeUser(getAccessToken(loginUser(new UserModel(email, password))));
    }
}
