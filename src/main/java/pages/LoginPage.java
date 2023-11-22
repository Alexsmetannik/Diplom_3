package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By loginHeader = By.xpath("//h2[text()='Вход']");
    private static final By registrationButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private static final By emailField = By.xpath(".//input[@type ='text']");
    private static final By passwordField = By.xpath(".//input[@type ='password']");
    private static final By loginButton = By.xpath(".//button[text()='Войти']");
    private static final By recoveryButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickOnRegistrationButton() {
        WebElement element = driver.findElement(registrationButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
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

    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public void clickOnRecoveryButton() {
        WebElement element = driver.findElement(recoveryButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(recoveryButton).click();
    }

    @Step("Переход на экран авторизации")
    public Boolean isLoginPageOpened() {
        if (driver.findElement(loginHeader).isDisplayed())  {
            return true;
        }
        return false;
    }

}
