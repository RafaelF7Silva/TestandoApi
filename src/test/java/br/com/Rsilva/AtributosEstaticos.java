package br.com.Rsilva;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class AtributosEstaticos {


    @BeforeClass
    public static void Setup(){
        RestAssured.baseURI = "https://restapi.wcaquino.me";
    }



    @Test
    public void CriandoAtributosEstaticos() {


        //RestAssured.port = 443;
        //RestAssured.basePath = "/v2";

        RestAssured.given()
                .log().all()
                .when()
                .get(" /users")
                .then()
                .statusCode(200)

        ;

    }

    @Test
    public void BaseAtributosEstaticos() {

        RestAssured.given()
                .when()
                .get(" /usersXML/3")
                .then()
                .statusCode(200)
                .body("user.name", Matchers.is("Ana Julia"))
                .body("user.@id",Matchers.is("3"))
                .body("user.filhos.name.size()",Matchers.is(2))
                .body("user.filhos.name[0]",Matchers.is("Zezinho"))
                .body("user.filhos.name",Matchers.hasItems("Luizinho","Zezinho"))
        ;

    }
}