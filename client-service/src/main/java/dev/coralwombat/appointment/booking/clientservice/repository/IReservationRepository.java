package dev.coralwombat.appointment.booking.clientservice.repository;

import dev.coralwombat.appointment.booking.dto.ReservationDTO;

/**
 * Bean that handles database operations related to the {@link dev.coralwombat.appointment.booking.entities.Reservation}.
 */
public interface IReservationRepository {

    /**
     * Inserts a {@link dev.coralwombat.appointment.booking.entities.Reservation} to the datasource.
     *
     * @param reservation - The DTO to insert.
     */
    void insertReservation(ReservationDTO reservation);

}
