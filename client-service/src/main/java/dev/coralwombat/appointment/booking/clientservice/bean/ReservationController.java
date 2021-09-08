package dev.coralwombat.appointment.booking.clientservice.bean;

import dev.coralwombat.appointment.booking.clientservice.repository.IReservationRepository;
import dev.coralwombat.appointment.booking.dto.ReservationDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller
public class ReservationController implements IReservationController {

    private IReservationRepository reservationRepository;

    @Autowired
    public ReservationController(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void insertReservation(ReservationDTO reservationDTO) {
        reservationRepository.insertReservation(reservationDTO);
    }
}
