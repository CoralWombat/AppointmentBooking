package dev.coralwombat.appointment.booking.clientservice.repository;

import dev.coralwombat.appointment.booking.dto.OpeningHoursDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import dev.coralwombat.appointment.booking.entities.OpeningHours;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.DayOfWeek;

@Log4j2
@Repository
public class OpeningHoursRepository implements IOpeningHoursRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public OpeningHoursDTO getOpeningHours(@NonNull Integer categoryId,
                                           @NonNull DayOfWeek day) throws NoResultException {
        Category category = new Category();
        category.setId(categoryId);

        OpeningHours openingHours = entityManager
                .createQuery("SELECT o FROM OpeningHours o WHERE o.category = :category AND o.day in :days", OpeningHours.class)
                .setParameter("category", category)
                .setParameter("days", day)
                .getSingleResult();

        return new OpeningHoursDTO(openingHours);
    }
}
