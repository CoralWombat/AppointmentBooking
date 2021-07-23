package dev.coralwombat.appointment.booking.clientservice.rest;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import dev.coralwombat.appointment.booking.entities.Category;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/category")
public class CategoryService {

	@PersistenceContext
	EntityManager entityManager;

	@GetMapping(path = "/getAll")
	@ApiOperation(value = "Gets all categories", notes = "Retrieves all categories from the database.")
	public ResponseEntity<Collection<CategoryDTO>> getAll() {
		log.info("CategoryService.getAll() called.");

		Collection<Category> entityCategories = entityManager.createQuery("FROM Category c", Category.class)
				.getResultList();

		Collection<CategoryDTO> categories = new LinkedList<>();
		for (Category entityCategory : entityCategories) {
			categories.add(new CategoryDTO(entityCategory));
		}

		log.info("CategoryService.getAll() returned with: " + categories.toString());
		return ResponseEntity.ok(categories);
	}

	@GetMapping(path = "/getMain")
	@ApiOperation(value = "Gets all main categories", notes = "Retrieves all main categories from the database.")
	public ResponseEntity<Collection<CategoryDTO>> getMain() {
		log.info("CategoryService.getMain() called.");

		Collection<Category> entityCategories = entityManager
				.createQuery("SELECT c FROM Category c WHERE c.parent = null", Category.class).getResultList();

		Collection<CategoryDTO> categories = new LinkedList<>();
		for (Category entityCategory : entityCategories) {
			categories.add(new CategoryDTO(entityCategory));
		}

		log.info("CategoryService.getMain() returned with: " + categories.toString());
		return ResponseEntity.ok(categories);
	}

	@ApiOperation(value = "Gets all child categories of the given parent", notes = "Retrieves all child categories of the parent category from the database.")
	@GetMapping(path = "/getChildren")
	public ResponseEntity<Collection<CategoryDTO>> getChildren(
			@ApiParam(required = true, value = "The ID of the parent category.") @RequestParam(required = true) Integer parentId) {
		log.info("CategoryService.getChildren() called with: parentId=" + parentId);

		var parent = entityManager.find(Category.class, parentId);
		if (parent == null) {
			log.error("Could not find parent. id=" + parent);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find parent. id=" + parent);
		}

		Collection<Category> entityCategories = entityManager
				.createQuery("SELECT c FROM Category c WHERE c.parent = :parent", Category.class)
				.setParameter("parent", parent).getResultList();

		Collection<CategoryDTO> categories = new LinkedList<>();
		for (Category entityCategory : entityCategories) {
			categories.add(new CategoryDTO(entityCategory));
		}

		log.info("CategoryService.getChildren() returned with: " + categories.toString());
		return ResponseEntity.ok(categories);
	}

}
