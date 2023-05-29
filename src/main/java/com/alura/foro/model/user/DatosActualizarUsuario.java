package com.alura.foro.model.user;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(@NotNull Long id, String nombre, String email, String password) {
}
