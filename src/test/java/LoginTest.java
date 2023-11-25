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
import pages.RecoveryPage;
import pages.RegistrationPage;
import static config.Enviroment.*;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    @Rule
    public DriverRule driverRule = new DriverRule();

    private String email;
    private String password;
    private User user;
    private UserClient userClient;
    private String bearerToken;
    private String token;


    @Before
    public void beforeLoginTest(){
        userClient = new UserClient();
        user = DataGenerator.getSuccessCreateUser();
        email = user.getEmail();
        password = user.getPassword();

        ValidatableResponse responseCreate = userClient.createUserRequest(user);
        bearerToken = responseCreate.extract().path("accessToken");
        token = bearerToken.substring(7);
    }

    @After
    public void deleteUser() {
        if(token != null){
            userClient.deleteUserRequest(token);
        }
    }

    @Test
    @DisplayName("Check login by click on MainPage")
    @Description("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginByMainPageTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        boolean actualResult = mainPage.isMainPageOpenedWithLogin();
        assertTrue("User is not login", actualResult);
    }

    @Test
    @DisplayName("Check login by click on AccountButton")
    @Description("Вход через кнопку 'Личный кабинет'")
    public void loginByAccountButtonTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickOnAccountButton();
        loginPage.loginUser(email, password);
        boolean actualResult = mainPage.isMainPageOpenedWithLogin();
        assertTrue("User is not login", actualResult);
    }

    @Test
    @DisplayName("Check login by click on RegistrationPage")
    @Description("Вход через кнопку в форме регистрации")
    public void loginByRegistrationPageTest() throws InterruptedException {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        boolean actualResult = mainPage.isMainPageOpenedWithLogin();
        assertTrue("User is not login", actualResult);
    }

    @Test
    @DisplayName("Check login by click on RecoveryPage")
    @Description("Вход через кнопку в форме восстановления пароля")
    public void loginByRecoveryPageTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RecoveryPage recoveryPage = new RecoveryPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRecoveryButton();
        recoveryPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        boolean actualResult = mainPage.isMainPageOpenedWithLogin();
        assertTrue("User is not login", actualResult);
    }
}
