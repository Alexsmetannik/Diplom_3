import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.time.Duration;

public class DriverRule extends ExternalResource {
    protected WebDriver driver;
    private WebDriver chromeDriver;
    private WebDriver yandexDriver;

    String nameBrowser = System.getProperty("nameBrowser");

    @Override
    protected void before() {
        this.driver = getDriver(nameBrowser);

        switch (nameBrowser) {
            case "CHROME":
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                ChromeDriverService service_CHROME = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/chromedriver"))
                        .build();
                driver = new ChromeDriver(service_CHROME);
                break;

            case "YANDEX":
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                ChromeDriverService service_YANDEX = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/yandexdriver"))
                        .build();
                driver = new ChromeDriver(service_YANDEX);
                break;
            default:
                throw new RuntimeException("Browser is not selected");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        }

    private WebDriver getDriver(String nameBrowser) {
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
