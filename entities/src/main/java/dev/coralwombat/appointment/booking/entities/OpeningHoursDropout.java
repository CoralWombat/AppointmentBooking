package dev.coralwombat.appointment.booking.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "opening_hours_exception")
@Data
public class OpeningHoursDropout implements Serializable {

    private static final long serialVersionUID = -1592489058864784896L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    Category category;

    @Column(name = "from_date")
    LocalDateTime from;

    @Column(name = "to_date")
    LocalDateTime to;

}
