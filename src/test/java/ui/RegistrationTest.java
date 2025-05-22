package ui;

import api.models.UserModel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ui.pageobjects.AccountPage;
import ui.pageobjects.LoginPage;
import ui.pageobjects.RegistrationPage;

import static api.steps.UserSteps.*;
import static org.junit.Assert.assertTrue;
import static testdata.TestData.*;

public class RegistrationTest extends BaseTest{

    private final String name = getTestUserName();
    private final String email = getTestUserEmail();
    private String password;

    @Before
    public void setup(){
        constructorPage.clickAccountLink();
        loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Регистрация пользователя с валидными данными")
    public void registerUserSuccess(){
        password = getTestUserPassword();
        registrationPage.registerUser(name, email, password);

        loginPage.login(email, password);
        constructorPage.clickAccountLink();
        accountPage = new AccountPage(driver);

        assertTrue("После успешной регистрации можно залогиниться и перейти в \"Личный кабинет\"",
                accountPage.hasExitButton());
    }

    @Test
    @DisplayName("Ошибка при регистрации пользователя с паролем длиной менее 6 символов")
    public void registerUserWith4CharsPasswordShowsError(){
        password = getTestUserPassword(4);
        registrationPage.registerUser(name, email, password);
        assertTrue("При вводе пароля длиной менее 6 символов должна появиться ошибка \"Некорректный пароль\"",
                registrationPage.hasIncorrectPasswordError());
    }

    @After
    public void cleanUp(){
        String token = getAccessToken(loginUser(new UserModel(email, password)));
        if (token != null){
            removeUser(token);
        }
    }
}
