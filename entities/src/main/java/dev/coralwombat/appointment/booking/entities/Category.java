package dev.coralwombat.appointment.booking.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = -5229059936348972714L;

    @Id
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "lenght")
    Integer lenght;

    @JoinColumn(name = "parent_id", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    Category parent;

}
