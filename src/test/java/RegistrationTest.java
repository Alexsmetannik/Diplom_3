import generators.DataGenerator;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
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

    private String email;
    private String password;
    private String name;

    @Before
    public void beforeRegistrationTest(){
        email = DataGenerator.getNewEmail();
        password = DataGenerator.getNewPassword();
        name = DataGenerator.getNewName();
    }

    @Test
    @DisplayName("Check successful user registration")
    @Description("Успешная регистрация пользователя")
    public void successfulRegistrationTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickOnLoginButton();
        loginPage.clickOnRegistrationButton();
        registrationPage.regUser(name, email, password);
        loginPage.loginUser(email, password);
        boolean actualResult = mainPage.isMainPageOpenedWithLogin();
        assertTrue(actualResult);

    }
}
