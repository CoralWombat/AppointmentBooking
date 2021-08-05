package dev.coralwombat.appointment.booking.clientservice.rest;

import dev.coralwombat.appointment.booking.dto.ReservationDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import dev.coralwombat.appointment.booking.entities.Reservation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Log4j2
@RestController
@RequestMapping("/reservation")
public class ReservationService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping(path = "/post")
    @ApiOperation(value = "Puts a reservation into the database",
            notes = "Puts the given reservation into the database.")
    public ResponseEntity<Object> post(@ApiParam(required = true, value = "The reservation to insert.") @RequestBody(required = true) ReservationDTO reservation) {
        log.info("ReservationService.post called with: reservation=" + reservation + ".");

        Category category = entityManager.find(Category.class, reservation.getCategory());
        if (category == null) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        var entityReservation = new Reservation();
        entityReservation.setId(reservation.getId());
        entityReservation.setCategory(category);
        entityReservation.setCustomerId(reservation.getCustomerId());
        entityReservation.setFrom(reservation.getFrom());
        entityReservation.setTo(reservation.getTo());
        entityManager.persist(entityReservation);

        log.info("ReservationService.post finished.");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
