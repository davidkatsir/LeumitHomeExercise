package w3schools;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("w3schools HTML Tables Testing")
public class HtmlTablesTest {
    private WebDriver driver;
    private HtmlTablesPage htmlTablesPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        htmlTablesPage = new HtmlTablesPage(driver);
        driver.get("http://www.w3schools.com/html/html_tables.asp");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Test getting company name from contact name")
    @Story("Get company name from contact name")
    public void testGetCompanyNameFromContactName() {
        String contactName = "Maria Anders"; // Example contact name
        WebElement table = driver.findElement(By.id("customers"));
        boolean isExpected = htmlTablesPage.verifyTableCellText(table, 1, contactName, 0, "Alfreds Futterkiste");
        Assert.assertTrue(isExpected, "Company name does not match");
    }

    @Test
    @Description("Test getting country name from company name")
    @Story("Get country name from company name")
    public void testGetCountryNameFromCompanyName() {
        String companyName = "Alfreds Futterkiste"; // Example company name
        String countryName = htmlTablesPage.getTableCellText(driver.findElement(By.id("customers")), 0, companyName, 2);
        Assert.assertNotNull(countryName, "Failed to find country name");
    }

    @Test
    @Description("Test getting contact name from country name")
    @Story("Get contact name from country name")
    public void testGetContactNameFromCountryName() {
        String countryName = "Germany"; // Example country name
        String contactName = htmlTablesPage.getTableCellText(driver.findElement(By.id("customers")), 2, countryName, 1);
        Assert.assertNotNull(contactName, "Failed to find contact name");
    }

    @Test
    @Description("Test getting contact name from country name - By XPath")
    @Story("Get contact name from country name using XPath")
    public void testGetContactNameFromCountryNameByXpath() throws Exception {
        WebElement table = driver.findElement(By.id("customers"));
        String contactName = htmlTablesPage.getTableCellTextByXPath(table, 2, "Francisco Chang", 2);
        Assert.assertNotNull(contactName, "Failed to find contact name");
    }
}
