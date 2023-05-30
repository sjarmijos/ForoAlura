package com.alura.foro.controller;

import com.alura.foro.model.answer.RespuestaRepository;
import com.alura.foro.model.course.Curso;
import com.alura.foro.model.topic.*;
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
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new Usuario(topico.getAutor().getId()).getId(),
                new Curso(topico.getCurso().getId()).getId());
        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestasDeCadaTopico> listarRespuestasPorTopico(@PathVariable Long id, @PageableDefault(size = 2) Pageable paginacion) {
        Topico topico = topicoRepository.getReferenceById(id);
        if (topico != null) {
            var respuestas = respuestaRepository.findByTopicoId(id, paginacion);
            var listaRespuestas = respuestas.map(DatosRespuestaPorTopicos::new);
            return ResponseEntity.ok(new DatosRespuestasDeCadaTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    listaRespuestas.getContent()
            ));
        }else{
            System.out.println("no se encontro");
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new Usuario(topico.getAutor().getId()).getId(),
                new Curso(topico.getCurso().getId()).getId()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

}
