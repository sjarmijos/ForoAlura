package com.alura.foro.model.answer;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarRespuesta(@NotBlank Long id, String mensaje, Long topico,
                                       Long autor, Boolean solucion) {
}
