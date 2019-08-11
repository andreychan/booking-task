package com.bookingtask.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class PropertyListPage extends BasePage {

    @FindBy(xpath = "//a[@data-category='price']")
    private WebElement lowPriceSortButton;

    /* this complex xpath created for 2 reasons:
    * 1.) to exclude results where price is divided in 2 parts and so not sorted along with all results
    * ("//div[count(div[contains(@class, 'roomrow_flex entire_row_clickable')]) = 1]" serves for this)
    * 2.) to exclude properties marked with "Appartments" etc. which often are not sorted by price for some reason
    * (first 2 rows of xpath added for this) */
    @FindBy(xpath = "//a[@class='hotel_name_link url'][count(div[@class='bui-u-inline']) = 0][count(span[@class=' sr-hotel__type ']) = 0]" +
            "/ancestor::div[contains(@class,'sr_item_new sr_item_default sr_property_block')]" +
            "//div[count(div[contains(@class, 'roomrow_flex entire_row_clickable')]) = 1]//div[@class='bui-price-display__value prco-inline-block-maker-helper']")
    private List<WebElement> sortablePrices;

    public PropertyListPage(WebDriver driver) {
        super(driver);
    }

    public PropertyListPage sortByLowPrice() {
        lowPriceSortButton.click();
        new WebDriverWait(driver, 5).until(
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[@class=' sort_category  selected  sort_price ']"))));
        return this;
    }

    public List<Integer> getPrices() {
        List<Integer> prices = new LinkedList<>();
        for (WebElement actualPrice : sortablePrices) {
            prices.add(Integer.parseInt(actualPrice.getAttribute("innerText").replaceAll("[^\\d]+", "")));
        }
        return prices;
    }

}