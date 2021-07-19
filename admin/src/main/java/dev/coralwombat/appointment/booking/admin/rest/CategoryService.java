package dev.coralwombat.appointment.booking.admin.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Object> put(@RequestParam(required = false) Integer id,
			@RequestParam(required = true) String name, @RequestParam(required = false) Integer parent) {
		log.info("CategoryService.put() called with: id=" + id + ", name=" + name + ", parent=" + parent + ".");
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);

		var category = entityManager.find(Category.class, id);
		if (category == null) {
			category = new Category();
			response = new ResponseEntity<>(HttpStatus.CREATED);
		}

		category.setId(id);
		category.setName(name);
		if (parent != null) {
			var parentCategory = entityManager.find(Category.class, parent);
			if (parentCategory == null) {
				log.error("Could not find parent. id=" + parent);
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			category.setParent(parentCategory);
		}
		entityManager.merge(category);

		log.info("CategoryService.put() finished.");
		return response;
	}

	@Transactional
	@DeleteMapping(path = "/delete")
	public void delete(@RequestParam(required = true) Integer id) {
		log.info("CategoryService.delete() called with: id=" + id);
		entityManager.createQuery("DELETE FROM Category c WHERE c.id = :id").setParameter("id", id).executeUpdate();
		log.info("CategoryService.delete() finished.");
	}

}
