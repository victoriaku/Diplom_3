package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pageobjects.AccountPage;
import ui.pageobjects.ConstructorPage;
import ui.pageobjects.LoginPage;
import ui.pageobjects.RegistrationPage;

import static api.constants.EndPoints.BASE_URL;

public class BaseTest {

    protected WebDriver driver;

    protected ConstructorPage constructorPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected AccountPage accountPage;


    @BeforeClass
    public static void setupRestAssured(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Before
    public void startBrowser(){
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")){
            startChrome();
        }
        else if (browser.equals("yandex")){
            startYandex();
        }

        driver.manage().window().maximize();
        constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();
    }

    private void startChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void startYandex(){
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\yandexdriver-25.4.0.1973-win64\\yandexdriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
