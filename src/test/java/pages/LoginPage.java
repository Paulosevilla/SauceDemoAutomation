package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;


    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void ingresarUsuario(String usuario) {
        driver.findElement(usernameInput).sendKeys(usuario);
    }

    public void ingresarPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void presionarLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String usuario, String password) {
        ingresarUsuario(usuario);
        ingresarPassword(password);
        presionarLogin();
    }
}