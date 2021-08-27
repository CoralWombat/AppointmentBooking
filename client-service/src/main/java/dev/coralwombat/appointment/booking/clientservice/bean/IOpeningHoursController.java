package dev.coralwombat.appointment.booking.clientservice.bean;

import dev.coralwombat.appointment.booking.clientservice.exception.AppointmentBookingException;
import dev.coralwombat.appointment.booking.dto.OpeningHoursDTO;
import lombok.NonNull;

import java.time.DayOfWeek;

/**
 * Contains every operations related to {@link dev.coralwombat.appointment.booking.entities.OpeningHours}.
 */
public interface IOpeningHoursController {

    /**
     * Looks for the OpeningHours of a category, and it's ancestors on a given day.
     *
     * @param categoryId - The category that is looked for.
     * @param day        - The day of the week.
     * @return An OpeningHoursTDO with opening hours.
     * @throws AppointmentBookingException If there is no OpeningHours found for that category or that category does not exist.
     */
    OpeningHoursDTO getOpeningHours(@NonNull Integer categoryId, @NonNull DayOfWeek day) throws AppointmentBookingException;

}
