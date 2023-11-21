package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By registrationHeader = By.xpath("//h2[text()='Регистрация']");
    private static final By nameField = By.xpath(".//fieldset[1]/div/div/input");
    private static final By emailField = By.xpath(".//fieldset[2]/div/div/input");
    private static final By passwordField = By.xpath(".//input[@name='Пароль']");
    private static final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By errorMessage = By.xpath("//p[text()='Некорректный пароль']");
    private static final By loginButton = By.xpath(".//a[text()='Войти']");



    @Step("Регистрация пользователя")
    public void regUser(String name, String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationHeader));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registrationButton).click();
    }

    @Step("Проверка отображения ошибки некорректного пароля")
    public boolean hasErrorMessage() {
        if (driver.findElement(errorMessage).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

}
