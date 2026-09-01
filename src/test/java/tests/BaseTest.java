package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class BaseTest {

    protected WebDriver driver;

    protected LoginPage loginPage;

    @BeforeEach
    void setUp() {

        // Abrir Chrome
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Abrir SauceDemo
        driver.get("https://www.saucedemo.com/");

        // Crear Page Object
        loginPage = new LoginPage(driver);

        // PRECONDICIÓN
        loginPage.login(
                "standard_user",
                "secret_sauce"
        );
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}