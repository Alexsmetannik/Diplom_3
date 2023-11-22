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

    private static final By profileText = By.xpath("//a[text()='Профиль']");
    private static final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private static final By logoStellarBurgers = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private static final By exitButton = By.xpath(".//li/button[text()='Выход']");

    @Step("Проверка открытия экрана личного кабинета")
    public boolean isAccountPageOpened() {
        if (driver.findElement(profileText).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void clickOnConstructorButton() {
        WebElement element = driver.findElement(constructorButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на логотип 'StellarBurgers'")
    public void clickOnLogoStellarBurgers() {
        WebElement element = driver.findElement(logoStellarBurgers);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(logoStellarBurgers).click();
    }

    @Step("Нажатие на кнопку 'Выход'")
    public void clickOnExitButton() {
        WebElement element = driver.findElement(exitButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(exitButton).click();
    }
}
