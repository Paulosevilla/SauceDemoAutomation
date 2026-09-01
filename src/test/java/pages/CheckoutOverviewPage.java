package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

    private WebDriver driver;

    private By finishButton =
            By.id("finish");

    private By completeHeader =
            By.className("complete-header");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void finalizarCompra() {
        driver.findElement(finishButton).click();
    }

    public String obtenerMensajeCompra() {
        return driver.findElement(completeHeader).getText();
    }
}
