package dev.coralwombat.appointment.booking.admin.rest;

import java.time.DayOfWeek;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.coralwombat.appointment.booking.dto.OpeningHoursDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import dev.coralwombat.appointment.booking.entities.OpeningHours;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/openingHours")
public class OpeningHoursService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PutMapping(path = "/put")
    @ApiOperation(value = "Puts an opening hour into the database",
	    notes = "Puts the given opening hour into the database. If the opening hour already exists it updates it.")
    public ResponseEntity<Object> put(@ApiParam(required = true, value = "The opening hours to insert or update.") @RequestBody(required = true) OpeningHoursDTO openingHours) {
	log.info("OpeningHoursService.put() called with: openingHours=" + openingHours.toString() + ".");
	HttpStatus status = HttpStatus.OK;

	var id = new OpeningHours();
	id.setCategory(entityManager.find(Category.class, openingHours.getCategory()));
	id.setDay(openingHours.getDay());
	var dbOpeningHours = entityManager.find(OpeningHours.class, id);
	if (dbOpeningHours == null) {
	    status = HttpStatus.CREATED;
	    dbOpeningHours = new OpeningHours();
	}

	dbOpeningHours.setCategory(entityManager.find(Category.class, openingHours.getCategory()));
	dbOpeningHours.setDay(openingHours.getDay());
	dbOpeningHours.setFrom(openingHours.getFrom());
	dbOpeningHours.setTo(openingHours.getTo());
	entityManager.persist(dbOpeningHours);

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

	entityManager.createQuery("DELETE FROM OpeningHours o WHERE o.category = :category AND o.day = :day")
		.setParameter("category", entityManager.find(Category.class, categoryId))
		.setParameter("day", day)
		.executeUpdate();

	log.info("OpeningHoursService.delete() finished.");
	return ResponseEntity.ok().build();
    }

}
