package com.tormes.projeto.Projeto.Spring.Pet;


import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tormes.projeto.Projeto.Spring.RacaAnimal.RacaAnimal;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pet")
@Getter
@Setter
public class Pet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "nome", length = 100)
    private String nome;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_raca_animal")
    private RacaAnimal racaAnimal;

    @Column(name = "data_nascimento")
    private LocalDate data_nascimento;

    @Column(name = "cd_user")
    private Integer cd_user;

    @Column(name = "dt_user")
    private LocalDate dt_user;
}
