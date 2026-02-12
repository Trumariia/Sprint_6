package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage {
    private final WebDriver driver;
    private final By upperButton = By.className("Button_Button__ra12g");
    private final By lowerButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final static String ACCORDION_PANEL = "accordion__panel-";
    private final static String ACCORDION_HEADING = "accordion__heading-";
    private final By acceptCookie = By.id("rcc-confirm-button");
    private final static String SITE = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        // открыть сайт
        driver.get(SITE);
    }

    //найти верхнюю кнопку Заказать
    public void clickUpperButton() {
        driver.findElement(upperButton).click();
    }

    //скролл до нижней кнопки Заказать
    public void clickLowerButton() {
        WebElement button = driver.findElement(lowerButton);
        DriverUtils.scrollTo(button, driver);
        button.click();
    }

    //принять куки
    public void acceptCookie() {
        driver.findElement(acceptCookie).click();
    }

    // скролл до вопросов, нажать на вопрос
    public void clickToQuestions(String suffix) {
        WebElement questionElement = driver.findElement(By.id(ACCORDION_HEADING + suffix));
        DriverUtils.scrollTo(questionElement, driver);
        questionElement.click();
    }

    //найти ответ
    public void checkAnswer(String suffix, String answer) {
        WebElement answerElement = driver.findElement(By.id(ACCORDION_PANEL + suffix));
        assertEquals(answer, answerElement.getText(), "Ошибка при сравнении текста");
    }
}












