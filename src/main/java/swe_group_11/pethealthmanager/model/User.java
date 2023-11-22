package swe_group_11.pethealthmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;



    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true) // 없어지면 pet도 삭제
    private List<Pet> pets;

}

