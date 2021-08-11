package dev.coralwombat.appointment.booking.entities;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "opening_hours")
@Data
public class OpeningHours implements Serializable {

    private static final long serialVersionUID = -8928316118076792489L;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Id
    @Column(name = "day")
    DayOfWeek day;

    @Column(name = "from_time")
    LocalTime from;

    @Column(name = "to_time")
    LocalTime to;

}
