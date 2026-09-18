package project.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    int id;

    @JoinColumn(name = "name")
    String name;

    public PlayerEntity(String name) {
        this.name = name;
    }
}
