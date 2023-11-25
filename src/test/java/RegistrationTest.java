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

    private String email;
    private String password;
    private String name;
    private final static String ERROR_PASSWORD = "1234";
    private User user;
    private UserClient userClient;
    private String bearerToken;
    private String token;
    private boolean isCreatedUser;

    @Before
    public void beforeRegistrationTest(){
        email = DataGenerator.getNewEmail();
        password = DataGenerator.getNewPassword();
        name = DataGenerator.getNewName();
        userClient = new UserClient();
        user = new User(email, password);
    }

    @After
    public void deleteUser() {
        if (isCreatedUser) {
            ValidatableResponse responseLogin = userClient.loginUserRequest(user);
            bearerToken = responseLogin.extract().path("accessToken");
            token = bearerToken.substring(7);

            if(token != null){
                userClient.deleteUserRequest(token);
            }
        }
    }

    @Test
    @DisplayName("Check successful user registration")
    @Description("Успешная регистрация пользователя")
    public void successfulRegistrationTest() {
        isCreatedUser = true;
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

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
        isCreatedUser = false;
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, ERROR_PASSWORD);
        boolean actualResult = registrationPage.hasErrorMessage();
        assertTrue("Message not exist", actualResult);
    }
}
