package Test;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.DashboardPage;
import Pages.HomePage;
import Pages.LoginPage;

public class LoginTests {
    private static WebDriver driver;
    private static WebDriverWait wait;
    public static LoginPage loginPage;

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omar_\\Desktop\\Omar\\QA course\\Automation testing\\Workspace\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        driver.manage().window().maximize();

        driver.get("https://staging.boltqr.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSignInLink();
        loginPage = new LoginPage(driver, wait);
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

    @Test
    public void testLoginWithIncorrectData() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail("example@gmail.com");
        loginPage.enterPassword("pass");
        loginPage.clickOnLoginButton();

        assertEquals("Urls don't match.", "https://staging.boltqr.com/login", driver.getCurrentUrl());
        assertEquals("Error messages don't match", "Your login combination is invalid!", loginPage.getAlertMessageText());
    }

    @Test
    public void testLoginWithValidData() {
        loginPage.enterEmail("admin");
        loginPage.enterPassword("admin");
        loginPage.clickOnLoginButton();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);

        assertEquals("Urls don't match.", "https://staging.boltqr.com/dashboard", driver.getCurrentUrl());
        assertEquals("Alert messages don't match", "Welcome back, Milomir! You've successfully logged in.", dashboardPage.getAlertMessageText());
        assertEquals("Page headings don't match", "Dashboard", dashboardPage.getPageHeadingText());

        dashboardPage.logout();
    }

    @Test
    public void tesLoginWithEmptyFields() {

        loginPage.clickOnLoginButton();

        assertEquals("Urls don't match.", "https://staging.boltqr.com/login", driver.getCurrentUrl());
        assertEquals("Validation messages don't match.", "Please fill out this field.", loginPage.getEmailFieldValidationMessage());
    }
}
