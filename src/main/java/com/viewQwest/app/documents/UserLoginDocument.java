package com.viewQwest.app.documents;

import com.viewQwest.app.context.UserLoginContext;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.viewQwest.app.scripts.StartDriver.driver;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class UserLoginDocument implements Document {
    private final UserLoginContext context;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserLoginDocument.class);

    public UserLoginDocument(UserLoginContext context) {
        this.context = context;
    }

    public byte[] login() throws Exception {
        WebElement userName = driver.findElementById("email");
        userName.clear();
        userName.sendKeys(context.getUserName());

        WebElement password = driver.findElementById("password");
        password.clear();
        password.sendKeys(context.getPassword());
        WebElement loginButton = driver.findElementById("email_sign_in_button");
        loginButton.click();

        if (loginButton.isDisplayed()){
            Assert.assertTrue(loginButton.isDisplayed());
            log.info("Error message Displayed...");
            return driver.getScreenshotAs(OutputType.BYTES);
        }else {
            Assert.assertFalse(loginButton.isDisplayed());
            log.info("Login succeeded...");
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(presenceOfElementLocated(By.id("imageView2")));
            return driver.getScreenshotAs(OutputType.BYTES);
        }
    }
}
