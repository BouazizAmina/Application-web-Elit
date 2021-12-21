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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_cotisations",referencedColumnName = "id")
    @Column(name = "id_cotisations")
    private Integer  id_cotisations;

}
