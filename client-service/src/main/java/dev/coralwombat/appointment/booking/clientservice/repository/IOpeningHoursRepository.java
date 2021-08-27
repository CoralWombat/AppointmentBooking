package dev.coralwombat.appointment.booking.clientservice.repository;

import dev.coralwombat.appointment.booking.dto.OpeningHoursDTO;
import lombok.NonNull;

import javax.persistence.NoResultException;
import java.time.DayOfWeek;

/**
 * Bean that handles database operations related to the {@link dev.coralwombat.appointment.booking.entities.OpeningHours}.
 */
public interface IOpeningHoursRepository {

    /**
     * Looks for the OpeningHours in the datasource. If did not find anything does not search in the parent category.
     *
     * @param categoryId - The id of the category to look for.
     * @param day        - The day of the week to look for.
     * @return A OpeningHoursDTO with the category and day that was looked for.
     * @throws NoResultException when there is no result for these inputs.
     */
    OpeningHoursDTO getOpeningHours(@NonNull Integer categoryId, @NonNull DayOfWeek day) throws NoResultException;

}
