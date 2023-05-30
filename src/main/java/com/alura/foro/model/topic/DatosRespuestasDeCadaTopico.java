package com.alura.foro.model.topic;

import java.util.List;

public record DatosRespuestasDeCadaTopico(Long id,
                                          String titulo,
                                          String mensaje,
                                          List<DatosRespuestaPorTopicos> respuestas) {
}
