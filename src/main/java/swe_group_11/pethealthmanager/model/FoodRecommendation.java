package swe_group_11.pethealthmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "food_recommendations")
public class FoodRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이전에 petId 필드를 사용했던 부분을 Pet 엔티티와의 직접적인 관계로 변경
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(nullable = false)
    private String recommendation;

    // @Column(name = "pet_id", insertable = false, updatable = false)
    // private Long petId;
}