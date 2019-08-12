package com.bookingtask.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

import static com.bookingtask.util.Constants.PATH_TO_GECKO_DRIVER;

public class TestBase {

    static WebDriver driver;

    //TODO add other browsers support
    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", PATH_TO_GECKO_DRIVER);
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--private");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en");
        options.setProfile(profile);
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

}