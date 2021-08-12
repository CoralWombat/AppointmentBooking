package dev.coralwombat.appointment.booking.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "opening_hours")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OpeningHours implements Serializable {

    private static final long serialVersionUID = -8928316118076792489L;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Id
    @Column(name = "day")
    DayOfWeek day;

    @Column(name = "from_time")
    LocalTime from;

    @Column(name = "to_time")
    LocalTime to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OpeningHours that = (OpeningHours) o;

        return Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return 1889357066;
    }
}
