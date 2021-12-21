package org.red.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString(of = "id", doNotUseGetters = true)
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
public class Reconposations  implements Serializable {


    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "reboressement")
    private String typere;


    @ManyToOne(optional = false,fetch = FetchType.LAZY,targetEntity = Cotisation.class)
    @JoinColumn(name = "id_cotisations",referencedColumnName = "id")
    private Cotisation  cotisation;




}
