package api.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.models.UserModel;

import static api.constants.EndPoints.*;
import static io.restassured.RestAssured.given;

public class UserSteps {

    @Step ("Создать пользователя")
    public static Response createUser(UserModel user){
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(CREATE_USER_PATH);
    }

    @Step ("Получить accessToken пользователя")
    public static String getAccessToken(Response userResponse){
        String accessToken = userResponse
                .then()
                .extract()
                .path("accessToken");
        return accessToken == null ? null : accessToken.replaceFirst("Bearer ", "");
    }

    @Step ("Авторизовать пользователя")
    public static Response loginUser(UserModel user){
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(LOGIN_USER_PATH);
    }

    @Step ("Удалить пользователя")
    public static Response removeUser(String token){
        return given()
                .auth().oauth2(token)
                .when()
                .delete(REMOVE_USER_PATH);
    }
}
