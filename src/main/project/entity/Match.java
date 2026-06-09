package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @OneToOne
    Player player1;

    @OneToOne
    Player player2;

    @OneToOne
    Player winner;
}
