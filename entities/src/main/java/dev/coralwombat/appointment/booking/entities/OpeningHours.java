package dev.coralwombat.appointment.booking.entities;

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
public class OpeningHours {
	
	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "id", nullable = false)
	Category category;

	@Id
	@Column(name = "day")
	DayOfWeek day;

	@Column(name = "from")
	LocalTime from;

	@Column(name = "to")
	LocalTime to;

}
