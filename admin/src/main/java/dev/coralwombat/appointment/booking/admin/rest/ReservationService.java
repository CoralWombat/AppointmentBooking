package dev.coralwombat.appointment.booking.admin.rest;

import dev.coralwombat.appointment.booking.admin.controller.IReservationController;
import dev.coralwombat.appointment.booking.dto.ReservationDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.LinkedList;

@Log4j2
@RestController
@RequestMapping("/reservation")
public class ReservationService {

    IReservationController reservationController;

    @Autowired
    public ReservationService(IReservationController reservationController) {
        this.reservationController = reservationController;
    }

    @GetMapping(path = "/get")
    @ApiOperation(value = "Gets the reservations by a category id.",
            notes = "Retrieves all reservations for the given category and it's children. If the categoryId is null, returns all reservations.")
    public ResponseEntity<Collection<ReservationDTO>> get(@ApiParam(required = false, value = "The ID of the category for the reservations.") @RequestParam(required = false) Integer categoryId) {
        log.info("ReservationService.get() called with: categoryId=" + categoryId);
        Collection<ReservationDTO> reservations = new LinkedList<>();

        if (categoryId == null) {
            reservations.addAll(reservationController.getAllReservations());
        } else {
            reservations.addAll(reservationController.getReservations(categoryId));
        }

        log.info("ReservationService.get() returned with: " + reservations);
        return ResponseEntity.ok(reservations);
    }

    @Transactional
    @DeleteMapping(path = "/delete")
    @ApiOperation(value = "Deletes a reservation.",
            notes = "Deletes a reservation from the database.")
    public ResponseEntity<Object> delete(@ApiParam(required = true, value = "The ID of the reservation to delete.") @RequestParam(required = true) Integer reservationId) {
        log.info("ReservationService.delete() called with: reservationId=" + reservationId + ".");

        reservationController.deleteReservation(reservationId);

        log.info("ReservationService.delete() finished.");
        return ResponseEntity.ok().build();
    }

}
