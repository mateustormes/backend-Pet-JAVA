package com.tormes.projeto.Projeto.Spring.PetUsuario;


import com.tormes.projeto.Projeto.Spring.Pet.Pet;
import com.tormes.projeto.Projeto.Spring.Usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pet_usuario")
@Getter
@Setter
public class PetUsuario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pet")
    private Pet pet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @Column(name = "cd_user")
    private Integer cd_user;

    @Column(name = "dt_user")
    private LocalDate dt_user;
}
