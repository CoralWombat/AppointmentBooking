package dev.coralwombat.appointment.booking.admin.repository;

import dev.coralwombat.appointment.booking.dto.ReservationDTO;

import java.util.List;

/**
 * Bean that handles database operations related to the {@link dev.coralwombat.appointment.booking.entities.Reservation}.
 */
public interface IReservationRepository {

    /**
     * Collects the direct reservations of a category from the datasource.
     *
     * @param categoryId - The id of the category of the reservations.
     * @return The reservations in a list.
     */
    List<ReservationDTO> getReservations(Integer categoryId);

    /**
     * Deletes a reservation from the datasource by id.
     *
     * @param reservationId - The id of the reservation.
     */
    void deleteReservation(Integer reservationId);

    /**
     * Collects all reservations from the datasource.
     */
    List<ReservationDTO> getAllReservations();
}
