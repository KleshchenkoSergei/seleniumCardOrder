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
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
        String text = driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText();
        assertEquals("Fail_Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
