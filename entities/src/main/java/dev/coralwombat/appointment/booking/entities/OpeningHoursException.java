package dev.coralwombat.appointment.booking.entities;

import java.time.LocalDate;

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
public class OpeningHoursException {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Integer id;

	@ManyToOne
	@JoinColumn(name = "id", nullable = true)
	Category category;

	@Column(name = "from")
	LocalDate from;

	@Column(name = "to")
	LocalDate to;

}
