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
@Table(name = "reservation")
@Data
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1734552087612247006L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @Column(name = "from_time")
    LocalDateTime from;

    @Column(name = "to_time")
    LocalDateTime to;

    @Column(name = "customer_id")
    String customerId;

}
