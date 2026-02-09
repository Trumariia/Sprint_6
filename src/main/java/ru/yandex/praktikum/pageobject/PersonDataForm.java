package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonDataForm {
    private final WebDriver driver;
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.className("select-search__input");
    private final static String metroSelect = "//li/button[div[text() = '%s']]";
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.className("Button_Middle__1CSJM");

    public PersonDataForm(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getName() {
        return driver.findElement(name);
    }

    public WebElement getSurname() {
        return driver.findElement(surname);
    }

    public WebElement getAddress() {
        return driver.findElement(address);
    }

    public WebElement getMetroStation() {
        return driver.findElement(metroStation);
    }

    public WebElement getMetroSelect(String metroSelect) {
        return driver.findElement(By.xpath(String.format(this.metroSelect, metroSelect)));
    }

    public WebElement getTelephone() {
        return driver.findElement(telephone);
    }

    public WebElement getButtonNext() {
        return driver.findElement(buttonNext);
    }
}

