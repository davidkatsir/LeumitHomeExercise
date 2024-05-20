package demoGuru99;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Guru99Page {
    private WebDriver driver;

    public Guru99Page(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Select menu item with single string: {menuItem}")
    public void selectMenuItem(String menuItem) {
        String[] menuItems = menuItem.split(";");
        String topMenuItem = menuItems[0].trim();
        String subMenuItem = menuItems.length > 1 ? menuItems[1].trim() : "";
        selectMenuItem(topMenuItem, subMenuItem);
    }

    @Step("Select menu item with top menu: {topMenuItem} and sub menu: {subMenuItem}")
    public void selectMenuItem(String topMenuItem, String subMenuItem) {
        WebElement topMenuElement = driver.findElement(By.linkText(topMenuItem));
        topMenuElement.click();
        if (!subMenuItem.isEmpty()) {
            WebElement subMenuElement = driver.findElement(By.linkText(subMenuItem));
            subMenuElement.click();
        }
    }
}
