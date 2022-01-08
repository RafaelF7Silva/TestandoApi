package br.com.Rsilva;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;

public class FileTest {

    @Test
    public void EnvioDeArquivo(){
        RestAssured.given()
                .log().all()
                .multiPart("arquivo",new File("src/main/resources/users.pdf"))
                .when()
                .post("https://restapi.wcaquino.me/upload")
                .then()
                .log().all()
                .statusCode(200)
                .body("name",Matchers.is("users.pdf"))

                ;

    }
  }
