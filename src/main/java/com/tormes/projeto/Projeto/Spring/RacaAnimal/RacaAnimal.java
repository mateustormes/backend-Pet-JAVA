package com.tormes.projeto.Projeto.Spring.RacaAnimal;


import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.tormes.projeto.Projeto.Spring.CategoriaAnimal.CategoriaAnimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "raca_animal")
public class RacaAnimal {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @OneToOne()
    @JoinColumn(name = "fk_categoria_animal", nullable = true)
    private CategoriaAnimal categoria_animal;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "cd_user")
    private Integer cd_user;
    
    @Column(name = "dt_user")
    private LocalDate dt_user;
}
