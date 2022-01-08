package br.com.Rsilva;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OlaMundoTest {

         //Execução via Junit com métodos de teste com as notações

       @Test

    public void Test_01_OlaMundo(){

           // Requisição do tipo Get, objeto de validação e verificação das respostas.

           Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");

          // Verificação com a  assertiva Equals:

           System.out.println();
           System.out.println(response.getBody().asString().equals("Ola Mundo!"));
           System.out.println(response.statusCode() == 200);

         // Assertiva booleana :

              Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
              Assert.assertTrue(response.statusCode() == 200);

        // Assertiva com Equals ( o qué esperado e o atual) :

              Assert.assertEquals(200 , response.statusCode());

        // Ferramenta de validação do RestAssured:

           ValidatableResponse validacao =  response.then();
           validacao.statusCode(200);

       }

       @Test
       public void Test_01_Outras_Formas_RestAssured(){

       Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
       ValidatableResponse validacao =  response.then();
       validacao.statusCode(200);

       // Simplificando em uma linha as validações acima.

       //RestAssured.get("https://restapi.wcaquino.me/ola").then().statusCode(200);  (sem falha)
       // RestAssured.get("https://restapi.wcaquino.me/ola").then().statusCode(201); (causando falha)
      // Utilizando alt+enter  definiu uma import statico do RestAssured*

       get("https://restapi.wcaquino.me/ola").then().statusCode(200);


       //Modo Fluente de leitura e organização de código:
       // Dado (Given) == pré condição
       // Quando (When) for feita uma requisição ou ação na UrL
       // Então (Then) o Status code será:

       RestAssured.given()
                  .when()
                         .get("https://restapi.wcaquino.me/ola")
                  .then()
                         .statusCode(200);

      }


       @Test
    public void Test_02_Matchers_Hamcrest(){

       // Validações do corpo HTML ou body do response com a biblioteca do Hamcrest que é uma depênencia do Junit.

       // Assertivas do Hamcrest é inverso do Equals vem o atual e depois o esperado

        //Assert.assertThat("Maria", Matchers.is("Maria"));
        //Foi criado um import Statico dos Matcher*


        Assert.assertThat("Maria", is("Maria"));       // is comparada igualdade
        Assert.assertThat(128, is(128));
        Assert.assertThat(128, isA(Integer.class));        //Verifica o tipo, se ele é um inteiro
        Assert.assertThat(128d, isA(Double.class));        //Verifica o tipo, se ele é um double
        Assert.assertThat(128d, greaterThan(120d));  //Verifica o 128 é maior que 120
        Assert.assertThat(128d, lessThan(130d));     //Verifica o 128 é menor que 120



           List<Integer> impares = Arrays.asList( 1,3,5,7,9);

        Assert.assertThat(impares, hasSize(5));                            //Verifica o tamanho da lista
        Assert.assertThat(impares, contains(1,3,5,7,9));                   //Verifica se contém os valores
        Assert.assertThat(impares, containsInAnyOrder(1,3,9,7,5));  //Verifica se contém os valores fora de ordem
        Assert.assertThat(impares, hasItem(1));                           //Verifica um item da lista
        Assert.assertThat(impares, hasItems(1,9));                        //Verifica aluns itens da lista

        // Matcher.is serve como conector de frases:

        Assert.assertThat("Maria", is(not("Pedro")));
        Assert.assertThat("Maria", anyOf(is("Joana"),is("Maria")));
        Assert.assertThat("Maria", allOf(startsWith("Ma"),endsWith("ria"),containsString("a")));
     }


     @Test
    public void ValidarBody(){

         RestAssured.given()
                 .when()
                 .get(" https://restapi.wcaquino.me/ola")
                 .then()
                 .statusCode(200)
                 .body(Matchers.is("Ola Mundo!"))
                 .body(containsString("Mundo"))
                 .body(Matchers.is(not(nullValue()))); // verifique que o corpo não está vazio



     }






}
