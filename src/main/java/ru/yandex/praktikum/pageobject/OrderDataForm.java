package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderDataForm {
    private final WebDriver driver;
    private final By date = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By periodRent = By.xpath("//span[@class='Dropdown-arrow']");
    private static final String TERM_ELEMENT = "//div[@class='Dropdown-option' and text()='%s']";
    private final By colourBlack = By.id("black");
    private final By colourGrey = By.id("grey");
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By createButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By buttonYes = By.xpath("//button[text()='Да']");
    private final By successOrderWindow = By.xpath(".//div[text()='Заказ оформлен']");

    public OrderDataForm(WebDriver driver) {
        this.driver = driver;
    }

    //найти поле Дата, ввести дату
    public void inputDate(String newDate) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(date));
        driver.findElement(date).sendKeys(newDate);

    }

    //найти поле Период, нажать
    public void inputPeriodRent() {
        driver.findElement(periodRent).click();
    }

    //скролл, выбрать
    public void scrollToTermElement(String newTermElement) {
        WebElement termElement = driver.findElement(By.xpath(String.format(TERM_ELEMENT, newTermElement)));
        DriverUtils.scrollTo(termElement, driver);
        termElement.click();
    }

    public void selectColor(String colour) {
        if (colour.equals("black")) {
            driver.findElement(colourBlack).click();
        } else if (colour.equals("grey")) {
            driver.findElement(colourGrey).click();
        }
    }

    //ввести комментарий
    public void inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
    }

    //нажать кнопку Заказать
    public void clickCreateButton() {
        driver.findElement(createButton).click();
    }

    //нажать Да
    public void clickButtonYes() {
        driver.findElement(buttonYes).click();
    }

    //окно с заказом
    public void findSuccessOrderWindow() {
        driver.findElement(successOrderWindow);
    }
}





