package dev.coralwombat.appointment.booking.admin.repository;

import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import lombok.NonNull;

import javax.persistence.NoResultException;
import java.util.List;

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

    /**
     * Retrieves all categories from the datasource.
     *
     * @return All categories in a {@link List}.
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Retrieves the main categories from the datasource.
     *
     * @return The main categories in a {@link List}.
     */
    List<CategoryDTO> getMainCategories();

    /**
     * Retrieves the categories that has the given parentId from the datasource.
     *
     * @param parentId - The parent of the categories.
     * @return The categories that's parent is the input parentId in a {@link List}.
     */
    List<CategoryDTO> getChildCategories(@NonNull Integer parentId);

}
