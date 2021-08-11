package dev.coralwombat.appointment.booking.clientservice.bean;

import java.time.DayOfWeek;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dev.coralwombat.appointment.booking.clientservice.exception.AppointmentBookingException;
import dev.coralwombat.appointment.booking.dto.OpeningHoursDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import dev.coralwombat.appointment.booking.entities.OpeningHours;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class OpeningHoursController {

    @PersistenceContext
    EntityManager entityManager;

    public OpeningHoursDTO getOpeningHours(@NonNull Integer categoryId,
                                           @NonNull DayOfWeek day) throws AppointmentBookingException {

        var category = entityManager.find(Category.class, categoryId);
        if (category == null)
            throw new AppointmentBookingException("Category not found: " + categoryId, null);

        OpeningHours openingHours = null;
        do {
            log.trace("Looking for opening hours for category: " + category.getId() + " on day: " + day);
            try {
                openingHours = entityManager
                        .createQuery("SELECT o FROM OpeningHours o WHERE o.category = :category AND o.day in :days", OpeningHours.class)
                        .setParameter("category", category)
                        .setParameter("days", day)
                        .getSingleResult();
            } catch (NoResultException e) {
                log.trace(e.getMessage());
            }

            category = category.getParent() != null ? entityManager.find(Category.class, category.getParent().getId()) : null;

        } while (openingHours == null && category != null);

        if (openingHours == null)
            throw new AppointmentBookingException("Opening hours not found for category: " + categoryId + " on day: " + day, null);

        return new OpeningHoursDTO(openingHours);
    }

}
