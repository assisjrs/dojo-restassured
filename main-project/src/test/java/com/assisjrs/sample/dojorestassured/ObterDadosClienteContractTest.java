package com.assisjrs.sample.dojorestassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class ObterDadosClienteContractTest {

    @BeforeAll
    static void before() {
        RestAssured.port = 9090;
    }

    @Test
    void deve_retornar_os_dados_conforme_o_schema() {
        given()
                .pathParam("id", 1)
        .when()
                .get("/clientes/{id}")
        .then()
                .assertThat().body(matchesJsonSchemaInClasspath("cliente-schema.json"));
    }

    @Test
    void deve_retornar_status_code_200_quando_encontrar_o_cliente_pelo_id() {
        given()
                .pathParam("id", 1)
        .when()
                .get("/clientes/{id}")
        .then()
                .statusCode(200);
    }

    @Test
    void deve_retornar_status_code_404_quando_nao_encontrar_o_cliente_pelo_id() {
        given()
                .pathParam("id", 0)
        .when()
                .get("/clientes/{id}")
        .then()
                .statusCode(404);
    }

    @Test
    void deve_retornar_nome_cliente() {
        given()
                .pathParam("id", 1)
        .when()
                .get("/clientes/{id}")
        .then()
                .assertThat().body("nome", equalTo("Assis"));
    }
}
