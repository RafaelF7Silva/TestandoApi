package br.com.Rsilva;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AuthTest {


    @Test
    public void DeveAcessarSwapi() {

        RestAssured.given()
                .log().all()
                .when()
                .get("https://swapi.dev/api/people/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", Matchers.is("Luke Skywalker"));
    }

    @Test
    public void deveObterClima() {

        //https://api.openweathermap.org/data/2.5/weather?q=Brasilia&appid=9f467a8ebc4be28d070fc7e6cd36fefd&units=metric
        RestAssured.given()
                .log().all()
                .queryParam("q", "Brasilia")
                .queryParam("appid", "9f467a8ebc4be28d070fc7e6cd36fefd")
                .queryParam("units", "metric")
                .when()
                .get("https://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", Matchers.is("Brasília"))
                .body("coord.lon", Matchers.is(-47.9297f))
                .body("main.temp", Matchers.greaterThan(24f))
        ;

    }

    @Test
    public void NaoDeveAcessarSemSenha() {

        RestAssured.given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/basicauth")
                .then()
                .log().all()
                .statusCode(401);

    }

    @Test
    public void AcessarComSenha() {

        RestAssured.given()
                .log().all()
                .when()
                .get("https://admin:senha@restapi.wcaquino.me/basicauth")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", Matchers.is("logado"))
        ;
    }

    @Test
    public void AcessarComSenha_02() {

        RestAssured.given()
                .log().all()
                .auth().basic("admin", "senha")
                .when()
                .get("https://restapi.wcaquino.me/basicauth")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", Matchers.is("logado"));
    }

}