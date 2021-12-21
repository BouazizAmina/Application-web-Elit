package org.red.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @ToString
@Table(name = "gpe_cotisation")
public class Cotisation implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "taux")
    @Basic
    private String taux;
}
