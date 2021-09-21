package dev.coralwombat.appointment.booking.clientservice.rest;

import dev.coralwombat.appointment.booking.clientservice.controller.ICategoryController;
import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Log4j2
@RestController
@RequestMapping("/category")
public class CategoryService {

    ICategoryController categoryController;

    @Autowired
    public CategoryService(ICategoryController categoryController) {
        this.categoryController = categoryController;
    }

    @GetMapping(path = "/getAll")
    @ApiOperation(value = "Gets all categories",
            notes = "Retrieves all categories from the database.")
    public ResponseEntity<Collection<CategoryDTO>> getAll() {
        log.info("CategoryService.getAll() called.");

        Collection<CategoryDTO> categories = categoryController.getAllCategories();

        log.info("CategoryService.getAll() returned with: " + categories.toString());
        return ResponseEntity.ok(categories);
    }

    @GetMapping(path = "/getMain")
    @ApiOperation(value = "Gets all main categories",
            notes = "Retrieves all main categories from the database.")
    public ResponseEntity<Collection<CategoryDTO>> getMain() {
        log.info("CategoryService.getMain() called.");

        Collection<CategoryDTO> categories = categoryController.getChildCategories(null);

        log.info("CategoryService.getMain() returned with: " + categories.toString());
        return ResponseEntity.ok(categories);
    }

    @ApiOperation(value = "Gets all child categories of the given parent",
            notes = "Retrieves all child categories of the parent category from the database.")
    @GetMapping(path = "/getChildren")
    public ResponseEntity<Collection<CategoryDTO>> getChildren(@ApiParam(required = true, value = "The ID of the parent category.") @RequestParam(required = true) Integer parentId) {
        log.info("CategoryService.getChildren() called with: parentId=" + parentId);

        Collection<CategoryDTO> categories = categoryController.getChildCategories(parentId);

        log.info("CategoryService.getChildren() returned with: " + categories.toString());
        return ResponseEntity.ok(categories);
    }

}
