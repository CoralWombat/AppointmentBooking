package dev.coralwombat.appointment.booking.clientservice.repository;

import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Log4j2
@Repository
public class CategoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Runs a select to retrieve the Category with the given id.
     *
     * @param id - The id of the category that is looked for.
     * @return The CategoryDTO with the id from the input.
     * @throws NoResultException when there is no Category with the given id.
     */
    public CategoryDTO getCategory(@NonNull Integer id) throws NoResultException {
        Category category = entityManager.find(Category.class, id);
        if (category == null) throw new NoResultException("Category not found for id: " + id);
        return new CategoryDTO(category);
    }

}
