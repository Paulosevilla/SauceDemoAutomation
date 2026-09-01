package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestfulBookerTests {

    @BeforeAll
    static void configurarAPI() {

        RestAssured.baseURI =
                "https://restful-booker.herokuapp.com";
    }


    @Test
    void obtenerListaDeReservas() {

        given()

                .when()
                .get("/booking")

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()));
    }


    @Test
    void crearReservaCorrectamente() {

        String body = """
                {
                    "firstname": "Paulo",
                    "lastname": "Sevilla",
                    "totalprice": 250,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2026-09-10",
                        "checkout": "2026-09-15"
                    },
                    "additionalneeds": "Breakfast"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(body)

                .when()
                .post("/booking")

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("Paulo"))
                .body("booking.lastname", equalTo("Sevilla"))
                .body("booking.totalprice", equalTo(250))
                .body("booking.depositpaid", equalTo(true));
    }

    @Test
    void obtenerReservaCreadaPorId() {

        String body = """
                {
                    "firstname": "Ana",
                    "lastname": "Lopez",
                    "totalprice": 300,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2026-10-01",
                        "checkout": "2026-10-05"
                    },
                    "additionalneeds": "Lunch"
                }
                """;

        Response respuestaCreacion =

                given()
                        .contentType(ContentType.JSON)
                        .body(body)

                        .when()
                        .post("/booking")

                        .then()
                        .statusCode(200)
                        .extract()
                        .response();


        int bookingId =
                respuestaCreacion.path("bookingid");


        given()

                .when()
                .get("/booking/" + bookingId)

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Ana"))
                .body("lastname", equalTo("Lopez"))
                .body("totalprice", equalTo(300))
                .body("depositpaid", equalTo(false))
                .body("additionalneeds", equalTo("Lunch"));
    }


    @Test
    void obtenerReservaInexistente() {

        given()

                .when()
                .get("/booking/999999999")

                .then()
                .statusCode(404);
    }


    @Test
    void buscarReservaPorNombreYApellido() {

        String nombre =
                "Prueba" + System.currentTimeMillis();

        String apellido =
                "Automation";

        String body = """
                {
                    "firstname": "%s",
                    "lastname": "%s",
                    "totalprice": 180,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2026-11-01",
                        "checkout": "2026-11-04"
                    },
                    "additionalneeds": "Dinner"
                }
                """.formatted(nombre, apellido);


        int bookingId =

                given()
                        .contentType(ContentType.JSON)
                        .body(body)

                        .when()
                        .post("/booking")

                        .then()
                        .statusCode(200)
                        .extract()
                        .path("bookingid");


        given()
                .queryParam("firstname", nombre)
                .queryParam("lastname", apellido)

                .when()
                .get("/booking")

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("bookingid", hasItem(bookingId));
    }
}