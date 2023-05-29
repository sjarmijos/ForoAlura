package com.alura.foro.controller;

import com.alura.foro.model.answer.*;
import com.alura.foro.model.topic.DatosActualizarTopico;
import com.alura.foro.model.topic.Topico;
import com.alura.foro.model.user.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> crearRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
                                                                  UriComponentsBuilder uriComponentsBuilder){
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta));
        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                new Topico(respuesta.getTopico().getId()).getId(),
                respuesta.getFechaCreacion(),
                new Usuario(respuesta.getAutor().getId()).getId(),
                respuesta.getSolucion());
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestas(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosListadoRespuesta::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return ResponseEntity.ok(
                new DatosRespuestaRespuesta(
                        respuesta.getId(),
                        respuesta.getMensaje(),
                        new Topico(respuesta.getTopico().getId()).getId(),
                        respuesta.getFechaCreacion(),
                        new Usuario(respuesta.getAutor().getId()).getId(),
                        respuesta.getSolucion()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();
    }
}
