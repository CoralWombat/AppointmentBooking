package dev.coralwombat.appointment.booking.admin.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@ApiOperation(value = "Puts an opening hour into the database", notes = "Puts the given opening hour into the database. If the opening hour already exists it updates it.")
	public ResponseEntity<Object> put(
			@ApiParam(required = true, value = "The opening hours to insert or update.") @RequestBody(required = true) OpeningHoursDTO openingHours) {
		log.info("OpeningHoursService.put() called with: openingHours=" + openingHours.toString() + ".");
		HttpStatus status = HttpStatus.OK;

		var id = new OpeningHours();
		id.setCategory(entityManager.find(Category.class, openingHours.getCategory()));
		id.setDay(openingHours.getDay());
		var dbOpeningHours = entityManager.find(OpeningHours.class, id);
		if (dbOpeningHours == null) {
			status = HttpStatus.CREATED;
		}

		var entityOpeningHours = new OpeningHours();
		entityOpeningHours.setCategory(entityManager.find(Category.class, openingHours.getCategory()));
		entityOpeningHours.setDay(openingHours.getDay());
		entityOpeningHours.setFrom(openingHours.getFrom());
		entityOpeningHours.setTo(openingHours.getTo());
		entityManager.merge(entityOpeningHours);

		log.info("OpeningHoursService.put() finished.");
		return ResponseEntity.status(status).build();
	}

}
