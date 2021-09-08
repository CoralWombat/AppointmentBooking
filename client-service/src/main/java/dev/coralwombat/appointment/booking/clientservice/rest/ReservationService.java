package dev.coralwombat.appointment.booking.clientservice.rest;

import dev.coralwombat.appointment.booking.clientservice.bean.ICategoryController;
import dev.coralwombat.appointment.booking.clientservice.bean.IReservationController;
import dev.coralwombat.appointment.booking.dto.ReservationDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Log4j2
@RestController
@RequestMapping("/reservation")
public class ReservationService {

    IReservationController reservationController;

    ICategoryController categoryController;

    @Autowired
    public ReservationService(IReservationController reservationController, ICategoryController categoryController) {
        this.reservationController = reservationController;
        this.categoryController = categoryController;
    }

    @Transactional
    @PostMapping(path = "/post")
    @ApiOperation(value = "Puts a reservation into the database",
            notes = "Puts the given reservation into the database.")
    public ResponseEntity<Object> post(@ApiParam(required = true, value = "The reservation to insert.") @RequestBody(required = true) ReservationDTO reservation) {
        log.info("ReservationService.post called with: reservation=" + reservation + ".");

        try {
            categoryController.getCategory(reservation.getCategory());
        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        reservationController.insertReservation(reservation);

        log.info("ReservationService.post finished.");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
