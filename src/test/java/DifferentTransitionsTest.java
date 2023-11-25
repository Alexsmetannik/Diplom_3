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
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import static config.Enviroment.*;

import static org.junit.Assert.assertTrue;

public class DifferentTransitionsTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    private String email;
    private String password;
    private User user;
    private UserClient userClient;
    private String bearerToken;
    private String token;


    @Before
    public void beforeDifferentTransitionsTest(){
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
    @DisplayName("Check transition by click on accountButton after login")
    @Description("Переход по клику на 'Личный кабинет' после авторизации")
    public void transitionByClickOnAccountButtonTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        mainPage.clickOnAccountButton();
        boolean actualResult = accountPage.isAccountPageOpened();
        assertTrue("Page is not correct", actualResult);
    }

    @Test
    @DisplayName("Check transition from AccountPage to MainPage by click on constructor after login")
    @Description("Переход из личного кабинета в конструктор по клику на 'Конструктор' после авторизации")
    public void transitionByClickOnConstructorTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        mainPage.clickOnAccountButton();
        accountPage.clickOnConstructorButton();
        boolean actualResult = mainPage.isHeadTextOpened();
        assertTrue("Page is not correct", actualResult);
    }

    @Test
    @DisplayName("Check transition from AccountPage to MainPage by click on Stellar Burgers after login")
    @Description("Переход из личного кабинета в конструктор по клику на логотип 'Stellar Burgers' после авторизации")
    public void transitionByClickOnStellarBurgersTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        mainPage.clickOnAccountButton();
        accountPage.clickOnLogoStellarBurgers();
        boolean actualResult = mainPage.isHeadTextOpened();
        assertTrue("Page is not correct", actualResult);
    }

    @Test
    @DisplayName("Check logout")
    @Description("Выход из аккаунта")
    public void logoutTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        mainPage.clickOnAccountButton();
        accountPage.clickOnExitButton();
        boolean actualResult = loginPage.isLoginPageOpened();
        assertTrue("Page is not correct", actualResult);
    }
}
