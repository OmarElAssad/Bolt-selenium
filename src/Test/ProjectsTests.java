package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectsPage;

public class ProjectsTests extends BaseTest {

    private static ProjectsPage projectsPage;
    private static DashboardPage dashboardPage;
    private static LoginPage loginPage;

    @Before
    public void setUp() {
        homePage.clickOnSignInLink();

        loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail("admin");
        loginPage.enterPassword("admin");
        loginPage.clickOnLoginButton();  

        dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.clickOnProjectsLink();

        projectsPage = new ProjectsPage(driver, wait);
    }

    // @After
    // public void tearDown() {
    //     dashboardPage.logout();
    // }

    // @Test
    // public void testDeleteAllProjects() {
    //     projectsPage.deleteAllProjects();
    // }

    @Test
    public void testValidCreationOfProjectWithChangeOfColor() {
        
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Omar");
        projectsPage.changeColorForProject();
        projectsPage.clickOnCreateButton();

        assertEquals("Urls don't match", "https://staging.boltqr.com/projects", driver.getCurrentUrl());
        assertEquals("Alert messages don't match", "Omar has been successfully created.", projectsPage.getAlertMessageText());
    }

    @Test
    public void testInvalidCreationOfProjectWithNoName() {
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.clickOnCreateButton();
        projectsPage.getProjectNameValidationMessag();

        assertEquals("Urls don't match.", "https://staging.boltqr.com/project-create", driver.getCurrentUrl());
        assertEquals("Validation message don't match.", "Please fill out this field.", projectsPage.getProjectNameValidationMessag());
    }

    @Test
    public void testDeletingAProject() {

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Omar");
        projectsPage.clickOnCreateButton();

        projectsPage.deleteProject();

        assertEquals("Alert messages don't match", "Omar has been successfully deleted.", projectsPage.getAlertMessageText());
    }

    @Test
    public void testFilteringProjectsTableBySearchInputField() {
        
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Omar");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Milomir");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Sonja");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Jelena");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnFilterButton();
        projectsPage.enterSearchInput("Jelena");
        projectsPage.clickOnFilterSubmitButton();
        
        List<String> projectNames = projectsPage.getNamesOfProjects();
        for (String name : projectNames) {
            assertTrue("Doesn't contain search value", name.contains("Jelena"));
        }
    }

    @Test
    public void testReorderingProjectsTableWithOrderByName() {
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Omar");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Milomir");
        projectsPage.clickOnCreateButton();

        assertEquals("First project names don't match.", "Omar", projectsPage.getFirstProjectNameBeforeFilter());
        
        
        projectsPage.clickOnFilterButton();
        projectsPage.clickOnOrderByDropDownMenu();
        projectsPage.clickOnOrderByName();
        projectsPage.clickOnFilterSubmitButton();

        assertEquals("Names don't match", "Milomir", projectsPage.getFirstProjectNameAfterFilter());
    }

    @Test
    public void testResultsPerPage() {
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Omar");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Milomir");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Sonja");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Jelena");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnFilterButton();
        projectsPage.clickOnResultsPerPageDropDownMenu();

        WebElement selectElement = driver.findElement(By.id("results_per_page"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByIndex(5);

        projectsPage.clickOnFilterSubmitButton();

        assertEquals("Values don't match", "500", projectsPage.getResultsPerPageValue());
    }
    


}




