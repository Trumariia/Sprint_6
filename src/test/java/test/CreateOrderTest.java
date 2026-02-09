package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageobject.OrderDataForm;
import ru.yandex.praktikum.pageobject.DriverUtils;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.PersonDataForm;

public class CreateOrderTest {
    WebDriver driver;

    @ParameterizedTest
    @CsvSource({
"chrome,  upButton,   Джек,   Капитан,    Москва, Воробьёвы горы,grey,  78216452124, 05.02.2026, двое суток,  Работай с душой!",
"chrome,  upButton,   Юлия,   Соколова,   Москва, Черкизовская,  black, 79216452252, 06.02.2026, трое суток,  Работай с душой!",
"chrome,  downButton, Егор,   Смирнов,    Москва, Фрунзенская,   grey,  79536452121, 05.02.2026, пятеро суток,Тише едешь - дальше будешь!",
"chrome,  downButton, Марина, Василькова, Москва, Университет,   black, 79536455589, 06.02.2026, сутки,       Тише едешь - дальше будешь!",
"firefox, upButton,   Джек,   Капитан,    Москва, Воробьёвы горы,grey,  78216452124, 05.02.2026, двое суток,  Работай с душой!",
"firefox, upButton,   Юлия,   Соколова,   Москва, Черкизовская,  black, 79216452252, 06.02.2026, трое суток,  Работай с душой!",
"firefox, downButton, Егор,   Смирнов,    Москва, Фрунзенская,   grey,  79536452121, 05.02.2026, пятеро суток,Тише едешь - дальше будешь!",
"firefox, downButton, Марина, Василькова, Москва, Университет,   black, 79536455589, 06.02.2026, сутки,       Тише едешь - дальше будешь!"
    })
    public void inputParameters(String driverType, String buttonType,
                                String name, String surname, String city,
                                String metroSelect, String color, String telephone,
                                String date, String term, String comment) throws InterruptedException {
        //запустить браузер
        if (driverType.equals("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        MainPage mainPage = new MainPage(driver);

        //найти кнопку Заказать
        WebElement buttonOrder;
        if (buttonType.equals("upButton")) {
            buttonOrder = mainPage.getUpButton();
        } else {
            buttonOrder = mainPage.getDownBotton();
            DriverUtils.scrollTo(buttonOrder, driver);
        }

        Thread.sleep(300);

        mainPage.getAcceptCookie().click();

        //нажать кнопку Заказать
        buttonOrder.click();

        Thread.sleep(1000);

        PersonDataForm personForm = new PersonDataForm(driver);

        //найти поле Имя, ввести значение
        personForm.getName().sendKeys(name);

        //найти поле Фамилия, ввести значение
        personForm.getSurname().sendKeys(surname);

        //найти поле Адрес, ввести значение
        personForm.getAddress().sendKeys(city);

        //найти поле Станция метро, нажать
        personForm.getMetroStation().click();

        //выбрать станцию метро
        WebElement metroSelectElement = personForm.getMetroSelect(metroSelect);
        DriverUtils.scrollTo(metroSelectElement, driver);
        metroSelectElement.click();

        //найти поле Телефон, ввести телефон
        personForm.getTelephone().sendKeys(telephone);

        Thread.sleep(1000);
        //найти кнопку Далее, нажать
        personForm.getButtonNext().click();


        OrderDataForm form = new OrderDataForm(driver);
        //найти поле Когда привезти самокат? ввести значение
        form.getDate().sendKeys(date);

        //найти поле Срок аренды, нажать
        form.getPeriodRent().click();

        //выбрать значение в выпадающем списке
        WebElement termElement = form.getTermElement(term);
        DriverUtils.scrollTo(termElement, driver);
        termElement.click();

        //найти поле Цвет, выбрать цвет
        form.getColor(color).click();

        //найти поле Коммент для курьера, ввести текст
        form.getComment().sendKeys(comment);
        Thread.sleep(1000);
        //найти кнопку Заказать, нажать
        form.getCreateButton().click();

        //подтвердить заказ
        form.getButtonYes().click();

        //проверка подтверждения
        WebElement successEl = form.getSuccessOrderWindow();
        Assertions.assertTrue(successEl.getText().contains("Заказ оформлен"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
