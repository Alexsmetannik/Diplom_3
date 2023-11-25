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

    private static final By REGISTRATION_HEADER = By.xpath("//h2[text()='Регистрация']");
    private static final By NAME_FIELD = By.xpath(".//fieldset[1]/div/div/input");
    private static final By EMAIL_FIELD = By.xpath(".//fieldset[2]/div/div/input");
    private static final By PASSWORD_FIELD = By.xpath(".//input[@name='Пароль']");
    private static final By REGISTRATION_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");
    private static final By ERROR_MESSAGE = By.xpath("//p[text()='Некорректный пароль']");
    private static final By LOGIN_BUTTON = By.xpath(".//a[text()='Войти']");



    @Step("Регистрация пользователя")
    public void regUser(String name, String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTRATION_HEADER));
        driver.findElement(NAME_FIELD).sendKeys(name);
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Проверка отображения ошибки некорректного пароля")
    public boolean hasErrorMessage() {
        if (driver.findElement(ERROR_MESSAGE).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickOnLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

}
