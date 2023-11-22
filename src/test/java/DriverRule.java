import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class DriverRule extends ExternalResource {
    protected WebDriver driver;
    private WebDriver chromeDriver;
    private WebDriver yandexDriver;
    protected enum NameBrowser {
        YANDEX,
        CHROME
    }

    //  NameBrowser nameBrowser = NameBrowser.CHROME;
        NameBrowser nameBrowser = NameBrowser.YANDEX;

    @Override
    protected void before() {
        this.driver = getDriver(nameBrowser);

        switch (nameBrowser) {
            case CHROME:
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                ChromeDriverService service1 = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/chromedriver"))
                        .build();
                driver = new ChromeDriver(service1);
                break;

            case YANDEX:
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                ChromeDriverService service2 = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/yandexdriver"))
                        .build();
                driver = new ChromeDriver(service2);
                break;
            default:
                throw new RuntimeException("Browser is not selected");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        }

    private WebDriver getDriver(NameBrowser nameBrowser) {
        if ("CHROME".equals(nameBrowser)) {
            return chromeDriver;
        }
        else return yandexDriver;
    }

    @Override
    protected void after(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
