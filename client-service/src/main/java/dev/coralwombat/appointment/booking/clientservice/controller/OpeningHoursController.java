package dev.coralwombat.appointment.booking.clientservice.controller;

import dev.coralwombat.appointment.booking.clientservice.exception.AppointmentBookingException;
import dev.coralwombat.appointment.booking.clientservice.repository.ICategoryRepository;
import dev.coralwombat.appointment.booking.clientservice.repository.IOpeningHoursRepository;
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
public class OpeningHoursController implements IOpeningHoursController{

    ICategoryRepository categoryRepository;

    IOpeningHoursRepository openingHoursRepository;

    @Autowired
    public OpeningHoursController(ICategoryRepository categoryRepository, IOpeningHoursRepository openingHoursRepository) {
        this.categoryRepository = categoryRepository;
        this.openingHoursRepository = openingHoursRepository;
    }

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
