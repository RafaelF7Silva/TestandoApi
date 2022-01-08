package br.com.Rsilva;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundo {

    public static void main(String[] args) {

        //Objeto response que está recebendo essa requisição URL

        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");

       /*
        Acessando recursos do servidor e imprimindo na tela,realizado uma requisição e analisada se está
        iguais ao esperado.
       */

        System.out.println(response.getBody().asString());

        System.out.println(response.statusCode());
        /*
        Status code é a resposta da requisição
        Principal Status cod 200
        Erros de cliente ex 404 requisição  errada , 400 requisição errada etc..
     */
        // Verificação com a  assertiva Equals

        System.out.println();
        System.out.println(response.getBody().asString().equals("Ola Mundo!"));
        System.out.println(response.statusCode() == 200);


        // Ferramenta de validação do RestAssured.

       ValidatableResponse validacao =  response.then();
       validacao.statusCode(200);

    }




}
