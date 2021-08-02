package dev.coralwombat.appointment.booking.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = -5229059936348972714L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "name")
    String name;

    @JoinColumn(name = "parent_id", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    Category parent;

}
