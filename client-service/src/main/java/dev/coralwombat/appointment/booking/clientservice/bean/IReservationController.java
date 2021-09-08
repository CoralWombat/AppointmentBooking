package dev.coralwombat.appointment.booking.clientservice.bean;

import dev.coralwombat.appointment.booking.dto.ReservationDTO;

/**
 * Contains every operations related to {@link dev.coralwombat.appointment.booking.entities.Reservation}.
 */
public interface IReservationController {

    /**
     * Submits a {@link ReservationDTO} to the datasource.
     *
     * @param reservationDTO - The {@link ReservationDTO} to submit.
     */
    void insertReservation(ReservationDTO reservationDTO);

}
