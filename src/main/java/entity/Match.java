package entity;

import jakarta.persistence.*;

public class Match {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="player_id")
    Player player1;

    @OneToOne
    @JoinColumn(name="player_id")
    Player player2;

    @OneToOne
    @JoinColumn(name="player_id")
    Player winner;
}
