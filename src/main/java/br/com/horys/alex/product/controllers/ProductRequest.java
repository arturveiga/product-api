package br.com.horys.alex.product.controllers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record ProductRequest(
    @NotBlank()
    @Size(min = 1, max = 100)
    String description,
    @NotNull
    Long categoryId
) {
}
