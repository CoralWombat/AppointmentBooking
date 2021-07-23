package dev.coralwombat.appointment.booking.admin.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/category")
public class CategoryService {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	@PutMapping(path = "/put")
	public ResponseEntity<Object> put(@RequestBody(required = true) CategoryDTO category) {
		log.info("CategoryService.put() called with: category=" + category.toString() + ".");
		HttpStatus status = HttpStatus.OK;

		var dbCategory = entityManager.find(Category.class, category.getId());
		if (dbCategory == null) {
			status = HttpStatus.CREATED;
		}

		var entityCategory = new Category();
		entityCategory.setId(category.getId());
		entityCategory.setName(category.getName());
		entityCategory.setParent(entityManager.find(Category.class, category.getParent()));
		entityManager.merge(entityCategory);

		log.info("CategoryService.put() finished.");
		return ResponseEntity.status(status).build();
	}

	@Transactional
	@DeleteMapping(path = "/delete")
	public void delete(@RequestParam(required = true) Integer id) {
		log.info("CategoryService.delete() called with: id=" + id);
		boolean hasChildren = entityManager
				.createQuery("SELECT c FROM Category c WHERE c.parent = :parentId", Category.class)
				.setParameter("parentId", entityManager.find(Category.class, id)).getResultList().isEmpty();
		if (hasChildren)
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Children found. id=" + id);

		entityManager.createQuery("DELETE FROM Category c WHERE c.id = :id").setParameter("id", id).executeUpdate();
		log.info("CategoryService.delete() finished.");
	}

}
