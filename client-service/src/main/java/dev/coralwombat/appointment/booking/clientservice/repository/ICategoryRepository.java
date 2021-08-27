package dev.coralwombat.appointment.booking.clientservice.repository;

import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import lombok.NonNull;

import javax.persistence.NoResultException;

/**
 * Bean that handles database operations related to the {@link dev.coralwombat.appointment.booking.entities.Category}.
 */
public interface ICategoryRepository {

    /**
     * Looks for a Category in the datasource.
     *
     * @param id - The id of the category that is looked for.
     * @return The CategoryDTO with the id from the input.
     * @throws NoResultException when there is no Category with the given id.
     */
    CategoryDTO getCategory(@NonNull Integer id) throws NoResultException;

}
