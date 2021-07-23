package dev.coralwombat.appointment.booking.dto;

import dev.coralwombat.appointment.booking.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

	public CategoryDTO(@NonNull Category importEntity) {
		this.id = importEntity.getId();
		this.name = importEntity.getName();
		this.parent = importEntity.getParent() != null ? importEntity.getParent().getId() : null;
	}

	@NonNull
	Integer id;

	String name;

	Integer parent;

}
