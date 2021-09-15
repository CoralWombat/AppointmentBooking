package dev.coralwombat.appointment.booking.clientservice.rest;

import dev.coralwombat.appointment.booking.clientservice.bean.IOpeningHoursDropoutController;
import dev.coralwombat.appointment.booking.dto.OpeningHoursDropoutDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@Log4j2
@RestController
@RequestMapping("/openingHoursDropout")
public class OpeningHoursDropoutService {

    IOpeningHoursDropoutController openingHoursDropoutController;

    @Autowired
    public OpeningHoursDropoutService(IOpeningHoursDropoutController openingHoursDropoutController) {
        this.openingHoursDropoutController = openingHoursDropoutController;
    }

    @GetMapping(path = "/get")
    @ApiOperation(value = "Gets all exceptions of a category.",
            notes = "Retrieves all opening hours exceptions of a category and its parents.")
    public ResponseEntity<Collection<OpeningHoursDropoutDTO>> get(@ApiParam(required = true, value = "The identifier of the category.") @RequestParam(required = true) Integer categoryId,
                                                                  @ApiParam(required = false, value = "The date from where to look up.") @RequestParam(required = false) LocalDate fromDate) {
        log.info("OpeningHoursExceptionService.get() called with: categoryId=" + categoryId + ", fromDate=" + fromDate + ".");
        Collection<OpeningHoursDropoutDTO> exceptions = openingHoursDropoutController.getOpeningHoursDropout(categoryId, fromDate);

        log.info("OpeningHoursExceptionService.get() returned with: " + exceptions.toString() + ".");
        return ResponseEntity.ok(exceptions);
    }

}
