package project.service;

import org.hibernate.Session;
import project.dto.MatchDto;

import project.dto.MatchResponseDto;
import project.dto.MatchesResponseDto;
import project.dto.PlayerScoreDto;
import project.entity.MatchEntity;
import project.entity.PlayerEntity;
import project.exceptions.MatchDoesntExist;
import project.match.Match;
import project.match.Player;
import project.repository.MatchRepo;
import project.repository.PlayerRepo;
import project.util.PoolManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MatchService {

    Map<String, Match> matches = new HashMap<>();
    MatchRepo matchRepo;
    PlayerRepo playerRepo;

    public MatchService(MatchRepo matchRepo, PlayerRepo playerRepo) {
        this.matchRepo = matchRepo;
        this.playerRepo = playerRepo;
    }

    public String startMatch(String firstPlayer, String secondPlayer) {

        Match match = new Match(new Player(firstPlayer), new Player(secondPlayer));
        String uuid = UUID.randomUUID().toString();
        matches.put(uuid, match);

        return uuid;

    }

    public MatchDto pointWinsBy(String uuid, String name) {
        Match match = matches.get(uuid);
        if (match == null) {
            throw new MatchDoesntExist("матч с таким uuid не найден");
        }

        match.pointWonBy(new Player(name));

        if(match.isFinished()) {
            saveMatch(match);
            matches.remove(uuid);
        }

        return parseMatch(match);
    }

    public MatchDto getMatch(String uuid) {
        Match match = matches.get(uuid);
        if (match == null) {
            throw new MatchDoesntExist("матч с таким uuid не найден");
        }

        return parseMatch(match);
    }

    public MatchesResponseDto getMatches(int pageNumber) {
        List<MatchResponseDto> responseDtoList;
        try (Session session = PoolManager.getSession()) {
            List<MatchEntity> matchEntities = matchRepo.findMatches(session,pageNumber,10);
            responseDtoList = matchEntities.stream()
                    .map(x -> new MatchResponseDto(x.getPlayer1().getName(), x.getPlayer2().getName(), x.getWinner().getName()))
                    .toList();
        }

        Long l = matchRepo.countMatches();
        return new MatchesResponseDto(responseDtoList, pageNumber, (int) Math.ceil((double) l.intValue() /10));
    }

    private PlayerEntity getPlayer(String name) {
        return playerRepo.findByName(name)
                .orElseGet(() -> {
                    PlayerEntity player = new PlayerEntity(name);
                    playerRepo.persist(player);
                    return player;
                });
    }
    private void saveMatch(Match match) {
        PlayerEntity firstPlayer = getPlayer(match.getFirstPlayer().getName());
        PlayerEntity secondPlayer = getPlayer(match.getSecondPlayer().getName());

        matchRepo.persist(new MatchEntity(
                firstPlayer,
                secondPlayer,
                match.getWinner().equals(match.getFirstPlayer()) ? firstPlayer : secondPlayer));
    }



    private MatchDto parseMatch(Match match) {
        PlayerScoreDto firstPlayer = parseMatchToPlayerScoreDto(match,1);
        PlayerScoreDto secondPlayer = parseMatchToPlayerScoreDto(match,2);
        return new MatchDto(firstPlayer, secondPlayer, match.getWinnerName().isPresent() ? match.getWinnerName().get() : null);
    }
    private PlayerScoreDto parseMatchToPlayerScoreDto(Match match, int player) {
        if(player == 1) {
            return new PlayerScoreDto(match.getFirstPlayer().toString(),
                    match.getCurrentGamePoints().isPresent() ? match.getCurrentGamePoints().get().playerOneScore() : null,
                    match.getCurrentSetGameScore().getFirstPlayer(),
                    match.getFirstPlayerSets(),
                    match.getCurrentTieScore().isPresent() ? match.getCurrentTieScore().get().getFirstPlayer() : null);
        } else if(player == 2) {
            return new PlayerScoreDto(match.getSecondPlayer().toString(),
                    match.getCurrentGamePoints().isPresent() ? match.getCurrentGamePoints().get().playerTwoScore() : null,
                    match.getCurrentSetGameScore().getSecondPlayer(),
                    match.getSecondPlayerSets(),
                    match.getCurrentTieScore().isPresent() ? match.getCurrentTieScore().get().getSecondPlayer() : null);
        }
        return null;

    }

}
