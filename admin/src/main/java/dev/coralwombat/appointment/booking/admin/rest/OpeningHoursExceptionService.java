package dev.coralwombat.appointment.booking.admin.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.coralwombat.appointment.booking.dto.OpeningHoursDropoutDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import dev.coralwombat.appointment.booking.entities.OpeningHoursDropout;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/openingHoursException")
public class OpeningHoursExceptionService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping(path = "/post")
    @ApiOperation(value = "Puts an opening hour exception into the database",
            notes = "Puts the given opening hour into the database.")
    public ResponseEntity<Object> post(@ApiParam(required = true, value = "The opening hours exception to insert.") @RequestBody(required = true) OpeningHoursDropoutDTO openingHoursException) {
        log.info("OpeningHoursExceptionService.post() called with: openingHoursException=" + openingHoursException.toString() + ".");

        var dbOpeningHoursException = new OpeningHoursDropout();
        dbOpeningHoursException.setCategory(entityManager.find(Category.class, openingHoursException.getCategoryId()));
        dbOpeningHoursException.setFrom(openingHoursException.getFrom());
        dbOpeningHoursException.setTo(openingHoursException.getTo());
        entityManager.persist(dbOpeningHoursException);

        log.info("OpeningHoursExceptionService.post() finished.");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @DeleteMapping(path = "/delete")
    @ApiOperation(value = "Deletes an opening hour exception from the database",
            notes = "Deletes the given opening hour from the database.")
    public ResponseEntity<Object> delete(@ApiParam(required = true, value = "The id of the exception to delete.") @RequestParam(required = true) Integer id) {
        log.info("OpeningHoursExceptionService.delete() called with: id=" + id + ".");

        entityManager.createQuery("DELETE FROM OpeningHoursDropout o WHERE o.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        log.info("OpeningHoursExceptionService.delete() finished.");
        return ResponseEntity.ok().build();
    }

}
