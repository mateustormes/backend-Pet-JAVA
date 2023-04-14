package com.tormes.projeto.Projeto.Spring.FotosPet;


import com.tormes.projeto.Projeto.Spring.Pet.Pet;
import com.tormes.projeto.Projeto.Spring.RacaAnimal.RacaAnimal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fotos_pet")
@Getter
@Setter
public class FotosPet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "foto")
    private byte[] foto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pet")
    private Pet pet;

    @Column(name = "cd_user")
    private Integer cd_user;

    @Column(name = "dt_user")
    private LocalDate dt_user;
}
