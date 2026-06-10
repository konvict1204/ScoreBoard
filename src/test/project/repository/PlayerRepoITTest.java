package project.repository;


import org.junit.jupiter.api.Test;
import project.entity.Player;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerRepoITTest extends PlayerRepoBaseITTest {
    PlayerRepo playerRepo;


    @Test
    void findByName(){
        Player player = new Player("Player");
        playerRepo = PlayerRepo.getInstance();
        playerRepo.persist(player);

        Optional<Player> maybePlayer = playerRepo.findByName(player.getName());

        assertThat(maybePlayer).isPresent();
        assertThat(maybePlayer.get().getName()).isEqualTo(player.getName());

    }

    @Test
    void persistHP(){
        Player player = new Player("Player");
        playerRepo = PlayerRepo.getInstance();

        playerRepo.persist(player);

        assertThat(player.getId()).isEqualTo(1);
    }
}
