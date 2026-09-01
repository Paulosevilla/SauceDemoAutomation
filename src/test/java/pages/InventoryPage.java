package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    // SELECTORS
    private By addBackpackButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private By removeBackpackButton =
            By.id("remove-sauce-labs-backpack");

    private By cartBadge =
            By.className("shopping_cart_badge");

    private By cartButton =
            By.className("shopping_cart_link");

    private By sortDropdown =
            By.className("product_sort_container");

    private By productPrices =
            By.className("inventory_item_price");

    // CONSTRUCTOR
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // ACCIONES

    public void agregarBackpack() {
        driver.findElement(addBackpackButton).click();
    }

    public void eliminarBackpack() {
        driver.findElement(removeBackpackButton).click();
    }

    public String obtenerCantidadCarrito() {
        return driver.findElement(cartBadge).getText();
    }

    public boolean existeContadorCarrito() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public void abrirCarrito() {
        driver.findElement(cartButton).click();
    }

    public void ordenarPorPrecioMenorAMayor() {
        Select select =
                new Select(driver.findElement(sortDropdown));

        select.selectByValue("lohi");
    }

    public List<Double> obtenerPrecios() {

        List<WebElement> elementos =
                driver.findElements(productPrices);

        List<Double> precios = new ArrayList<>();

        for (WebElement elemento : elementos) {

            String texto =
                    elemento.getText().replace("$", "");

            precios.add(Double.parseDouble(texto));
        }

        return precios;
    }
}