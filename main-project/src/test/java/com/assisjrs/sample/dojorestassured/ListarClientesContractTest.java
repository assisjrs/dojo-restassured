package com.assisjrs.sample.dojorestassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class ListarClientesContractTest {

    @BeforeAll
    static void before() {
        RestAssured.port = 9090;
    }

    @Test
    void deve_retornar_status_code_200() {
        get("/clientes").then().statusCode(200);
    }

    @Test
    void deve_retornar_uma_lista_de_clientes() {
        get("/clientes")
                .then()
                .assertThat().body("$.size()", equalTo(2));
    }

    @Test
    void deve_retornar_a_resposta_da_requisicao_em_menos_de_1_segundo() {
       when()
               .get("/clientes")
       .then()
               .time(lessThanOrEqualTo(1L), SECONDS);
    }
}
