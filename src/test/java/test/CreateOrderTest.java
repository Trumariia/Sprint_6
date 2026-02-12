package test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageobject.OrderDataForm;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.PersonDataForm;

public class CreateOrderTest extends BaseTest {

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
                                String date, String term, String comment) {
        //запустить браузер
        if (driverType.equals("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }

        MainPage mainPage = new MainPage(driver);

        mainPage.acceptCookie();

        if (buttonType.equals("upButton")) {
            mainPage.clickUpperButton();
        } else {
            mainPage.clickLowerButton();
        }


        PersonDataForm personForm = new PersonDataForm(driver);


        personForm.inputName(name);

        personForm.inputSurname(surname);

        personForm.inputAddress(city);

        personForm.changeMetroStation(metroSelect);

        personForm.inputTelephone(telephone);

        personForm.clickButtonNext();


        OrderDataForm form = new OrderDataForm(driver);

        form.inputDate(date);

        form.inputPeriodRent();

        form.scrollToTermElement(term);

        form.selectColor(color);

        form.inputComment(comment);

        form.clickCreateButton();

        form.clickButtonYes();

        form.findSuccessOrderWindow();

    }
}

