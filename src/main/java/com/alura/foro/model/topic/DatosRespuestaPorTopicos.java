package com.alura.foro.model.topic;

import com.alura.foro.model.answer.Respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaPorTopicos (Long id,
                                        Long autor_id,
                                        LocalDateTime fechaCreacion,
                                        String mensaje,
                                        boolean solucion) {

    public DatosRespuestaPorTopicos(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getAutor().getId(),
                respuesta.getFechaCreacion(),
                respuesta.getMensaje(),
                respuesta.getSolucion()
        );
    }
}
