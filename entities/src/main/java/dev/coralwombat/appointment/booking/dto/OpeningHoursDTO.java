package dev.coralwombat.appointment.booking.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import dev.coralwombat.appointment.booking.entities.OpeningHours;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains the opening hours of a category on a day of the week.")
public class OpeningHoursDTO {

	public OpeningHoursDTO(OpeningHours importEntity) {
		this.category = importEntity.getCategory() != null ? importEntity.getCategory().getId() : null;
		this.day = importEntity.getDay();
		this.from = importEntity.getFrom();
		this.to = importEntity.getTo();
	}

	@ApiModelProperty(value = "The ID of the category.")
	Integer category;

	@NonNull
	@ApiModelProperty(value = "The day of the week.")
	DayOfWeek day;

	@NonNull
	@ApiModelProperty(value = "The hour from which the opening is active.")
	LocalTime from;

	@NonNull
	@ApiModelProperty(value = "The hour from which the opening is inactive.")
	LocalTime to;

}
