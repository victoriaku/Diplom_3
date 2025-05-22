package ui;

import api.models.UserModel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ui.pageobjects.LoginPage;
import ui.pageobjects.RecoverPasswordPage;
import ui.pageobjects.RegistrationPage;

import static api.steps.UserSteps.*;
import static org.junit.Assert.assertTrue;
import static testdata.TestData.*;

public class LoginTest extends BaseTest{

    private static final String EMAIL = getTestUserEmail();
    private static final String PASSWORD = getTestUserPassword();
    private static final String NAME = getTestUserName();

    @BeforeClass
    public static void setupUser(){
        createUser(new UserModel(EMAIL, PASSWORD, NAME));
    }

    @Before
    public void setup(){
        loginPage = new LoginPage(driver);
    }

    private void loginAndCheckSuccess() {
        loginPage.login(EMAIL, PASSWORD);
        assertTrue("После успешного входа в систему на странице \"Конструктор\" появляется кнопка \"Оформить заказ\"",
                constructorPage.hasPlaceOrderButton());
    }

    @Test
    @DisplayName("Логин через кнопку \"Войти в аккаунт\" в \"Конструкторе\"")
    public void loginFromConstructorButtonSuccess(){
        constructorPage.clickLoginButton();
        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Логин через ссылку \"Личный кабинет\" в хедере")
    public void loginFromHeaderLinkSuccess(){
        constructorPage.clickAccountLink();
        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Логин через ссылку в форме регистрации")
    public void loginFromRegistrationFormLinkSuccess(){
        constructorPage.clickAccountLink();
        loginPage.clickRegistrationLink();

        registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();

        loginAndCheckSuccess();
    }

    @Test
    @DisplayName("Логин через ссылку в форме восстановления пароля")
    public void loginFromRecoverPasswordFormLinkSuccess(){
        constructorPage.clickAccountLink();
        loginPage.clickRecoverPasswordLink();

        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        recoverPasswordPage.clickLoginLink();

        loginAndCheckSuccess();
    }

    @AfterClass
    public static void cleanUp(){
        removeUser(getAccessToken(loginUser(new UserModel(EMAIL, PASSWORD))));
    }
}
