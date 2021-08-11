package dev.coralwombat.appointment.booking.dto;

import java.time.LocalDateTime;

import dev.coralwombat.appointment.booking.entities.OpeningHoursException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains an exception in the opening hours of a category.")
public class OpeningHoursExceptionDTO {

    public OpeningHoursExceptionDTO(OpeningHoursException importEntity) {
        this.id = importEntity.getId();
        this.categoryId = importEntity.getCategory() != null ? importEntity.getCategory().getId() : null;
        this.from = importEntity.getFrom();
        this.to = importEntity.getTo();
    }

    @ApiModelProperty(value = "The identifier of the exception.")
    Integer id;

    @ApiModelProperty(value = "The identifier of the category that the exception belongs to.")
    Integer categoryId;

    @NonNull
    @ApiModelProperty(value = "The date that shows when the given category shall not be available from.")
    LocalDateTime from;

    @NonNull
    @ApiModelProperty(value = "The date that shows when the given category shall not be available to.")
    LocalDateTime to;

}
