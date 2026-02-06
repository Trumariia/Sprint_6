package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverUtils {
    public static void scrollTo(WebElement element, WebDriver withDriver) {
        ((JavascriptExecutor)withDriver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
