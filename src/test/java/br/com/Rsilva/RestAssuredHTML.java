package br.com.Rsilva;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

public class RestAssuredHTML {

    @Test
    public void BuscarComHTML(){

        RestAssured.given()
                .log().all()

                .when()
                .get("https://restapi.wcaquino.me/v2/users")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.HTML)
                .body("html.body.div.table.tbody.tr.size()", Matchers.is(3))
                         // Navegando pelo HTML até chegar nas linhas "tr" e verificando o tamanho do id //
                .body("html.body.div.table.tbody.tr[1].td[2]", Matchers.is("25"))
                ;


    }
}
