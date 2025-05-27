package site.nomoreparties.stellarburgers.test.ui;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.api.models.UserModel;
import site.nomoreparties.stellarburgers.test.BaseTest;
import site.nomoreparties.stellarburgers.ui.pageobjects.AccountPage;
import site.nomoreparties.stellarburgers.ui.pageobjects.LoginPage;

import static org.junit.Assert.assertTrue;
import static site.nomoreparties.stellarburgers.api.steps.UserSteps.*;
import static site.nomoreparties.stellarburgers.test.testdata.TestData.*;

public class LogoutTest extends BaseTest {

    private final String email = getTestUserEmail();
    private final String password = getTestUserPassword();
    private final String name = getTestUserName();

    @Before
    public void setup() {
        createUser(new UserModel(email, password, name));
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке \"Выйти\" в личном кабинете")
    public void logoutSuccess() {
        constructorPage.clickAccountLink();

        loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        constructorPage.clickAccountLink();

        accountPage = new AccountPage(driver);
        accountPage.clickExitButton();

        assertTrue("После выхода из аккаунта должен произойти переход на страницу авторизации",
                loginPage.waitForPageLoad());
    }

    @After
    public void cleanUp() {
        removeUser(getAccessToken(loginUser(new UserModel(email, password))));
    }
}