package dev.coralwombat.appointment.booking.admin.repository;

import dev.coralwombat.appointment.booking.dto.OpeningHoursDropoutDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Bean that handles database operations related to the {@link dev.coralwombat.appointment.booking.entities.OpeningHoursDropout}.
 */
public interface IOpeningHoursDropoutRepository {

    /**
     * Looks up the dropouts of a category that's interval is collides or after the given date.
     *
     * @param categoryId - The id of the category.
     * @param fromDate   - The date from the collection is relevant.
     * @return A list that contains the dropouts that are looked for.
     */
    List<OpeningHoursDropoutDTO> getOpeningHoursDropout(Integer categoryId, LocalDate fromDate);

}
