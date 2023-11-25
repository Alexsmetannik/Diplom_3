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

import static org.junit.Assert.assertTrue;

public class ConstructorTest{
    @Rule
    public DriverRule driverRule = new DriverRule();

    private User user;
    private UserClient userClient;
    private String bearerToken;
    private String token;
    private String email;
    private String password;


    @Before
    public void beforeConstructorTest(){
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
    @DisplayName("Check transition to 'Rolls' after login")
    @Description("Переход к разделу 'Булки' после авторизации")
    public void transitionToRollsTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        mainPage.clickOnSaucesTab();
        mainPage.clickOnRollsTab();
        boolean actualResult = mainPage.isRollsTextOpened();
        assertTrue("Tab is not correct", actualResult);
    }

    @Test
    @DisplayName("Check transition to 'Sauces' after login")
    @Description("Переход к разделу 'Соусы' после авторизации")
    public void transitionToSaucesTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        mainPage.clickOnSaucesTab();
        boolean actualResult = mainPage.isSaucesTextOpened();
        assertTrue("Tab is not correct", actualResult);
    }

    @Test
    @DisplayName("Check transition to 'Fillings' after login")
    @Description("Переход к разделу 'Начинки' после авторизации")
    public void transitionToFillingsTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.loginUser(email, password);
        mainPage.clickOnFillingsTab();
        boolean actualResult = mainPage.isFillingsTextOpened();
        assertTrue("Tab is not correct", actualResult);
    }

}
