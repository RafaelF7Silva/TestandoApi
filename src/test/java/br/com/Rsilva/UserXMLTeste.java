package br.com.Rsilva;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class UserXMLTeste {



    @Test
    public void TrabalahndoComXML_01() {

        RestAssured.baseURI ="https://restapi.wcaquino.me";

        RestAssured.given()
                .when()
                .get(" https://restapi.wcaquino.me/usersXML/3")
                .then()
                .statusCode(200)
                .body("user.name", Matchers.is("Ana Julia"))
                .body("user.@id",Matchers.is("3"))
                .body("user.filhos.name.size()",Matchers.is(2))
                .body("user.filhos.name[0]",Matchers.is("Zezinho"))
                .body("user.filhos.name",Matchers.hasItems("Luizinho","Zezinho"))
        ;

    }
    @Test
    public void TrabalahndoComXML_02() {

        RestAssured.given()
                .when()
                .get(" https://restapi.wcaquino.me/usersXML/3")
                .then()
                .statusCode(200)
                .rootPath("user")
                .body("name", Matchers.is("Ana Julia"))
                .body("@id",Matchers.is("3"))

                .rootPath("user.filhos")

                .body("name.size()",Matchers.is(2))
                .body("name[0]",Matchers.is("Zezinho"))
                .body("name",Matchers.hasItems("Luizinho","Zezinho"))
        ;
    }
    @Test
    public void TrabalahndoComXML_03() {

        RestAssured.given()
                .when()
                .get(" https://restapi.wcaquino.me/usersXML")
                .then()
                .statusCode(200)
                .rootPath("users.user")
                .body("size()", Matchers.is(3))
                .body("findAll {it.age.toInteger() <= 25}.size()",Matchers.is(2))
                .body("find {it.age== 25}.name",Matchers.is("Maria Joaquina"))


        ;
    }



}

