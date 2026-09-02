package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestfulBookerTests {


    @BeforeAll
    static void configurarAPI(){

        RestAssured.baseURI =
                "https://restful-booker.herokuapp.com";
    }


    // API-01: Obtener lista de reservas
    @Test
    public void obtenerListaReservas(){

        given()

                .when()
                .get("/booking")

                .then()
                .statusCode(200)
                .body("$", not(empty()));

    }



    // API-02: Obtener una reserva por ID
    @Test
    public void obtenerReservaPorID(){

        given()
                .pathParam("id", "1")

                .when()
                .get("/booking/{id}")

                .then()
                .statusCode(200)
                .body("firstname", notNullValue())
                .body("lastname", notNullValue());

    }



    // API-03: Crear una reserva usando POST
    @Test
    public void crearReserva(){

        String body = """
                {
                    "firstname": "Paulo",
                    "lastname": "Sevilla",
                    "totalprice": 500,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2026-09-01",
                        "checkout": "2026-09-05"
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
                .body("booking.firstname",
                        equalTo("Paulo"))
                .body("booking.lastname",
                        equalTo("Sevilla"));

    }



    // API-04: Validar una reserva inexistente
    @Test
    public void reservaNoExiste(){

        given()
                .pathParam("id", "999999")

                .when()
                .get("/booking/{id}")

                .then()
                .statusCode(404);

    }



    // API-05: Buscar reservas por nombre
    @Test
    public void buscarReservaPorNombre(){

        given()
                .queryParam("firstname", "Mark")

                .when()
                .get("/booking")

                .then()
                .statusCode(200)
                .body("$", notNullValue());

    }

}
