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
import pages.RecoveryPage;
import pages.RegistrationPage;
import static config.Enviroment.baseURL;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    @Rule
    public DriverRule driverRule = new DriverRule();

    private String email;
    private String password;
    private String name;
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
    @DisplayName("Check login by click on MainPage")
    @Description("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginByMainPageTest() {
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
        assertTrue("User is not login", actualResult);
    }

    @Test
    @DisplayName("Check login by click on AccountButton")
    @Description("Вход через кнопку 'Личный кабинет'")
    public void loginByAccountButtonTest() {
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
        assertTrue("User is not login", actualResult);
    }

    @Test
    @DisplayName("Check login by click on RegistrationPage")
    @Description("Вход через кнопку в форме регистрации")
    public void loginByRegistrationPageTest() throws InterruptedException {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

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
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        RecoveryPage recoveryPage = new RecoveryPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

        loginPage.clickOnRecoveryButton();
        recoveryPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        boolean actualResult = mainPage.isMainPageOpenedWithLogin();
        assertTrue("User is not login", actualResult);
    }
}
