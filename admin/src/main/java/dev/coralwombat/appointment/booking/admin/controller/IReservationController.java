package dev.coralwombat.appointment.booking.admin.controller;

import dev.coralwombat.appointment.booking.dto.ReservationDTO;

import java.util.List;

/**
 * Contains every operations related to {@link dev.coralwombat.appointment.booking.entities.Reservation}.
 */
public interface IReservationController {

    /**
     * Collects all reservations.
     *
     * @return All reservations in a list.
     */
    List<ReservationDTO> getAllReservations();

    /**
     * Gets all reservations of a category and all of it's child.
     *
     * @param categoryId - The id of the category of the reservations.
     * @return The reservations in a list.
     */
    List<ReservationDTO> getReservations(Integer categoryId);

    /**
     * Deletes a reservation.
     *
     * @param reservationId - The id of the reservation to delete.
     */
    void deleteReservation(Integer reservationId);

}
