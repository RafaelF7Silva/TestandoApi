package br.com.Rsilva;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class UserJsonTest {

        @Test
        public void VerificarPrimeiroNivel() {
            RestAssured.given()
                    .when()
                    .get(" https://restapi.wcaquino.me/users/1")
                    .then()
                    .statusCode(200)
                    .body("id", Matchers.is(1))
                    .body("name", Matchers.containsString("Silva"))
                    .body("age", Matchers.greaterThan(18));

        }

    @Test
    public void OutrasFormas_VerificarPrimeiroNivel() {

        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");

        //path
        response.path("id");
        Assert.assertEquals(Integer.valueOf(1), response.path("id"));

        // JsonPath

        JsonPath jpath = new JsonPath(response.asString());
        Assert.assertEquals(1, jpath.getInt("id"));

        //from

        int id = JsonPath.from(response.asString()).getInt("id");
        Assert.assertEquals("id", "id");
    }

    @Test
    public void VerificarSegundoNivel() {
        RestAssured.given()
                .when()
                .get(" https://restapi.wcaquino.me/users/2")
                .then()
                .statusCode(200)
                .body("name", Matchers.containsString("Joaquina"))
                .body("endereco.rua", Matchers.is("Rua dos bobos")); //Navegando pelos niveis do Json
    }

    @Test
    public void VerificarTerceiroNivel() {
        //Trabalhando com Lista
        RestAssured.given()
                .when()
                .get(" https://restapi.wcaquino.me/users/3")
                .then()
                .statusCode(200)
                .body("name", Matchers.containsString("Ana"))
                .body("filhos", Matchers.hasSize(2))
                .body("filhos [0].name", Matchers.is("Zezinho"))
                .body("filhos [1].name", Matchers.is("Luizinho"))
                .body("filhos.name", Matchers.hasItem("Zezinho"))
                .body("filhos.name", Matchers.hasItems("Zezinho", "Luizinho"));

    }

    @Test
    public void VerificarErroUsuario() {
        //Trabalhando com Lista
        RestAssured.given()
                .when()
                .get(" https://restapi.wcaquino.me/users/4")
                .then()
                .statusCode(404)
                .body("error",Matchers.is("Usuário inexistente"));
    }
    @Test
    public void VerificarListaRaiz() {
        //Trabalhando com Lista
        RestAssured.given()
                .when()
                .get(" https://restapi.wcaquino.me/users")
                .then()
                .statusCode(200)
                .body("",Matchers.hasSize(3))
                .body("name",Matchers.hasItems("João da Silva","Maria Joaquina","Ana Júlia"))
                .body("age [1]",Matchers.is(25))
                .body("filhos.name",Matchers.hasItem(Arrays.asList("Zezinho","Luizinho")))
                .body("salary",Matchers.contains(1234.5677f, 2500, null))
                    ;


    }
    @Test
    public void VerificacoesAvancadas() {

        RestAssured.given()
                .when()
                .get(" https://restapi.wcaquino.me/users")
                .then()
                .statusCode(200)
                .body("",Matchers.hasSize(3))
                .body("age.findAll{it <=25}.size()",Matchers.is(2))//Quantos usuários for menor ou igual 25 anos
                .body("age.findAll{it <=25 && it > 20 }.size()",Matchers.is(1)) //Mais que 20 e até 25
                .body("findAll{it.age <=25 && it.age > 20 }.name",Matchers.hasItem("Maria Joaquina"))
                .body("findAll{it.age <=25 }[0].name",Matchers.is("Maria Joaquina"))
                .body("findAll{it.age <=25 }[-1].name",Matchers.is("Ana Júlia"))
                .body("find{it.age <=25 }.name",Matchers.is("Maria Joaquina"))
                .body("findAll{it.name.contains ('n') }.name",Matchers.hasItems("Maria Joaquina","Ana Júlia"))
                .body("findAll{it.name.length () > 10 }.name",Matchers.hasItems("João da Silva","Maria Joaquina"))
                .body("name.findAll{it.startsWith ('Maria')}.collect{it.toUpperCase()}" ,Matchers.hasItem("MARIA JOAQUINA"))
                .body("age.collect{it * 2 }", Matchers.hasItems(60,50,40))
                .body("id.max()",Matchers.is(3))
                .body("salary.min()",Matchers.is(1234.5678f))
                .body("salary.findAll {it != null}.sum()",Matchers.is(Matchers.closeTo(3734.5678f,0.001)))

        ;
    }

}