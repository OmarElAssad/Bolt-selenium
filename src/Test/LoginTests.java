package test;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    private static LoginPage loginPage;

    @Before
    public void setUp() {
        homePage.clickOnSignInLink();
        loginPage = new LoginPage(driver, wait);
    }

    @After
    public void tearDown() {
        driver.get("https://staging.boltqr.com/");
    }

    @Test
    public void testLoginWithIncorrectData() {
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
