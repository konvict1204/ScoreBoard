package project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.entity.Player;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchDto {
    int id;
    Player player1;
    Player player2;


}
