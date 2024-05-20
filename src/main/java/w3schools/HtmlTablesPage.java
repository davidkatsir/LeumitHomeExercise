package w3schools;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HtmlTablesPage {
    private WebDriver driver;

    public HtmlTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));
        for (int index = 1; index < rows.size(); index++) {
            List<WebElement> cells = rows.get(index).findElements(By.cssSelector("td"));
            if (cells.get(searchColumn).getText().equals(searchText)) {
                return cells.get(returnColumnText).getText();
            }
        }
        throw new NoSuchElementException("No matching cell found.");
    }

    public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String actualText = getTableCellText(table, searchColumn, searchText, returnColumnText);
        return actualText != null && actualText.equals(expectedText);
    }

    public String getTableCellTextByXPath(WebElement table, int searchColumn, String searchText, int returnColumnText) throws Exception {
        // Construct the XPath expression to find the correct row
        String rowXpath = String.format(".//tr[td[%d] = '%s']", searchColumn, searchText);
        // Find the row matching the search criteria
        WebElement row = table.findElement(By.xpath(rowXpath));
        // Construct the XPath to find the specific cell in that row
        String cellXpath = String.format("./td[%d]", returnColumnText);
        // Find the cell within the located row
        WebElement cell = row.findElement(By.xpath(cellXpath));
        // Return the text of the found cell
        return cell.getText();
    }
}
