package dev.coralwombat.appointment.booking.clientservice.repository;

import dev.coralwombat.appointment.booking.dto.ReservationDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import dev.coralwombat.appointment.booking.entities.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ReservationRepository implements IReservationRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void insertReservation(ReservationDTO reservation) {
        var category = new Category();
        category.setId(reservation.getCategory());

        var entityReservation = new Reservation();
        entityReservation.setId(reservation.getId());
        entityReservation.setCategory(category);
        entityReservation.setCustomerId(reservation.getCustomerId());
        entityReservation.setFrom(reservation.getFrom());
        entityReservation.setTo(reservation.getTo());
        entityManager.persist(entityReservation);
    }
}
