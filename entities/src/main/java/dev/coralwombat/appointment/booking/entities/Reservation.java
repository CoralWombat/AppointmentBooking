package dev.coralwombat.appointment.booking.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1734552087612247006L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @Column(name = "from_time")
    LocalDateTime from;

    @Column(name = "to_time")
    LocalDateTime to;

    @Column(name = "customer_id")
    @Nationalized
    String customerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reservation that = (Reservation) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1851714121;
    }
}
