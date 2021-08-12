package dev.coralwombat.appointment.booking.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "opening_hours_exception")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OpeningHoursDropout implements Serializable {

    private static final long serialVersionUID = -1592489058864784896L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @Column(name = "from_date")
    LocalDateTime from;

    @Column(name = "to_date")
    LocalDateTime to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OpeningHoursDropout that = (OpeningHoursDropout) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 2068549604;
    }
}
