package dev.coralwombat.appointment.booking.admin.controller;

import dev.coralwombat.appointment.booking.dto.OpeningHoursDropoutDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Contains every operations related to {@link dev.coralwombat.appointment.booking.entities.OpeningHoursDropout}.
 */
public interface IOpeningHoursDropoutController {

    /**
     * Collects all dropouts of a category that's interval is collides or after the given date.
     *
     * @param categoryId - The id of the category.
     * @param fromDate   - The date from the collection is relevant.
     * @return A list that contains the dropouts that are looked for.
     */
    List<OpeningHoursDropoutDTO> getOpeningHoursDropout(Integer categoryId, LocalDate fromDate);

}
