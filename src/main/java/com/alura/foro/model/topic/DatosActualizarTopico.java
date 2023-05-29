package com.alura.foro.model.topic;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id, String titulo, String mensaje, StatusTopico status,
                                    Long autor, Long curso) {
}
