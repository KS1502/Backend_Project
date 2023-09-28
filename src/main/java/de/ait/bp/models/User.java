package de.ait.bp.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;



@EqualsAndHashCode(of = {"id", "email"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    public enum Role {
        ADMIN,
        MANAGER,
        GUEST
    }

    public enum State {
        CONFIRMED,
        NOT_CONFIRMED,
        BANNED,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashPassword;

    @ManyToMany
    @JoinTable(name = "person_apartment",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "apartment_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "apartment_id"}))
    private Set<Apartment> apartments;

}
