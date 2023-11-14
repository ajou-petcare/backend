package swe_group_11.pethealthmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;


}
