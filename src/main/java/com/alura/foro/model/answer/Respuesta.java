package com.alura.foro.model.answer;

import com.alura.foro.model.topic.DatosActualizarTopico;
import com.alura.foro.model.topic.Topico;
import com.alura.foro.model.user.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne
    private Topico topico;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    private Usuario autor;
    private Boolean solucion = false;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.topico = new Topico(datosRegistroRespuesta.topico());
        this.autor = new Usuario(datosRegistroRespuesta.autor());
    }

    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {
        if(datosActualizarRespuesta.mensaje() != null){
            this.mensaje = datosActualizarRespuesta.mensaje();
        }
        if(datosActualizarRespuesta.topico() != null){
            this.topico = new Topico(datosActualizarRespuesta.topico());
        }
        if(datosActualizarRespuesta.autor() != null){
            this.autor = new Usuario(datosActualizarRespuesta.autor());
        }
        if(datosActualizarRespuesta.solucion() != null){
            this.solucion = datosActualizarRespuesta.solucion();
        }
    }
}
