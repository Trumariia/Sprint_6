package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderDataForm {
    private final WebDriver driver;
    private final By date = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By periodRent = By.xpath("//span[@class='Dropdown-arrow']");
    private static final String termElement = "//div[@class='Dropdown-option' and text()='%s']";
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By createButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By buttonYes = By.xpath("//button[text()='Да']");
    private final By successOrderWindow = By.xpath(".//div[text()='Заказ оформлен']");

    public OrderDataForm(WebDriver driver) {
        this.driver = driver;

    }

    public WebElement getDate() {
        return driver.findElement(date);
    }

    public WebElement getPeriodRent() {
        return driver.findElement(periodRent);
    }

    public WebElement getTermElement(String term) {
        return driver.findElement(By.xpath(String.format(termElement, term)));
    }

    public WebElement getColor(String color) {
        return driver.findElement(By.id(color));
    }

    public WebElement getComment() {
        return driver.findElement(comment);
    }

    public WebElement getCreateButton() {
        return driver.findElement(createButton);
    }

    public WebElement getButtonYes() {
        return driver.findElement(buttonYes);
    }

    public WebElement getSuccessOrderWindow() {
        return driver.findElement(successOrderWindow);
    }
}

