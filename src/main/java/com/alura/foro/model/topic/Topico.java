package com.alura.foro.model.topic;

import com.alura.foro.model.answer.Respuesta;
import com.alura.foro.model.course.Curso;
import com.alura.foro.model.user.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(String titulo, String mensaje, Usuario autor, Curso curso){
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
    }

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.autor = new Usuario(datosRegistroTopico.autor());
        this.curso = new Curso(datosRegistroTopico.curso());
    }

    public Topico(Long topico) {
        this.id = topico;
    }


    public Long autorID(Usuario autor){
        return autor.getId();
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if(datosActualizarTopico.titulo() != null){
            this.titulo = datosActualizarTopico.titulo();
        }
        if(datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if(datosActualizarTopico.status() != null){
            this.status = datosActualizarTopico.status();
        }
        if(datosActualizarTopico.autor() != null){
            this.autor = new Usuario(datosActualizarTopico.autor());
        }
        if(datosActualizarTopico.curso() != null){
            this.curso = new Curso(datosActualizarTopico.curso());
        }
    }

    public void desactivarTopico(){
        this.status = StatusTopico.DESACTIVADO;
    }
}
