package com.assisjrs.sample.dojorestassured.controller;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cliente {
    private Integer id;
    private String nome;
}
