package dev.coralwombat.appointment.booking.dto;

import dev.coralwombat.appointment.booking.entities.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains the details of a category.")
public class CategoryDTO {

    public CategoryDTO(@NonNull Category importEntity) {
        this.id = importEntity.getId();
        this.name = importEntity.getName();
        this.parent = importEntity.getParent() != null ? importEntity.getParent().getId() : null;
    }

    @NonNull
    @ApiModelProperty(notes = "The ID of the category.")
    Integer id;

    @ApiModelProperty(notes = "The name of the category.")
    String name;

    @ApiModelProperty(notes = "The time length of the category (service) in minutes.")
    Integer lenght;

    @ApiModelProperty(notes = "The ID of the parent of the category.")
    Integer parent;

}
