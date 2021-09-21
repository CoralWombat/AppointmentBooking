package dev.coralwombat.appointment.booking.admin.rest;

import dev.coralwombat.appointment.booking.admin.controller.IOpeningHoursController;
import dev.coralwombat.appointment.booking.admin.exception.AppointmentBookingException;
import dev.coralwombat.appointment.booking.dto.OpeningHoursDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.DayOfWeek;

@Log4j2
@RestController
@RequestMapping("/openingHours")
public class OpeningHoursService {

    IOpeningHoursController openingHoursController;

    public OpeningHoursService(IOpeningHoursController openingHoursController) {
        this.openingHoursController = openingHoursController;
    }

    @Transactional
    @PutMapping(path = "/put")
    @ApiOperation(value = "Puts an opening hour into the database",
            notes = "Puts the given opening hour into the database. If the opening hour already exists it updates it.")
    public ResponseEntity<Object> put(@ApiParam(required = true, value = "The opening hours to insert or update.") @RequestBody(required = true) OpeningHoursDTO openingHours) {
        log.info("OpeningHoursService.put() called with: openingHours=" + openingHours.toString() + ".");
        HttpStatus status = HttpStatus.OK;

        try {
            openingHours = openingHoursController.getOpeningHours(openingHours.getCategory(), openingHours.getDay());
            if (openingHours == null) {
                status = HttpStatus.CREATED;
                openingHours = new OpeningHoursDTO();
            }
        } catch (AppointmentBookingException e) {
            status = HttpStatus.CREATED;
        }

        openingHoursController.saveOpeningHours(openingHours);

        log.info("OpeningHoursService.put() finished.");
        return ResponseEntity.status(status).build();
    }

    @Transactional
    @DeleteMapping(path = "/delete")
    @ApiOperation(value = "Deletes an opening hour",
            notes = "Deletes the given opening hour from the database.")
    public ResponseEntity<Object> delete(@ApiParam(required = true, value = "The ID of the category to delete.") @RequestParam(required = true) Integer categoryId,
                                         @ApiParam(required = true, value = "The day of the opening hours to delete.") @RequestParam(required = true) DayOfWeek day) {
        log.info("OpeningHoursService.delete() called with: categoryId=" + categoryId + ", day=" + day);

        openingHoursController.deleteOpeningHours(categoryId, day);

        log.info("OpeningHoursService.delete() finished.");
        return ResponseEntity.ok().build();
    }

}
