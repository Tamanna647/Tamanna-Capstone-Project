package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dirPath = System.getProperty("user.dir") + "/screenshots/";
            File dir = new File(dirPath);

            // Create screenshots directory if it doesn't exist
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String path = dirPath + fileName + ".png";
            File dest = new File(path);
            Files.copy(src.toPath(), dest.toPath());
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
