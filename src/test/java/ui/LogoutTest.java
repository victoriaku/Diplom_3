package ui;

import api.models.UserModel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ui.pageobjects.AccountPage;
import ui.pageobjects.LoginPage;

import static api.steps.UserSteps.*;
import static org.junit.Assert.assertTrue;
import static testdata.TestData.*;

public class LogoutTest extends BaseTest{

    private static final String EMAIL = getTestUserEmail();
    private static final String PASSWORD = getTestUserPassword();
    private static final String NAME = getTestUserName();

    @BeforeClass
    public static void setupUser(){
        createUser(new UserModel(EMAIL, PASSWORD, NAME));
    }

    @Before
    public void setup(){
        constructorPage.clickAccountLink();

        loginPage = new LoginPage(driver);
        loginPage.login(EMAIL, PASSWORD);

        constructorPage.clickAccountLink();

        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке \"Выйти\" в личном кабинете")
    public void logoutSuccess(){
        accountPage.clickExitButton();
        assertTrue("После выхода из аккаунта должен произойти переход на страницу авторизации",
                loginPage.hasLoginButton());
    }

    @AfterClass
    public static void cleanUp(){
        removeUser(getAccessToken(loginUser(new UserModel(EMAIL, PASSWORD))));
    }
}
