package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    // SELECTORS
    private By backpackProduct =
            By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']");

    private By removeBackpackButton =
            By.id("remove-sauce-labs-backpack");

    private By checkoutButton =
            By.id("checkout");

    // CONSTRUCTOR
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean backpackEstaEnCarrito() {
        return !driver.findElements(backpackProduct).isEmpty();
    }

    public void eliminarBackpack() {
        driver.findElement(removeBackpackButton).click();
    }

    public void irCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
