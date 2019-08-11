package com.bookingtask.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

}