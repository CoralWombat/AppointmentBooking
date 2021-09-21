package dev.coralwombat.appointment.booking.admin.repository;

import dev.coralwombat.appointment.booking.dto.OpeningHoursDropoutDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import dev.coralwombat.appointment.booking.entities.OpeningHoursDropout;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Log4j2
@Repository
public class OpeningHoursDropoutRepository implements IOpeningHoursDropoutRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<OpeningHoursDropoutDTO> getOpeningHoursDropout(@NonNull Integer categoryId, LocalDate fromDate) {
        List<OpeningHoursDropoutDTO> exceptions = new LinkedList<>();

        var category = entityManager.find(Category.class, categoryId);
        while (category != null) {
            List<OpeningHoursDropout> dbExceptions;

            if (fromDate == null) {
                dbExceptions = entityManager.createQuery("SELECT o FROM OpeningHoursDropout o WHERE o.category = :category", OpeningHoursDropout.class)
                        .setParameter("category", category)
                        .getResultList();
            } else {
                dbExceptions = entityManager.createQuery("SELECT o FROM OpeningHoursDropout o WHERE o.category = :category and o.to > :fromDate", OpeningHoursDropout.class)
                        .setParameter("category", category)
                        .setParameter("fromDate", fromDate)
                        .getResultList();
            }

            for (OpeningHoursDropout dbException : dbExceptions) {
                exceptions.add(new OpeningHoursDropoutDTO(dbException));
            }

            category = category.getParent() != null ? entityManager.find(Category.class, category.getParent().getId()) : null;
        }

        return exceptions;
    }
}
