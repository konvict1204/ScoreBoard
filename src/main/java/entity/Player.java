package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column(unique=true)
    String name;
}
