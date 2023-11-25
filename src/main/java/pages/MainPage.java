package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By LOGIN_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By MAKE_ORDER_BUTTON = By.xpath("//button[text()='Оформить заказ']");
    private static final By ACCOUNT_BUTTON = By.xpath("//p[text()='Личный Кабинет']");
    private static final By HEAD_TEXT = By.xpath(".//h1[text()='Соберите бургер']");
    private static final By ROLLS_TAB = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[1]");
    private static final By SOUCES_TAB = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[2]");
    private static final By FILLINGS_TAB = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[3]");
    private static final String ACTIVE_TAB_TEXT = "tab_tab_type_current__2BEPc";

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
        WebElement element = driver.findElement(ROLLS_TAB);
        return element.getAttribute("class").contains(ACTIVE_TAB_TEXT);
    }

    @Step("Нажатие на вкладку 'Соусы'")
    public void clickOnSaucesTab() {
        driver.findElement(SOUCES_TAB).click();
    }

    @Step("Проверка открытия вкладки 'Соусы'")
    public boolean isSaucesTextOpened() {
        WebElement element = driver.findElement(SOUCES_TAB);
        return element.getAttribute("class").contains(ACTIVE_TAB_TEXT);
    }

    @Step("Нажатие на вкладку 'Начинки'")
    public void clickOnFillingsTab() {
        driver.findElement(FILLINGS_TAB).click();
    }

    @Step("Проверка открытия вкладки 'Начинки'")
    public boolean isFillingsTextOpened() {
        WebElement element = driver.findElement(FILLINGS_TAB);
        return element.getAttribute("class").contains(ACTIVE_TAB_TEXT);
    }
}
