package test;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static HomePage homePage;
    
    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omar_\\Desktop\\Omar\\QA course\\Automation testing\\Workspace\\chromedriver.exe");
        driver = new ChromeDriver();

        // System.setProperty("webdriver.gecko.driver", "C:\\Users\\omar_\\Desktop\\Omar\\QA course\\Automation testing\\Workspace\\geckodriver.exe");
        // driver = new FirefoxDriver();

        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        Dimension tablet = new Dimension(1024, 600);
        Dimension desktop = new Dimension(1920, 1080);

        driver.manage().window().setSize(tablet);
        driver.get("https://staging.boltqr.com/");   
    }

    // @AfterClass
    // public static void tearDownClass() {
    //     driver.quit();
    // }
}
