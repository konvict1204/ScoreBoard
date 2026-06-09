package project.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    public Player(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column(unique=true)
    String name;
}
