package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By loginHeader = By.xpath("//h2[text()='Вход']");
    private static final By registrationButton = By.className("Auth_link__1fOlj");
    private static final By emailField = By.xpath(".//input[@type ='text']");
    private static final By passwordField = By.xpath(".//input[@type ='password']");
    private static final By loginButton = By.xpath(".//button[text()='Войти']");

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickOnRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Авторизация пользователя")
    public void loginUser(String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
