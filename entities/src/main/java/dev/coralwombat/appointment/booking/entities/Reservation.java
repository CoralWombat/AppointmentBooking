package dev.coralwombat.appointment.booking.entities;

import java.io.Serializable;
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
@Table(name = "reservation")
@Data
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1734552087612247006L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Integer id;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = true)
	Category category;

	@Column(name = "fromDate")
	LocalDate from;

	@Column(name = "toDate")
	LocalDate to;

	@Column(name = "customerName")
	String customerName;

	@Column(name = "customerPhone")
	String customerPhone;

	@Column(name = "customerEmail")
	String customerEmail;

	@Column(name = "notificationSend")
	Boolean notificationSend;

}
