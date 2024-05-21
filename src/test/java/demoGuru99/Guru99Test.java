package demoGuru99;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Guru99Test {
    private WebDriver driver;
    private Guru99Page guru99Page;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        guru99Page = new Guru99Page(driver);
        driver.get("http://demo.guru99.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void verifyAndRefresh(String topMenuItem, String subMenuItem) {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://demo.guru99.com/#google_vignette")) {
            driver.navigate().refresh();
            if (subMenuItem.isEmpty()) {
                guru99Page.selectMenuItem(topMenuItem);
            } else {
                guru99Page.selectMenuItem(topMenuItem, subMenuItem);
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Description("Test selecting a menu item using one string parameter")
    public void testSelectMenuItemSingleString() {
        guru99Page.selectMenuItem("SEO; Page-1");
        verifyAndRefresh("SEO", "Page-1");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/seo/page-1.html");
    }

    @Test
    @Description("Test selecting a menu item using two string parameters")
    public void testSelectMenuItemTwoStrings() {
        guru99Page.selectMenuItem("SEO", "Page-1");
        verifyAndRefresh("SEO", "Page-1");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/seo/page-1.html");
    }
}
