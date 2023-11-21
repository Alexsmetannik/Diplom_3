package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By mainHeader = By.xpath("//h1[text()='Соберите бургер']");
    private static final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private static final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");


    public void waitForLoadHeaderMainPage(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(mainHeader));
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверка успешности авторизации")
    public Boolean isMainPageOpenedWithLogin() {
        if (driver.findElement(makeOrderButton).isDisplayed()) {
            return true;
        }
        return false;
    }
}
