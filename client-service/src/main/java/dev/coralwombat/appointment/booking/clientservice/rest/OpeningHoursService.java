package dev.coralwombat.appointment.booking.clientservice.rest;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.coralwombat.appointment.booking.clientservice.bean.OpeningHoursController;
import dev.coralwombat.appointment.booking.clientservice.exception.AppointmentBookingException;
import dev.coralwombat.appointment.booking.dto.OpeningHoursDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/openingHours")
public class OpeningHoursService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    OpeningHoursController openingHoursController;

    @GetMapping(path = "/get")
    @ApiOperation(value = "Gets the opening hours for the given category",
	    notes = "Retrieves the opening hours from the database.")
    public ResponseEntity<Map<DayOfWeek, OpeningHoursDTO>> get(@ApiParam(required = true, value = "The id of the category.") @RequestParam(required = true) Integer categoryId,
	    @ApiParam(required = false, value = "The day.") @RequestParam(required = false) DayOfWeek day) throws AppointmentBookingException {
	log.info("OpeningHoursService.get() called with: categoryId=" + categoryId + ", day=" + day + ".");

	Map<DayOfWeek, OpeningHoursDTO> openingHourses = new EnumMap<>(DayOfWeek.class);
	if (day != null) {
	    openingHourses.put(day, openingHoursController.getOpeningHours(categoryId, day));
	} else {
	    for (DayOfWeek currentDay : DayOfWeek.values()) {
		try {
		    openingHourses.put(currentDay, openingHoursController.getOpeningHours(categoryId, currentDay));
		} catch (AppointmentBookingException e) {
		    log.trace(e.getMessage());
		}
	    }
	}

	log.info("OpeningHoursService.get() returned with: " + openingHourses.toString());
	return ResponseEntity.ok(openingHourses);
    }

}
