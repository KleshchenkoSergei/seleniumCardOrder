package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.*;

public class CardOrderTest {

    private WebDriver driver;

    @BeforeAll
    static void setUp() {

        WebDriverManager.chromedriver().setup(); // использование webdriver manager
//        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe"); //ручной выбор драйвера
    }

    @BeforeEach
    void setUp2() {

        // заупск драйвера в режиме headless
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        //запуск драйвера в графическом режиме
//        driver = new ChromeDriver();
    }

    @AfterEach
    public void close() {
        driver.quit();
        driver = null;
    }

    @Test
    public void test() {
        driver.get("http://localhost:9999");

        //CSS selectors
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Андрей");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79239236655");

        //Special CSS selectors  [data-test-id ="name"]
//        driver.findElement(By.cssSelector("[data-test-id=\"name\"]")).sendKeys("Андрей");
//        driver.findElement(By.cssSelector("[data-test-id=\"phone\"]")).sendKeys("+79239236655");

        //No CSS selectors
//        List<WebElement> elements = driver.findElements(By.className("input__control"));
//        elements.get(0).sendKeys("Андрей");
//        elements.get(1).sendKeys("+79265669999");

//        driver.findElement(By.className("input_control")).sendKeys("Андрей");
//        driver.findElement().sendKeys("+79265669999");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click(); // кнопка отправить
        //driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText(); //order-success
        String text = driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
