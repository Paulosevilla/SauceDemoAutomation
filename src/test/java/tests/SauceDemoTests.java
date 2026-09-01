package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.InventoryPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SauceDemoTests extends BaseTest {
    @Test
    void agregarProductoAlCarrito() {

        InventoryPage inventoryPage =
                new InventoryPage(driver);

        inventoryPage.agregarBackpack();

        assertEquals(
                "1",
                inventoryPage.obtenerCantidadCarrito()
        );
    }

    @Test
    void eliminarProductoDelCarrito() {

        InventoryPage inventoryPage =
                new InventoryPage(driver);

        inventoryPage.agregarBackpack();

        inventoryPage.abrirCarrito();

        CartPage cartPage =
                new CartPage(driver);

        assertTrue(
                cartPage.backpackEstaEnCarrito()
        );

        cartPage.eliminarBackpack();

        assertFalse(
                cartPage.backpackEstaEnCarrito()
        );
    }

    @Test
    void ordenarProductosPorPrecio() {

        InventoryPage inventoryPage =
                new InventoryPage(driver);

        inventoryPage.ordenarPorPrecioMenorAMayor();

        List<Double> preciosObtenidos =
                inventoryPage.obtenerPrecios();

        List<Double> preciosEsperados =
                new ArrayList<>(preciosObtenidos);

        Collections.sort(preciosEsperados);

        assertEquals(
                preciosEsperados,
                preciosObtenidos
        );
    }



    @Test
    void realizarCompraCorrectamente() {

        InventoryPage inventoryPage =
                new InventoryPage(driver);

        inventoryPage.agregarBackpack();

        inventoryPage.abrirCarrito();


        CartPage cartPage =
                new CartPage(driver);

        cartPage.irCheckout();


        CheckoutPage checkoutPage =
                new CheckoutPage(driver);

        checkoutPage.completarDatos(
                "Paulo",
                "Sevilla",
                "0000"
        );


        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        overviewPage.finalizarCompra();


        assertEquals(
                "Thank you for your order!",
                overviewPage.obtenerMensajeCompra()
        );
    }



    @Test
    void validarCheckoutSinNombre() {

        InventoryPage inventoryPage =
                new InventoryPage(driver);

        inventoryPage.agregarBackpack();

        inventoryPage.abrirCarrito();


        CartPage cartPage =
                new CartPage(driver);

        cartPage.irCheckout();


        CheckoutPage checkoutPage =
                new CheckoutPage(driver);

        checkoutPage.presionarContinue();


        assertEquals(
                "Error: First Name is required",
                checkoutPage.obtenerMensajeError()
        );
    }
}