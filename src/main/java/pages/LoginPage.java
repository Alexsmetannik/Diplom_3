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

    private static final By LOGIN_HEADER = By.xpath("//h2[text()='Вход']");
    private static final By REGISTRATION_BUTTON = By.xpath(".//a[text()='Зарегистрироваться']");
    private static final By EMAIL_FIELD = By.xpath(".//input[@type ='text']");
    private static final By PASSWORD_FIELD = By.xpath(".//input[@type ='password']");
    private static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти']");
    private static final By RECOVERY_BUTTON = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickOnRegistrationButton() {
        WebElement element = driver.findElement(REGISTRATION_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Авторизация пользователя")
    public void loginUser(String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_HEADER));
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public void clickOnRecoveryButton() {
        WebElement element = driver.findElement(RECOVERY_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(RECOVERY_BUTTON).click();
    }

    @Step("Переход на экран авторизации")
    public Boolean isLoginPageOpened() {
        if (driver.findElement(LOGIN_HEADER).isDisplayed())  {
            return true;
        }
        return false;
    }

}
