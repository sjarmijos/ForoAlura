package com.alura.foro.model.answer;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(Long id,
                                      String mensaje,
                                      Long topico,
                                      LocalDateTime fechaCreacion,
                                      Long autor,
                                      Boolean solucion) {
}
