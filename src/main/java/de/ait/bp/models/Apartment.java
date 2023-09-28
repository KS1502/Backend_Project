package de.ait.bp.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(of = {"id"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "apartment")
public class Apartment {

    public enum State {
        CONFIRMED,
        DELETED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private State state;

    @Column(nullable = false)
    private int numberOfRooms;

    @OneToMany(mappedBy = "apartment")
    private List<Booking> bookings;

    @ManyToMany(mappedBy = "apartments")
    private Set<User> persons;
}
