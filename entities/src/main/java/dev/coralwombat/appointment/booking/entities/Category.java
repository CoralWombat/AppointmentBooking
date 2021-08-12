package dev.coralwombat.appointment.booking.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = -5229059936348972714L;

    @Id
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "length")
    Integer length;

    @JoinColumn(name = "parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Category parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;

        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return 1596826009;
    }
}
