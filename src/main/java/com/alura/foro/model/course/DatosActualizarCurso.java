package com.alura.foro.model.course;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(@NotNull Long id, String nombre, String categoria) {
}
