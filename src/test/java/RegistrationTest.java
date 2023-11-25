import api.LoginUser;
import api.User;
import api.UserClient;
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
import static config.Enviroment.*;

import static org.junit.Assert.*;

public class RegistrationTest {

    @Rule
    public DriverRule driverRule = new DriverRule();

    private String newEmail;
    private String newPassword;
    private String newName;
    private final static String ERROR_PASSWORD = "12345";
    private User user;
    private UserClient userClient;
    private LoginUser login;
    private String bearerToken;
    private String token;
    private boolean actualResult;

    @Before
    public void beforeRegistrationTest(){
        userClient = new UserClient();
        newEmail = DataGenerator.getNewEmail();
        newPassword = DataGenerator.getNewPassword();
        newName = DataGenerator.getNewName();
        user = new User(newEmail, newPassword, newName);
        login = new LoginUser();
    }

    @After
    public void deleteUser() {
        if (actualResult) {
            ValidatableResponse responseLogin = userClient.loginUserRequest(login.from(user));
            bearerToken = responseLogin.extract().path("accessToken");
            token = bearerToken.substring(7);
            userClient.deleteUserRequest(token);
        }
    }

    @Test
    @DisplayName("Check successful user registration")
    @Description("Успешная регистрация пользователя")
    public void successfulRegistrationTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(newName, newEmail, newPassword);
        loginPage.loginUser(newEmail, newPassword);
        actualResult = mainPage.isMainPageOpenedWithLogin();
        assertTrue("User is not login",actualResult);
    }

    @Test
    @DisplayName("Check error during registration for an incorrect password")
    @Description("Проверка ошибки при регистрации для некорректного пароля. Минимальный пароль — шесть символов.")
    public void errorRegistrationIncorrectPasswordTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(newName, newEmail, ERROR_PASSWORD);
        actualResult = !registrationPage.hasErrorMessage();
        assertFalse("Message not exist", actualResult);
    }
}
