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
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class DifferentTransitionsTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    private final static String baseURL = "https://stellarburgers.nomoreparties.site/";
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
    @DisplayName("Check transition by click on accountButton after login")
    @Description("Переход по клику на 'Личный кабинет' после авторизации")
    public void transitionByClickOnAccountButtonTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

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
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

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
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

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
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

        loginPage.loginUser(email, password);
        mainPage.clickOnAccountButton();
        accountPage.clickOnExitButton();
        boolean actualResult = loginPage.isLoginPageOpened();
        assertTrue("Page is not correct", actualResult);
    }
}
