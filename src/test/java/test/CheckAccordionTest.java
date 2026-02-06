package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pageObject.DriverUtils;
import ru.yandex.praktikum.pageObject.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckAccordionTest {
    WebDriver driver = new ChromeDriver();

    @ParameterizedTest
    @CsvSource({
            "0, 'Сутки — 400 рублей. Оплата курьеру — наличными или картой.'",
            "1, 'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.'",
            "2, 'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.'",
            "3, 'Только начиная с завтрашнего дня. Но скоро станем расторопнее.'",
            "4, 'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.'",
            "5, 'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.'",
            "6, 'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.'",
            "7, 'Да, обязательно. Всем самокатов! И Москве, и Московской области.'"
    })
    public void Check_question(String questionNumber, String answer) throws InterruptedException {

        MainPage mainPage = new MainPage(driver);

        //добавить ожидание драйвера
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.getAccordionHeadingBy(questionNumber)));

        //скролл до вопроса  Найти вопрос
        WebElement questionElement = mainPage.getAccordionHeading(questionNumber);
        DriverUtils.scrollTo(questionElement, driver);

        // клик на него
        questionElement.click();

        //задержка
        Thread.sleep(500);

        // найти ответ
        WebElement answerElement = mainPage.getAccordionPanel(questionNumber);

        // сравнить со значением Текста
        assertEquals(answer, answerElement.getText(), "Ошибка при сравнении текста");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
