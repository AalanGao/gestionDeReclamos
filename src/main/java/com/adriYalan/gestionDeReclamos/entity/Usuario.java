package com.adriYalan.gestionDeReclamos.entity;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios_sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusuario")
    private int idUsuario;

    @Column(nullable = false, unique = true,name="email")
    private String email;

    @Column(nullable = false,name="password")
    private String password;

    @Column(nullable = false,name = "documento")
    private String documento;


    @Column(nullable = false,name="rol")
    private String rol;


}

