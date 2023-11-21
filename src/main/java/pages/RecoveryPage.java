package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoveryPage {
    private WebDriver driver;

    public RecoveryPage(WebDriver driver){
        this.driver = driver;
    }

    // private static final By recoveryHeader = By.xpath("//h2[text()='Восстановление пароля']");
    private static final By loginButton = By.xpath("//a[text()='Войти']");

    @Step("Нажатие на кнопку 'Войти'")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

}
