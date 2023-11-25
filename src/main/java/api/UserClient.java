package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static config.Enviroment.BASE_URL;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String PATH_LOGIN = "api/auth/login";
    private static final String PATH_DELETE = "api/auth/user";

    @Step("Login user")
    public ValidatableResponse loginUserRequest(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL + PATH_LOGIN)
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse deleteUserRequest(String token) {
        return given()
                .auth().oauth2(token)
                .when()
                .delete(BASE_URL + PATH_DELETE)
                .then();
    }
}
