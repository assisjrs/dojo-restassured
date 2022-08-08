package com.assisjrs.sample.dojorestassured.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final List<Cliente> clientes = asList(
            Cliente.builder().id(1).nome("Assis").build(),
            Cliente.builder().id(2).nome("Júnior").build()
    );

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return ok(clientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getById(@PathVariable final Integer id) {
        final var cliente = clientes.stream().filter(c -> c.getId().equals(id))
                .findAny();

        if (cliente.isPresent())
            return ok(cliente.get());

        return notFound().build();
    }
}
