package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    @After
    public void tearDown() {
        dashboardPage.logout();
    }

    /* The delete all projects method is for your convenience, to be used if needed after conducting multiple tests */

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
    public void testEditingAProjectByChangingName() {
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("Test");
        projectsPage.changeColorForProject();
        projectsPage.clickOnCreateButton();

        assertEquals("Urls don't match", "https://staging.boltqr.com/projects", driver.getCurrentUrl());
        assertEquals("Alert messages don't match", "Test has been successfully created.", projectsPage.getAlertMessageText());

        projectsPage.clickOnKebabDropDownMenu();
        projectsPage.clickOnEditOption();
        projectsPage.changeProjectName("qa test");
        projectsPage.clickOnCreateButton();

        assertEquals("Alert messages don't match", "qa test has been successfully updated.", projectsPage.getAlertMessageText());

        projectsPage.clickOnProjectsLink();
    }

    @Test
    public void testSearchInputFieldFilter() {
        
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
    public void testOrderByFilter() {
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
        projectsPage.clickOnOrderByDropDownMenu();
        projectsPage.enterOrderByIndexValue(1);

        projectsPage.clickOnFilterSubmitButton();

        assertEquals("Names don't match", "name", projectsPage.getOrderByAttributeValue());
    }

    @Test
    public void testOrderTypeFilter() {
        
        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("1");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("2");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("3");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnCreateAProjectLink();
        projectsPage.enterNameToProject("4");
        projectsPage.clickOnCreateButton();

        projectsPage.clickOnFilterButton();
        projectsPage.clickOnOrderTypeDropDownMenu();
        projectsPage.enterOrderTypeIndexValue(1);

        projectsPage.clickOnFilterSubmitButton();

        assertEquals("Names don't match", "DESC", projectsPage.getOrderTypeAttributeValue());
    }

    @Test
    public void testResultsPerPageFilter() {
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
        projectsPage.enterResultsPerPageIndexValue(5);

        projectsPage.clickOnFilterSubmitButton();

        assertEquals("Values don't match", "500", projectsPage.getResultsPerPageAttributeValue());
    }
    
}




