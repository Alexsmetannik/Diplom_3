package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    private static final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By makeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private static final By accountButton = By.xpath("//p[text()='Личный Кабинет']");
    private static final By headText = By.xpath(".//h1[text()='Соберите бургер']");
    private static final By rollsTab = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[1]");
    private static final By rollsText = By.xpath("//h2[text()='Булки']");
    private static final By saucesTab = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[2]");
    private static final By saucesText = By.xpath("//h2[text()='Соусы']");
    private static final By fillingsTab = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[3]");
    private static final By fillingsText = By.xpath("//h2[text()='Соусы']");

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверка успешности авторизации")
    public boolean isMainPageOpenedWithLogin() {
        if (driver.findElement(makeOrderButton).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void clickOnAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Заголовок 'Соберите бургер' отображается")
    public Boolean isHeadTextOpened() {
        if (driver.findElement(headText).isDisplayed())  {
            return true;
        }
        return false;
    }

    @Step("Нажатие на вкладку 'Булки'")
    public void clickOnBunTab() {
        driver.findElement(rollsTab).click();
    }

    @Step("Проверка открытия вкладки 'Булки'")
    public boolean isRollsTextOpened() {
        if (driver.findElement(rollsText).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на вкладку 'Соусы'")
    public void clickOnSauceTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Проверка открытия вкладки 'Соусы'")
    public boolean isSaucesTextOpened() {
        if (driver.findElement(saucesText).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Step("Нажатие на вкладку 'Начинки'")
    public void clickOnFillingTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверка открытия вкладки 'Начинки'")
    public boolean isFillingsTextOpened() {
        if (driver.findElement(fillingsText).isDisplayed()) {
            return true;
        }
        return false;
    }
}
