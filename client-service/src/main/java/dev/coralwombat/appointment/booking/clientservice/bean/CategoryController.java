package dev.coralwombat.appointment.booking.clientservice.bean;

import dev.coralwombat.appointment.booking.clientservice.repository.ICategoryRepository;
import dev.coralwombat.appointment.booking.dto.CategoryDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Log4j2
@Controller
public class CategoryController implements ICategoryController {

    ICategoryRepository categoryRepository;

    @Autowired
    public CategoryController(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getChildCategories(Integer parentId) {
        return parentId != null ? categoryRepository.getChildCategories(parentId) : categoryRepository.getMainCategories();
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.getAllCategories();
    }
}
