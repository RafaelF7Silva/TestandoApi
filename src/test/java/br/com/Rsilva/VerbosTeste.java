package br.com.Rsilva;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class VerbosTeste {


    @Test
    public void SalvarUsuario() {
        RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body("{\"name\": \"Jose\",\"age\": 50}")
                .when()
                .post(" https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.is(Matchers.notNullValue()))
                .body("name", Matchers.is("Jose"))
                .body("age", Matchers.is(50))
        ;
    }

    @Test
    public void NaoSalvarUsuarioSemNome() {
        RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body("{\"age\": 50}")
                .when()
                .post(" https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(400)
                .body("id", Matchers.is(Matchers.nullValue()))
                .body("error", Matchers.is("Name é um atributo obrigatório"))
        ;
    }

    @Test
    public void SalvarUsuario_XML() {
        RestAssured.given()
                .log().all()
                .contentType("application/xml")
                .body(" <user><name> Jose</name><age>50</age></user>")
                .when()
                .post(" https://restapi.wcaquino.me/usersXML")
                .then()
                .log().all()
                .statusCode(201)
                .body("user.@id", Matchers.is(Matchers.notNullValue()))
                .body("user.name", Matchers.is("Jose"))
                .body("user.age", Matchers.is("50"))
        ;
    }

    @Test
    public void AlterarUmUsuario() {
        RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body("{\"name\": \"Antonio\",\"age\": 80}")
                .when()
                .put(" https://restapi.wcaquino.me/users/1")//put- verbo de alteração
                .then()
                .log().all()
                .statusCode(200)
                .body("id", Matchers.is(1))
                .body("name", Matchers.is("Antonio"))
                .body("age", Matchers.is(80))
                .body("salary", Matchers.is(1234.5678f))
        ;
    }

    @Test
    public void CustomizarUrl() {
        RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body("{\"name\": \"Antonio\",\"age\": 80}")
                .when()
                .put(" https://restapi.wcaquino.me/{entidades}/{usersId}", "users", "1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", Matchers.is(1))
                .body("name", Matchers.is("Antonio"))
                .body("age", Matchers.is(80))
                .body("salary", Matchers.is(1234.5678f));


    }

    @Test
    public void CustomizarUrl_02() {
        RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .body("{\"name\": \"Antonio\",\"age\": 80}")
                .pathParam("{entidade}", "users")
                .pathParam("userId", 1)
                .when()
                .put(" https://restapi.wcaquino.me/{entidade}/{usersId}", "users", "1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", Matchers.is(1))
                .body("name", Matchers.is("Antonio"))
                .body("age", Matchers.is(80))
                .body("salary", Matchers.is(1234.5678f));
    }

    @Test
    public void RemoverUsuario() {

        RestAssured
                .given()
                .log().all()
                .when()
                .delete(" https://restapi.wcaquino.me/users/1")
                .then()
                .log().all()
                .statusCode(204)
        ;
    }

    @Test
    public void NaoRemoverUsuario() {

        RestAssured
                .given()
                .log().all()
                .when()
                .delete(" https://restapi.wcaquino.me/users/1000")
                .then()
                .log().all()
                .statusCode(400)
                .body("error",Matchers.is("Registro inexistente"))

                ;
    }
}




