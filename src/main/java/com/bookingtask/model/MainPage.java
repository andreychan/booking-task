package com.bookingtask.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(id = "ss")
    private WebElement whereToGoField;

    @FindBy(xpath = "//div[@class='xp__dates-inner xp__dates__checkin']")
    private WebElement checkInZone;

    @FindBy(xpath = "//td[contains(@class, 'bui-calendar__date')]")
    private List<WebElement> calendarDates;

    @FindBy(xpath = "//button[contains(@class, 'sb-searchbox__button')]")
    private WebElement checkPricesButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage enterWhereToGo(String whereToGo) {
        whereToGoField.clear();
        whereToGoField.sendKeys(whereToGo);
        return this;
    }

    public MainPage enterDates(String from, String to) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(checkInZone));
        checkInZone.click();
        //click firstly on "to" date - temporal workaround for bug when calendar selection dates are saved from previous query
        //TODO add isPresent() check
        calendarDates.stream().filter(calendarDate -> to.equals(calendarDate.getAttribute("data-date")))
                .findAny().get().click();
        calendarDates.stream().filter(calendarDate -> from.equals(calendarDate.getAttribute("data-date")))
                .findAny().get().click();
        calendarDates.stream().filter(calendarDate -> to.equals(calendarDate.getAttribute("data-date")))
                .findAny().get().click();
        return this;
    }

    public PropertyListPage clickCheckPrices() {
        checkPricesButton.click();
        return new PropertyListPage(driver);
    }

}