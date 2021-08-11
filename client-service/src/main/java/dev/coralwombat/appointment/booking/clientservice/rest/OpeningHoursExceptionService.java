package dev.coralwombat.appointment.booking.clientservice.rest;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(path = "/get")
    @ApiOperation(value = "Gets all exceptions of a category.",
            notes = "Retrieves all opening hours exceptions of a category and its parents.")
    public ResponseEntity<Collection<OpeningHoursDropoutDTO>> get(@ApiParam(required = true, value = "The identifier of the category.") @RequestParam(required = true) Integer categoryId) {
        log.info("OpeningHoursExceptionService.get() called with: categoryId=" + categoryId + ".");
        Collection<OpeningHoursDropoutDTO> exceptions = new LinkedList<>();

        var category = entityManager.find(Category.class, categoryId);
        while (category != null) {
            List<OpeningHoursDropout> dbExceptions = entityManager.createQuery("SELECT o FROM OpeningHoursDropout o WHERE o.category = :category", OpeningHoursDropout.class)
                    .setParameter("category", category)
                    .getResultList();

            for (OpeningHoursDropout dbException : dbExceptions) {
                exceptions.add(new OpeningHoursDropoutDTO(dbException));
            }

            category = category.getParent() != null ? entityManager.find(Category.class, category.getParent().getId()) : null;
        }

        log.info("OpeningHoursExceptionService.get() returned with: " + exceptions.toString() + ".");
        return ResponseEntity.ok(exceptions);
    }

}
