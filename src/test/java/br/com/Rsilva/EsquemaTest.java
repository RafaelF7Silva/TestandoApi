package br.com.Rsilva;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import org.junit.Test;
import org.xml.sax.SAXParseException;


public class EsquemaTest {

    @Test
    public void ValidarEsquemaXSD(){
        RestAssured.given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/usersXML")
                .then()
                .log().all()
                .statusCode(200)
                .body(RestAssuredMatchers.matchesXsdInClasspath("user.xsd"))


        ;

    }
    @Test (expected = SAXParseException.class)
    public void NaoValidarEsquemaXSD(){
        RestAssured.given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/invalidusersXML")
                .then()
                .log().all()
                .statusCode(200)
                .body(RestAssuredMatchers.matchesXsdInClasspath("user.xsd"))


        ;

    }

}
