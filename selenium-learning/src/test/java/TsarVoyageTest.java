import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by katem on 4/30/2017.
 */
@RunWith(JUnit4.class)
public class TsarVoyageTest extends TestCase {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\Users\\katem\\IdeaProjects\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void tabVenirEnRussie_opens() {
        driver.get("https://tsarvoyages.com");
        By tabVivreEnRussieSelector = By.xpath("//*[@id=\"header\"]/div[2]/div/div[3]/a");
        driver.findElement(tabVivreEnRussieSelector).click();
        By firstTourPriceSelector = By.xpath("//*[@id=\"page-page\"]/div/div[2]/div/div/div/div[3]/div/div/div[1]/div/ul/li[1]/div/div/div[1]/div[2]/div/span[2]/span[2]");
        String currency = driver.findElement(firstTourPriceSelector).getText();
        assertEquals("RUB", currency);
    }

}
