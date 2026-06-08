import entity.Player;
import repository.MatchRepo;
import repository.PlayerRepo;

public class Start {
    public static void main(String[] args) {
        PlayerRepo repo = PlayerRepo.getInstance();
        Player player = repo.findById("1").get();

        System.out.println(player);
    }
}
