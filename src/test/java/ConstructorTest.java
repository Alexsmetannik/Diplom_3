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

import static org.junit.Assert.assertTrue;

public class ConstructorTest {
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
    @DisplayName("Check transition to 'Rolls' after login")
    @Description("Переход к разделу 'Булки' после авторизации")
    public void transitionToRollsTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

        loginPage.loginUser(email, password);

        mainPage.clickOnFillingTab();
        mainPage.clickOnBunTab();
        boolean actualResult = mainPage.isRollsTextOpened();
        assertTrue("Tab is not correct", actualResult);
    }

    @Test
    @DisplayName("Check transition to 'Sauces' after login")
    @Description("Переход к разделу 'Соусы' после авторизации")
    public void transitionToSaucesTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

        loginPage.loginUser(email, password);

        mainPage.clickOnSauceTab();
        boolean actualResult = mainPage.isSaucesTextOpened();
        assertTrue("Tab is not correct", actualResult);
    }

    @Test
    @DisplayName("Check transition to 'Fillings' after login")
    @Description("Переход к разделу 'Начинки' после авторизации")
    public void transitionToFillingsTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(baseURL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);

        loginPage.loginUser(email, password);

        mainPage.clickOnFillingTab();
        boolean actualResult = mainPage.isFillingsTextOpened();
        assertTrue("Tab is not correct", actualResult);
    }

}
