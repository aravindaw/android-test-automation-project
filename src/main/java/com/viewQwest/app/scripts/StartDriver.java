package com.viewQwest.app.scripts;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class StartDriver {
    public static AndroidDriver driver;

    @Step("Before Suite: Setup the environment variables and start the app")
    @BeforeSuite
    public void setUp() throws MalformedURLException, ConfigurationException {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new PropertiesConfiguration("src/main/resources/config.properties"));

//        File app=new File(config.getProperty("APK_PATH").toString().trim());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getProperty("DEVICE_NAME").toString().trim());
        capabilities.setCapability("app",config.getProperty("APK_PATH").toString().trim());
        capabilities.setCapability("appActivity", config.getProperty("APP_ACTIVITY").toString().trim());
        capabilities.setCapability("appPackage", config.getProperty("APP_PACKAGE").toString().trim());
        capabilities.setCapability("adv", config.getProperty("AVD").toString().trim());
        driver = new AndroidDriver(new URL("http://"+config.getProperty("APPIUM_SERVER")+":4723/wd/hub"), capabilities);

    }

    @Step("After Suite: Close the driver and app")
    @AfterSuite
    public void teardown() {
        //close the app
        driver.quit();
    }
}
