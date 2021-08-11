package dev.coralwombat.appointment.booking.dto;

import java.time.LocalDateTime;

import dev.coralwombat.appointment.booking.entities.Reservation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Contains all information about a reservation.")
public class ReservationDTO {

    public ReservationDTO(Reservation importEntity) {
        this.id = importEntity.getId();
        this.category = importEntity.getCategory() != null ? importEntity.getCategory().getId() : null;
        this.from = importEntity.getFrom();
        this.to = importEntity.getTo();
        this.customerId = importEntity.getCustomerId();
    }

    @ApiModelProperty(value = "The identifier of the reservation.")
    Integer id;

    @ApiModelProperty("The identifier of the category that is reserved.")
    Integer category;

    @NonNull
    @ApiModelProperty("The time that the reservation is valid from.")
    LocalDateTime from;

    @NonNull
    @ApiModelProperty("The time that the reservation is valid to.")
    LocalDateTime to;

    @NonNull
    @ApiModelProperty("The identifier of the customer that made the reservation.")
    String customerId;

}
