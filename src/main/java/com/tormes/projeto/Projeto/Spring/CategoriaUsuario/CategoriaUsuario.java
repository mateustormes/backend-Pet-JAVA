package com.tormes.projeto.Projeto.Spring.CategoriaUsuario;


import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categoria_usuario")
@Getter
@Setter
public class CategoriaUsuario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "descricao", length = 80)
    private String descricao;
    
    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "cd_user")
    private Integer cd_user;

    @Column(name = "dt_user")
    private LocalDate dt_user;
}
