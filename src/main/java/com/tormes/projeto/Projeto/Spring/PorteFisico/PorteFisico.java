package com.tormes.projeto.Projeto.Spring.PorteFisico;


import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.tormes.projeto.Projeto.Spring.Status.Status;

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
@Table(name = "porte_fisico")
public class PorteFisico {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "descricao")
    private String descricao;

    @OneToOne()
    @JoinColumn(name = "fk_status", nullable = true)
    private Status fk_status;

    @Column(name = "cd_user")
    private Integer cd_user;

    @Column(name = "dt_user")
    private LocalDate dt_user;
}
