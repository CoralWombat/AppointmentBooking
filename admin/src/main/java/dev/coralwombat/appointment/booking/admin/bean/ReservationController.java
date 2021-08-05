package dev.coralwombat.appointment.booking.admin.bean;

import dev.coralwombat.appointment.booking.dto.ReservationDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import dev.coralwombat.appointment.booking.entities.Reservation;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.LinkedList;

@Log4j2
@Repository
public class ReservationController {

    @PersistenceContext
    EntityManager entityManager;

    public Collection<ReservationDTO> getReservations(@NonNull Integer categoryId) {
        Collection<ReservationDTO> reservations = new LinkedList<>();

        Collection<Integer> unprocessedCategories = new LinkedList<>();
        unprocessedCategories.add(categoryId);
        while (!unprocessedCategories.isEmpty()) {
            Integer currentCategory = unprocessedCategories.stream().findFirst().orElse(-1);
            unprocessedCategories.remove(currentCategory);

            unprocessedCategories.addAll(findChildrenCategoryIds(currentCategory));

            Category category = entityManager.find(Category.class, currentCategory);
            Collection<Reservation> entityReservations = entityManager
                    .createQuery("SELECT r FROM Reservation r WHERE r.category = :category", Reservation.class)
                    .setParameter("category", category)
                    .getResultList();

            for (Reservation entityReservation : entityReservations) {
                reservations.add(new ReservationDTO(entityReservation));
            }
        }

        return reservations;
    }

    public Collection<ReservationDTO> getAllReservations() {
        Collection<Reservation> entityReservations = entityManager
                .createQuery("SELECT r FROM Reservation r", Reservation.class)
                .getResultList();

        Collection<ReservationDTO> reservations = new LinkedList<>();
        for (Reservation entityReservation : entityReservations) {
            reservations.add(new ReservationDTO(entityReservation));
        }
        return reservations;
    }

    private Collection<Integer> findChildrenCategoryIds(@NonNull Integer parentId) {
        var parent = entityManager.find(Category.class, parentId);

        Collection<Category> entityCategories = entityManager
                .createQuery("SELECT c FROM Category c WHERE c.parent = :parent", Category.class)
                .setParameter("parent", parent)
                .getResultList();

        Collection<Integer> categoryIds = new LinkedList<>();
        for (Category category : entityCategories) {
            categoryIds.add(category.getId());
        }
        return categoryIds;
    }

}
