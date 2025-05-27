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

public class TransitionTest extends BaseTest {

    private final String email = getTestUserEmail();
    private final String password = getTestUserPassword();
    private final String name = getTestUserName();

    @Before
    public void setup() {
        createUser(new UserModel(email, password, name));

        constructorPage.clickAccountLink();

        loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        constructorPage.clickAccountLink();

        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Переход в личный кабинет кликом по ссылке в хедере")
    public void clickAccountLinkShouldSwitchToAccountPage() {
        assertTrue("Должен произойти переход в личный кабинет", accountPage.waitForPageLoad());
    }

    @Test
    @DisplayName("Переход из личного кабинета в \"Конструктор\" кликом по логотипу")
    public void clickLogoShouldSwitchToConstructorPage() {
        accountPage.clickLogoLink();

        assertTrue("Должен произойти переход на страницу \"Конструктор\"",
                constructorPage.waitForPageLoad());
    }

    @Test
    @DisplayName("Переход из личного кабинета в \"Конструктор\" кликом по ссылке в хедере")
    public void clickConstructorLinkShouldSwitchToConstructorPage() {
        accountPage.clickConstructorLink();

        assertTrue("Должен произойти переход на страницу \"Конструктор\"",
                constructorPage.waitForPageLoad());
    }

    @After
    public void cleanUp() {
        removeUser(getAccessToken(loginUser(new UserModel(email, password))));
    }
}
