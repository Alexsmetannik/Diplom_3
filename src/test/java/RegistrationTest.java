import api.DeleteUser;
import api.LoginUser;
import generators.DataGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.*;

public class RegistrationTest {

    @Rule
    public DriverRule driverRule = new DriverRule();

    private final static String baseURL = "https://stellarburgers.nomoreparties.site/";
    private String email;
    private String password;
    private String name;
    private final static String errorPassword = "1234";
    private LoginUser loginUser;
    private DeleteUser deleteUser;
    private String bearerToken;
    private String token;

    @Before
    public void beforeRegistrationTest(){
        email = DataGenerator.getNewEmail();
        password = DataGenerator.getNewPassword();
        name = DataGenerator.getNewName();
        loginUser = new LoginUser(email, password);
    }

    @After
    public void deleteUser() {
        ValidatableResponse responseLogin = loginUser.loginUserRequest(loginUser);
        bearerToken = responseLogin.extract().path("accessToken");
        token = bearerToken.substring(7);

        deleteUser = new DeleteUser();
        if(token != null){
            deleteUser.deleteUserRequest(token);
        }
    }

    @Test
    @DisplayName("Check successful user registration")
    @Description("Успешная регистрация пользователя")
    public void successfulRegistrationTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);
        loginPage.loginUser(email, password);
        boolean actualResult = mainPage.isMainPageOpenedWithLogin();
        assertTrue("User is not login",actualResult);
    }

    @Test
    @DisplayName("Check error during registration for an incorrect password")
    @Description("Проверка ошибки при регистрации для некорректного пароля. Минимальный пароль — шесть символов.")
    public void errorRegistrationIncorrectPasswordTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, errorPassword);
        boolean actualResult = registrationPage.hasErrorMessage();
        assertTrue("Message not exist", actualResult);
    }
}
