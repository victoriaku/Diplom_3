package site.nomoreparties.stellarburgers.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.nomoreparties.stellarburgers.ui.pageobjects.AccountPage;
import site.nomoreparties.stellarburgers.ui.pageobjects.ConstructorPage;
import site.nomoreparties.stellarburgers.ui.pageobjects.LoginPage;
import site.nomoreparties.stellarburgers.ui.pageobjects.RegistrationPage;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.api.constants.EndPoints.BASE_URL;

public class BaseTest {

    protected WebDriver driver;

    protected ConstructorPage constructorPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected AccountPage accountPage;

    @BeforeClass
    public static void setupRestAssured() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Before
    public void startBrowser() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            startChrome();
        } else if (browser.equals("yandex")) {
            startYandex();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();
    }

    private void startChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void startYandex() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\yandexdriver-25.4.0.1973-win64\\yandexdriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
