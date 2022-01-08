package br.com.Rsilva;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

public class EnvioDadosTest {


    @Test
    public void EnvioDadosViaQuery_01() {
        RestAssured.given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/v2/users?format=xml")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                ;

    }

    @Test
    public void EnvioDadosViaQuery_02() {
        RestAssured.given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/v2/users?format=json")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
        ;

    }

    @Test
    public void EnvioDadosViaQueryViaParametro() {
        RestAssured.given()
                .log().all()
                .queryParam("format","xml")
                .when()
                .get("https://restapi.wcaquino.me/v2/users")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                .contentType(Matchers.containsString("utf-8"))
        ;

    }
    @Test
    public void EnvioDadosViaHeader_01() {
        RestAssured.given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/v2/users")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.HTML)
        ;

    }
    @Test
    public void EnvioValorViaHeader_01() {
        RestAssured.given()
                .log().all()
                .accept(ContentType.JSON)// Para esa requisição só deve aceitar valor do tipo json
                .when()
                .get("https://restapi.wcaquino.me/v2/users")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
        ;

    }




    }


