package dev.coralwombat.appointment.booking.admin.repository;

import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Log4j2
@Repository
public class CategoryRepository implements ICategoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CategoryDTO getCategory(@NonNull Integer id) throws NoResultException {
        Category category = entityManager.find(Category.class, id);
        if (category == null) throw new NoResultException("Category not found for id: " + id);
        return new CategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        Collection<Category> entityCategories = entityManager
                .createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();

        List<CategoryDTO> categories = new LinkedList<>();
        for (Category entityCategory : entityCategories) {
            categories.add(new CategoryDTO(entityCategory));
        }

        return categories;
    }

    @Override
    public List<CategoryDTO> getMainCategories() {
        Collection<Category> entityCategories = entityManager
                .createQuery("SELECT c FROM Category c WHERE c.parent = null", Category.class)
                .getResultList();

        List<CategoryDTO> categories = new LinkedList<>();
        for (Category entityCategory : entityCategories) {
            categories.add(new CategoryDTO(entityCategory));
        }

        return categories;
    }

    @Override
    public List<CategoryDTO> getChildCategories(@NonNull Integer parentId) {
        var parent = entityManager.find(Category.class, parentId);
        if (parent == null) {
            log.error("Could not find parent. id=" + parentId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find parent. id=" + parentId);
        }

        Collection<Category> entityCategories = entityManager
                .createQuery("SELECT c FROM Category c WHERE c.parent = :parent", Category.class)
                .setParameter("parent", parent)
                .getResultList();

        List<CategoryDTO> categories = new LinkedList<>();
        for (Category entityCategory : entityCategories) {
            categories.add(new CategoryDTO(entityCategory));
        }

        return categories;
    }

}
