package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import base.BaseTest;
import utils.ExcelUtil;
import utils.ScreenshotUtil;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.openqa.selenium.By;

@Listeners(listeners.ExtentTestNGListener.class)
public class OrangeHRM_LoginTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void loginLogoutTest(String username, String password) {
        try {
            Thread.sleep(2000);
            driver.findElement(By.name("username")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(3000);

            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, username);

            if (username.equals("Admin") && password.equals("admin123")) {
                boolean isDashboard = driver.getCurrentUrl().contains("/dashboard");
                Assert.assertTrue(isDashboard, "Login should succeed");

                System.out.println("✅ Valid login passed");
                
                driver.findElement(By.cssSelector(".oxd-userdropdown-tab")).click();
                driver.findElement(By.xpath("//a[text()='Logout']")).click();
            } else {
                boolean isError = driver.getPageSource().contains("Invalid credentials");
                Assert.assertTrue(isError, "Login should fail for invalid credentials");
                System.out.println("❌ Invalid login correctly failed");
            }
        } catch (Exception e) {
            AssertJUnit.fail("Exception: " + e.getMessage());
        }
    }

    @DataProvider
    public Object[][] loginData() {
        return ExcelUtil.readLoginData(System.getProperty("user.dir") + "/src/test/resources/loginData.xlsx", "Sheet1");
    }
}
