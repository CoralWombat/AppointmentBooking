package dev.coralwombat.appointment.booking.clientservice.bean;

import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import dev.coralwombat.appointment.booking.entities.Category;

import java.util.List;

/**
 * Contains every operations related to {@link dev.coralwombat.appointment.booking.entities.Category}.
 */
public interface ICategoryController {

    /**
     * Collects the direct child categories of a parent. If the parentId is null, then it looks up the main categories.
     *
     * @param parentId - The id of the parent of the categories.
     * @return The child categories of the parent in a {@link List}.
     */
    List<CategoryDTO> getChildCategories(Integer parentId);

    /**
     * Collects all categories.
     *
     * @return All categories in a {@link List}.
     */
    List<CategoryDTO> getAllCategories();

}
