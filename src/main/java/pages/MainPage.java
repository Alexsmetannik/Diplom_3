package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By MAKE_ORDER_BUTTON = By.xpath("//button[text()='Оформить заказ']");
    private static final By ACCOUNT_BUTTON = By.xpath("//p[text()='Личный Кабинет']");
    private static final By HEAD_TEXT = By.xpath(".//h1[text()='Соберите бургер']");
    private static final By ROLLS_TAB = By.xpath("//span[text()='Булки']");
    private static final By ROLLS_TEXT = By.xpath("//h2[text()='Булки']");
    private static final By SOUCES_TAB = By.xpath("//span[text()='Соусы']");
    private static final By SOUCES_TEXT = By.xpath("//h2[text()='Соусы']");
    private static final By FILLINGS_TAB = By.xpath("//span[text()='Начинки']");
    private static final By FILLINGS_TEXT = By.xpath("//h2[text()='Начинки']");

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickOnLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Проверка успешности авторизации")
    public boolean isMainPageOpenedWithLogin() {
        if (driver.findElement(MAKE_ORDER_BUTTON).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void clickOnAccountButton() {
        driver.findElement(ACCOUNT_BUTTON).click();
    }

    @Step("Заголовок 'Соберите бургер' отображается")
    public Boolean isHeadTextOpened() {
        if (driver.findElement(HEAD_TEXT).isDisplayed())  {
            return true;
        }
        return false;
    }

    @Step("Нажатие на вкладку 'Булки'")
    public void clickOnRollsTab() {
        driver.findElement(ROLLS_TAB).click();
    }

    @Step("Проверка открытия вкладки 'Булки'")
    public boolean isRollsTextOpened() {
        if (driver.findElement(ROLLS_TEXT).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на вкладку 'Соусы'")
    public void clickOnSaucesTab() {
        driver.findElement(SOUCES_TAB).click();
    }

    @Step("Проверка открытия вкладки 'Соусы'")
    public boolean isSaucesTextOpened() {
        if (driver.findElement(SOUCES_TEXT).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на вкладку 'Начинки'")
    public void clickOnFillingsTab() {
        driver.findElement(FILLINGS_TAB).click();
    }

    @Step("Проверка открытия вкладки 'Начинки'")
    public boolean isFillingsTextOpened() {
        if (driver.findElement(FILLINGS_TEXT).isDisplayed()) {
            return true;
        }
        return false;
    }
}
