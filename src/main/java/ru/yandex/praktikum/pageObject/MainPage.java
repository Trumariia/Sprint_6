package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver ;
    private final By upperButton = By.className("Button_Button__ra12g");
    private final By lowerButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final static String accordionPanel = "accordion__panel-";
    private final String accordionHeading = "accordion__heading-";
    private final By acceptCookie = By.id("rcc-confirm-button");

    public MainPage (WebDriver driver){
        this.driver = driver;
        // открыть сайт
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public By getAccordionHeadingBy(String suffix) {
        return By.id(accordionHeading + suffix);
    }

    public WebElement getAccordionHeading(String suffix) {
        return driver.findElement(By.id(accordionHeading + suffix));
    }

    public WebElement getAccordionPanel(String suffix) {
        return driver.findElement(By.id(accordionPanel + suffix));
    }

    public WebElement getUpButton() {
        return driver.findElement(upperButton);
    }

    public WebElement getDownBotton() {
        return driver.findElement(lowerButton);
    }

    public WebElement getAcceptCookie() {
        return driver.findElement(acceptCookie);
    }

//    public void clickSignInButton() {
//        driver.findElement(yandexButton);
//    }
} 

