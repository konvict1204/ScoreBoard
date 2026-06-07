package repository;

import entity.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerRepoITTest extends PlayerRepoBaseITTest {
    PlayerRepo playerRepo;

    @Test
    void getMatchesTest(){
        playerRepo = PlayerRepo.getInstance();
        Player player = new Player();
        player.setName("Player1");

        Integer persist = playerRepo.persist(player);

        assertThat(persist).isEqualTo(1);
    }
}
