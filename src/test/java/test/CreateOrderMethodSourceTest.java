package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObject.DriverUtils;
import ru.yandex.praktikum.pageObject.MainPage;
import ru.yandex.praktikum.pageObject.OrderDataForm;
import ru.yandex.praktikum.pageObject.PersonDataForm;

import java.util.stream.Stream;

public class CreateOrderMethodSourceTest {
    WebDriver driver;

    static Stream<String> generateTestData() {
        String[] formData = {
"Джек,   Капитан,    Москва, Воробьёвы горы,grey,  78216452124, 05.02.2026, двое суток,  Работай с душой!",
"Юлия,   Соколова,   Москва, Черкизовская,  black, 79216452252, 06.02.2026, трое суток,  Работай с душой!",
"Егор,   Смирнов,    Москва, Фрунзенская,   grey,  79536452121, 05.02.2026, пятеро суток,Тише едешь - дальше будешь!",
"Марина, Василькова, Москва, Университет,   black, 79536455589, 06.02.2026, сутки,       Тише едешь - дальше будешь!"
        };

        return Stream.of(
                "chrome,  upButton,   " + formData[0],
                "chrome,  upButton,   " + formData[1],
                "chrome,  downButton, " + formData[2],
                "chrome,  downButton, " + formData[3],
                "firefox, upButton,   " + formData[0],
                "firefox, upButton,   " + formData[1],
                "firefox, downButton, " + formData[2],
                "firefox, downButton, " + formData[3]
        );
    }

    @ParameterizedTest
    @MethodSource("generateTestData")
    public void InputParameters(String data) throws InterruptedException {
        String[] datas = data.split(",");
        String driverType = datas[0].trim();
        String buttonType = datas[1].trim();
        String name = datas[2].trim();
        String surname = datas[3].trim();
        String city = datas[4].trim();
        String metroSelect = datas[5].trim();
        String color = datas[6].trim();
        String telephone = datas[7].trim();
        String date = datas[8].trim(), term = datas[9].trim(), comment = datas[10].trim();

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
