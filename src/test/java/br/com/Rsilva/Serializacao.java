package br.com.Rsilva;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Serializacao {

    @Test
    public void SalvarUsuario_Map() {
        Map<String, Object> params = new HashMap<String,Object>();

        params.put("name" ,"Usuario Via Map");
        params.put("age", 25);


        RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body(params)
                .when()
                .post(" https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.is(Matchers.notNullValue()))
                .body("name", Matchers.is("Usuario Via Map"))
                .body("age", Matchers.is(25))
        ;
    }

    @Test
    public void SalvarUsuario_Objeto() {

        User user = new User("Usuario via Objeto",35);
        Map<String, Object> params = new HashMap<String,Object>();


        RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body(user)
                .when()
                .post(" https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.is(Matchers.notNullValue()))
                .body("name", Matchers.is("Usuario via Objeto"))
                .body("age", Matchers.is(35))
        ;
    }

}
