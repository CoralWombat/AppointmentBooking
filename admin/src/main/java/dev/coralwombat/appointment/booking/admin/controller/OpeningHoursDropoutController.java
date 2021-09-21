package dev.coralwombat.appointment.booking.admin.controller;

import dev.coralwombat.appointment.booking.admin.repository.IOpeningHoursDropoutRepository;
import dev.coralwombat.appointment.booking.dto.OpeningHoursDropoutDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Controller
public class OpeningHoursDropoutController implements IOpeningHoursDropoutController {

    IOpeningHoursDropoutRepository openingHoursDropoutRepository;

    @Autowired
    public OpeningHoursDropoutController(IOpeningHoursDropoutRepository openingHoursDropoutRepository) {
        this.openingHoursDropoutRepository = openingHoursDropoutRepository;
    }

    @Override
    public List<OpeningHoursDropoutDTO> getOpeningHoursDropout(Integer categoryId, LocalDate fromDate) {
        return openingHoursDropoutRepository.getOpeningHoursDropout(categoryId, fromDate);
    }
}
