package pages;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By projectsLink = By.xpath("/html/body/div[2]/div/div[2]/ul/li[5]/a");

    private By header2 = By.tagName("h2");

    private By createProjectLink = By.className("btn-primary");
    private By nameTheProject = By.id("name");
    private By createButton = By.name("submit");
    private By alertmessage = By.className("alert-success");

    private By filterButton = By.className("filters-button");

    private By searchInputField = By.id("search");

    private By searchByDropDownMenu = By.id("search_by");

    private By orderByDropDownMenu = By.id("order_by");
    private By orderByCreatedDateTime = By.xpath("//*[@id='order_by']/option[1]");
    private By orderByName = By.xpath("//*[@id='order_by']/option[2]");

    private By orderTypeDropDownMenu = By.id("order_type");

    private By resultsPerPageDropDownMenu = By.id("results_per_page");

    private By filterSubmitButton = By.name("submit");

    private By projectName = By.xpath("/html/body/div[2]/section/div[1]/main/div/div[2]/table/tbody/tr/td[1]/a");

    private By firstProjectNameBeforeFilter = By.xpath("/html/body/div[2]/section/div[1]/main/div/div[3]/table/tbody/tr[1]/td[1]/a");
    private By firstProjectNameAfterFilter = By.xpath("/html/body/div[2]/section/div[1]/main/div/div[2]/table/tbody/tr[1]/td[1]/a");

    private By kebabDropDownMenu = By.className("text-secondary");
    private By deleteOption = By.linkText("Delete");
    private By deleteProject = By.className("btn-danger");


    public ProjectsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickOnProjectsLink() {
        driver.findElement(projectsLink).click();
    }

    public void clickOnCreateAProjectLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createProjectLink)).click();
    }

    public void enterNameToProject(String name) {
        driver.findElement(nameTheProject).sendKeys(name);
    }

    public void changeColorForProject() {
        String sampleJS = "document.getElementById('color').value='#ffffff'";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(sampleJS);
    }
    
    public void clickOnCreateButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createButton)).click();
    }

    public String getProjectNameValidationMessag() {
        return driver.findElement(nameTheProject).getAttribute("validationMessage");
    }

    public String getAlertMessageText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertmessage)).getText();
    }

    public void clickOnFilterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterButton)).click();
    }

    public void enterSearchInput(String searchInput) {
        driver.findElement(searchInputField).sendKeys(searchInput);
    }

    public void clickOnSearchByDropDownMenu() {
        driver.findElement(searchByDropDownMenu).click();
    }

    public void clickOnOrderByDropDownMenu() {
        driver.findElement(orderByDropDownMenu).click();
    }

    public void clickOnOrderByCreatedDateName() {
        driver.findElement(orderByCreatedDateTime).click();
    }

    public void clickOnOrderByName() {
        driver.findElement(orderByName).click();
    }

    public void clickOnOrderTypeDropDownMenu() {
        driver.findElement(orderTypeDropDownMenu).click();
    }

    public void clickOnResultsPerPageDropDownMenu() {
        driver.findElement(resultsPerPageDropDownMenu).click();
    }

    public String getResultsPerPageValue() {
        return driver.findElement(By.xpath("//*[@id='results_per_page']")).getAttribute("value");
    }
        
    public void clickOnFilterSubmitButton() {
        driver.findElement(filterSubmitButton).click();
    }

    public String getProjectName() {
        return driver.findElement(projectName).getText();
    }

    public String getFirstProjectNameBeforeFilter() {
        return driver.findElement(firstProjectNameBeforeFilter).getText();
    }

    public String getFirstProjectNameAfterFilter() {
        return driver.findElement(firstProjectNameAfterFilter).getText();
    }

    public void clickOnKebabDropDownMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(kebabDropDownMenu)).click();
    }

    public void clickOnDeleteOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteOption)).click();
    }

    public void clickOnDeleteProject() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteProject)).click();
    }
    
    public void deleteProject() {
        clickOnKebabDropDownMenu();
        clickOnDeleteOption();
        clickOnDeleteProject();
    }

    public List<String> getNamesOfProjects() {
        List<String> projectNames = new ArrayList<>();
        WebElement table = driver.findElement(By.tagName("tbody"));
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            projectNames.add(cells.get(0).getText());
        }
        return projectNames;
    }

    public void deleteAllProjects() {
        do {
            deleteProject();
        } while (getFirstProjectNameBeforeFilter() != null);

    }

    public String getHeader2() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header2)).getText();
    }
}
