package dev.coralwombat.appointment.booking.admin.controller;

import dev.coralwombat.appointment.booking.admin.repository.ICategoryRepository;
import dev.coralwombat.appointment.booking.admin.repository.IReservationRepository;
import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import dev.coralwombat.appointment.booking.dto.ReservationDTO;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;

@Log4j2
@Controller
public class ReservationController implements IReservationController {

    ICategoryRepository categoryRepository;

    IReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ICategoryRepository categoryRepository, IReservationRepository reservationRepository) {
        this.categoryRepository = categoryRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationDTO> getReservations(@NonNull Integer categoryId) {
        List<ReservationDTO> reservations = new LinkedList<>();

        List<CategoryDTO> unprocessedCategories = new LinkedList<>();
        unprocessedCategories.add(categoryRepository.getCategory(categoryId));
        while (!unprocessedCategories.isEmpty()) {
            CategoryDTO currentCategory = unprocessedCategories.stream().findFirst().orElse(new CategoryDTO());
            unprocessedCategories.remove(currentCategory);

            unprocessedCategories.addAll(categoryRepository.getChildCategories(currentCategory.getId()));

            reservations.addAll(reservationRepository.getReservations(currentCategory.getId()));
        }

        return reservations;
    }

    @Override
    public void deleteReservation(Integer reservationId) {
        reservationRepository.deleteReservation(reservationId);
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

}
