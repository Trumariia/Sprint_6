package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonDataForm {
    private final WebDriver driver;
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.className("select-search__input");
    private final static String METRO_SELECT = "//li/button[div[text() = '%s']]";
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.className("Button_Middle__1CSJM");

    public PersonDataForm(WebDriver driver) {
        this.driver = driver;
    }

    //найти поле Имя, ввести значение
    public void inputName(String newName) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(name));
        driver.findElement(name).sendKeys(newName);
    }

    //найти поле Фамилия, ввести значение
    public void inputSurname(String newSurname) {
        driver.findElement(surname).sendKeys(newSurname);
    }

    //найти поле Адрес, ввести значение
    public void inputAddress(String city) {
        driver.findElement(address).sendKeys(city);
    }

    //найти поле Станция метро, нажать
    public void changeMetroStation(String metroSelect) {
        driver.findElement(metroStation).click();

        WebElement metroSelectElement = driver.findElement(By.xpath(String.format(METRO_SELECT, metroSelect)));
        DriverUtils.scrollTo(metroSelectElement, driver);
        metroSelectElement.click();
    }

    //найти поле Телефон, ввести телефон
    public void inputTelephone(String newTelephone) {
        driver.findElement(telephone).sendKeys(newTelephone);
    }

    //найти кнопку Далее, нажать
    public void clickButtonNext() {
            driver.findElement(buttonNext).click();
    }
}

