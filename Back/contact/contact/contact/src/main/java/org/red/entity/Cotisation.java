package org.red.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "gpe_cotisation")
@XmlRootElement
@Data
@ToString(of = "id", doNotUseGetters = true)
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@NamedQueries({
        @NamedQuery(name = "Cotisation.findAll", query = "SELECT g FROM Cotisation g"),
        @NamedQuery(name = "Cotisation.findById", query = "SELECT g FROM Cotisation g WHERE g.id = :id")})
public class Cotisation implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "taux")
    @Basic
    private String taux;

   @JsonIgnore
   @OneToMany(cascade = {CascadeType.ALL},targetEntity = Reconposations.class,fetch = FetchType.LAZY,mappedBy = "cotisation")
   private List<Reconposations> listreboresssment;







}
