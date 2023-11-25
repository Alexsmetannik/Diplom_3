package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {
    private WebDriver driver;

    public AccountPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By PROFILE_TEXT = By.xpath("//a[text()='Профиль']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//p[text()='Конструктор']");
    private static final By LOGO_STELLAR_BURGERS = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private static final By EXIT_BUTTON = By.xpath(".//li/button[text()='Выход']");

    @Step("Проверка открытия экрана личного кабинета")
    public boolean isAccountPageOpened() {
        if (driver.findElement(PROFILE_TEXT).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void clickOnConstructorButton() {
        WebElement element = driver.findElement(CONSTRUCTOR_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step("Нажатие на логотип 'StellarBurgers'")
    public void clickOnLogoStellarBurgers() {
        WebElement element = driver.findElement(LOGO_STELLAR_BURGERS);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(LOGO_STELLAR_BURGERS).click();
    }

    @Step("Нажатие на кнопку 'Выход'")
    public void clickOnExitButton() {
        WebElement element = driver.findElement(EXIT_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(EXIT_BUTTON).click();
    }
}
