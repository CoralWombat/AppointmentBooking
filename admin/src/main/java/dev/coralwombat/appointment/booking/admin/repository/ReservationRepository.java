package dev.coralwombat.appointment.booking.admin.repository;

import dev.coralwombat.appointment.booking.dto.ReservationDTO;
import dev.coralwombat.appointment.booking.entities.Reservation;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ReservationRepository implements IReservationRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ReservationDTO> getReservations(@NonNull Integer categoryId) {
        List<ReservationDTO> reservations = new LinkedList<>();

        Collection<Reservation> entityReservations = entityManager
                .createQuery("SELECT r FROM Reservation r WHERE r.category.id = :categoryId", Reservation.class)
                .setParameter("categoryId", categoryId)
                .getResultList();

        for (Reservation entityReservation : entityReservations) {
            reservations.add(new ReservationDTO(entityReservation));
        }

        return reservations;
    }

    @Override
    public void deleteReservation(@NonNull Integer reservationId) {
        entityManager.createQuery("DELETE FROM Reservation c WHERE c.id = :id")
                .setParameter("id", reservationId)
                .executeUpdate();
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> entityReservations = entityManager
                .createQuery("SELECT r FROM Reservation r", Reservation.class)
                .getResultList();

        List<ReservationDTO> reservations = new LinkedList<>();
        for (Reservation entityReservation : entityReservations) {
            reservations.add(new ReservationDTO(entityReservation));
        }
        return reservations;
    }
}
