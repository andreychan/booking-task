package com.bookingtask.test;

import static org.testng.Assert.*;

import com.bookingtask.model.MainPage;
import com.bookingtask.model.PropertyListPage;
import com.bookingtask.util.Constants;
import com.bookingtask.util.Utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest extends TestBase{

    @Test(dataProvider = "testCities")
    public void basicActionsTest(String city, int duration) {
        driver.get(Constants.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        assertTrue(driver.getTitle().endsWith("единиц размещения по всему миру: отели и другие варианты. Забронируйте отель прямо сейчас!"));
        mainPage.enterWhereToGo(city).enterDates(Utils.getTodayDate(), Utils.getTodayDatePlusDays(duration));

        PropertyListPage propertiesPage = mainPage.clickCheckPrices();
        assertTrue(driver.getTitle().startsWith("Booking.com: Отели по направлению"));
        List<Integer> prices = propertiesPage.sortByLowPrice().getPrices();
        assertEquals(prices, prices.stream().sorted().collect(Collectors.toList()));
    }

    @DataProvider(name = "testCities")
    public Object[][] createData1() {
        return new Object[][]{
                {"Одесса", 14},
                {"Киев", 21},
                {"Нью-Йорк", 28},
                {"Будапешт", 3},
                {"Прага", 7}
        };
    }

}