package dev.coralwombat.appointment.booking.clientservice.bean;

import dev.coralwombat.appointment.booking.clientservice.exception.AppointmentBookingException;
import dev.coralwombat.appointment.booking.clientservice.repository.CategoryRepository;
import dev.coralwombat.appointment.booking.clientservice.repository.OpeningHoursRepository;
import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import dev.coralwombat.appointment.booking.dto.OpeningHoursDTO;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.NoResultException;
import java.time.DayOfWeek;

@Log4j2
@Controller
public class OpeningHoursController {

    @Autowired
    OpeningHoursRepository openingHoursRepository;

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Looks for the OpeningHours of a category, and it's ancestors on a given day.
     *
     * @param categoryId - The category that is looked for.
     * @param day        - The day of the week.
     * @return An OpeningHoursTDO with opening hours.
     * @throws AppointmentBookingException If there is no OpeningHours found for that category or that category does not exist.
     */
    public OpeningHoursDTO getOpeningHours(@NonNull Integer categoryId,
                                           @NonNull DayOfWeek day) throws AppointmentBookingException {

        CategoryDTO category;
        try {
            category = categoryRepository.getCategory(categoryId);
        } catch (NoResultException e) {
            throw new AppointmentBookingException(e.getMessage(), e);
        }

        OpeningHoursDTO openingHours = null;
        do {
            log.trace("Looking for opening hours for category: " + category.getId() + " on day: " + day);
            try {
                openingHours = openingHoursRepository.getOpeningHours(category.getId(), day);
            } catch (NoResultException e) {
                log.trace(e.getMessage());
            }

            try {
                category = category.getParent() != null ? categoryRepository.getCategory(category.getParent()) : null;
            } catch (NoResultException e) {
                category = null;
            }

        } while (openingHours == null && category != null);

        if (openingHours == null)
            throw new AppointmentBookingException("Opening hours not found for category: " + categoryId + " on day: " + day, null);

        return openingHours;
    }

}
