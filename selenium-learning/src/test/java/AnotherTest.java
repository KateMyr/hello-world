import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by katem on 4/23/2017.
 */
public class AnotherTest {

    private static ChromeDriverService service;
    private WebDriver driver;


    @BeforeClass
    public static void createAndStartService() throws IOException {
        //Install ChromeDrvierService with the path where we have chrome driver itself
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
        //Step 1- Driver Instantiation: Instantiate driver object as ChromeDriver
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    //We should add @Test annotation that JUnit will run below method
    @Test
    //Start to write our test method. It should ends with "Test"
    public void chromeTest() {


        //Step 2- Navigation: Open a website
        driver.navigate().to("https://www.teknosa.com/");

        //Step 3- Assertion: Check its title is correct
        //assertEquals method Parameters: Message, Expected Value, Actual Value
        Assert.assertEquals("Title check failed!", "Teknosa Alışveriş Sitesi - Herkes İçin Teknoloji", driver.getTitle());

        //Step 4- Close Driver
        driver.close();

        //Step 5- Quit Driver
        driver.quit();
    }
}

