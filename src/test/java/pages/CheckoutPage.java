package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    // SELECTORS
    private By firstNameInput =
            By.id("first-name");

    private By lastNameInput =
            By.id("last-name");

    private By postalCodeInput =
            By.id("postal-code");

    private By continueButton =
            By.id("continue");

    private By errorMessage =
            By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ingresarNombre(String nombre) {
        driver.findElement(firstNameInput).sendKeys(nombre);
    }

    public void ingresarApellido(String apellido) {
        driver.findElement(lastNameInput).sendKeys(apellido);
    }

    public void ingresarCodigoPostal(String codigo) {
        driver.findElement(postalCodeInput).sendKeys(codigo);
    }

    public void presionarContinue() {
        driver.findElement(continueButton).click();
    }

    public void completarDatos(
            String nombre,
            String apellido,
            String codigo) {

        ingresarNombre(nombre);
        ingresarApellido(apellido);
        ingresarCodigoPostal(codigo);

        presionarContinue();
    }

    public String obtenerMensajeError() {
        return driver.findElement(errorMessage).getText();
    }
}
