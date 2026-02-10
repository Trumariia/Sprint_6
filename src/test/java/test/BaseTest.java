package test;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    WebDriver driver;

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}


